package com.igreendata.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig for enable swagger .
 *
 * @author Dulip Chandana
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Swagger registration
     * @return Docket
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.igreendata.account.controller"))
                .paths(regex("/api.*"))
                .build();

    }
}
