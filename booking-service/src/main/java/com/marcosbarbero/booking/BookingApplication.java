package com.marcosbarbero.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Booking application startup class.
 *
 * @author Marcos Barbero
 */
@EnableResourceServer
@SpringCloudApplication
public class BookingApplication {

    public static void main(String... args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}
