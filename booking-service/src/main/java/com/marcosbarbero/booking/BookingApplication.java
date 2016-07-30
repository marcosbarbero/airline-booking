package com.marcosbarbero.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Booking application startup class.
 *
 * @author Marcos Barbero
 */
@SpringCloudApplication
public class BookingApplication {

    public static void main(String... args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}
