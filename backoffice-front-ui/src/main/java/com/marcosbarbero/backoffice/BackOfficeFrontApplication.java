package com.marcosbarbero.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marcos Barbero
 */
@SpringBootApplication
public class BackOfficeFrontApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String... args) {
        SpringApplication.run(BackOfficeFrontApplication.class, args);
    }
}
