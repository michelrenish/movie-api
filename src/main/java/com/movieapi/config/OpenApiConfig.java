package com.movieapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI/Swagger documentation.
 * Provides API metadata and documentation settings.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie API")
                        .description("A RESTful API for managing a movie catalog with in-memory storage")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Movie API Support")
                                .email("support@movieapi.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
