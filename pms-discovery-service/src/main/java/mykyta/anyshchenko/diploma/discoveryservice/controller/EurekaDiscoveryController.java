package mykyta.anyshchenko.diploma.discoveryservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.eureka.registry.InstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("discovery")
public class EurekaDiscoveryController {

    @Autowired
    private InstanceRegistry instanceRegistry;


    @GetMapping({"/{serviceId}"})
    public String getServiceUrlByServiceId(@PathVariable String serviceId) {
        return instanceRegistry.getApplication(serviceId.toUpperCase()).getInstances().stream().findFirst()
                .map(InstanceInfo::getHomePageUrl)
                .orElseThrow(() -> new RuntimeException("There is no service with id " + serviceId));
    }
}
