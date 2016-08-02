package com.marcosbarbero.search;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * A search module for the whole application.
 *
 * @author Marcos Barbero
 */
@SpringCloudApplication
public class SearchApplication {

    public static void main(String... args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
