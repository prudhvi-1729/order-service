package com.capstone.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI orderServiceAPI(){
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info().title("Order Service API").version("1.0.0"));
    }

}
