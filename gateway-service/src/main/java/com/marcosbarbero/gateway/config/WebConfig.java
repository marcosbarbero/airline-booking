package com.marcosbarbero.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web configuration.
 *
 * @author Marcos Barbero
 */
@Configuration
public class WebConfig {

    private static final String MAPPING_ALL = "/**";
    private static final long MAX_AGE = 3600;
    private static final String METHODS = "GET,POST,HEAD,OPTIONS,PUT,PATCH,DELETE,TRACE";

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(MAPPING_ALL)
                        .allowedMethods(METHODS.split(","))
                        .maxAge(MAX_AGE);
            }
        };
    }
}
