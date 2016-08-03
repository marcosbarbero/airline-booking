package com.marcosbarbero.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Customer service
 *
 * @author Marcos Barbero
 */
@EnableResourceServer
@SpringCloudApplication
public class CustomerApplication {

    public static void main(String... args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
