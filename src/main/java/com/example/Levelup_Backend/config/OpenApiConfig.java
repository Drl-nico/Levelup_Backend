package com.example.Levelup_Backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        // For local development set the server URL to the port your backend uses
        openAPI.addServersItem(new Server().url("http://localhost:8082"));
        return openAPI;
    }
}
