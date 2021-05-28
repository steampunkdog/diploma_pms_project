package mykyta.anyshchenko.diploma.roomservice;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mykyta.anyshchenko.diploma.roomservice.elasticsearch.CustomEntityMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@EnableDiscoveryClient
@EnableReactiveElasticsearchRepositories("mykyta.anyshchenko.diploma.roomservice.repository.elasticsearch")
@EnableJpaRepositories("mykyta.anyshchenko.diploma.roomservice.repository.postgresql")
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
