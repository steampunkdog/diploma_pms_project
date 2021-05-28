package mykyta.anyshchenko.diploma.bookingservice;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mykyta.anyshchenko.diploma.bookingservice.elasticsearch.CustomEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableReactiveElasticsearchRepositories("mykyta.anyshchenko.diploma.bookingservice.repository.index")
@EnableReactiveMongoRepositories("mykyta.anyshchenko.diploma.bookingservice.repository.mongo")
@EnableDiscoveryClient
@ComponentScan("mykyta.anyshchenko.diploma")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

    @Bean
    public EntityMapper getEntityMapper() {
        return new CustomEntityMapper();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(new JavaTimeModule());
    }
}
