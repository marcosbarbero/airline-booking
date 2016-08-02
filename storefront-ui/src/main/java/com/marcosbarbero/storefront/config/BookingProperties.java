package com.marcosbarbero.storefront.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties("booking.endpoints")
public class BookingProperties {
    private String customers;
}
