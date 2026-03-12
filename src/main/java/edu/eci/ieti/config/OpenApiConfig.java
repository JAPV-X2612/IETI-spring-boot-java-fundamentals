package edu.eci.ieti.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for Swagger documentation
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Project API")
                        .version("1.0.0")
                        .description("REST API for managing users in a Spring Boot application")
                        .contact(new Contact()
                                .name("Jesús Pinzón")
                                .email("jesus.pinzon-v@mail.escuelaing.edu.co")));
    }
}
