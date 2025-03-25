package org.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI productAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Product API")
                .description("API for managing products and generate token")
                .version("1.0"));
    }
}