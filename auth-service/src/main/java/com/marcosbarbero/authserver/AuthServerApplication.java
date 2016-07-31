package com.marcosbarbero.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * User Account and Authorization server.
 *
 * @author Marcos Barbero
 */
@SpringCloudApplication
public class AuthServerApplication {

    public static void main(String... args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
