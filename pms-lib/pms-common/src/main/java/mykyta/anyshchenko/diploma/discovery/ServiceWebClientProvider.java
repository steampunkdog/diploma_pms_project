package mykyta.anyshchenko.diploma.discovery;

import mykyta.anyshchenko.diploma.discovery.exception.ServiceDoesntExistException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServiceWebClientProvider {

    private final DiscoveryClient discoveryClient;

    public ServiceWebClientProvider(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public WebClient buildWebClientForService(String serviceId) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(serviceId).stream()
                .findFirst()
                .orElseThrow(() -> new ServiceDoesntExistException(serviceId));

        return WebClient.builder()
                .baseUrl(serviceInstance.getUri().toString())
                .build();
    }
}

