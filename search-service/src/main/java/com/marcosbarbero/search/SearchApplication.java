package com.marcosbarbero.search;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * A search module for the whole application.
 *
 * @author Marcos Barbero
 */
@EnableResourceServer
@SpringCloudApplication
public class SearchApplication {

    public static void main(String... args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
