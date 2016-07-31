package com.marcosbarbero.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Customer service
 *
 * @author Marcos Barbero
 */
@SpringCloudApplication
public class CustomerApplication {

    public static void main(String... args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
