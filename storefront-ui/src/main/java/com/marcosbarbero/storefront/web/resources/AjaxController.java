package com.marcosbarbero.storefront.web.resources;

import com.marcosbarbero.storefront.config.BookingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marcos Barbero
 */
@RestController
public class AjaxController {

    private final BookingProperties bookingProperties;
    private final RestTemplate restTemplate;

    @Autowired
    public AjaxController(BookingProperties bookingProperties, RestTemplate restTemplate) {
        this.bookingProperties = bookingProperties;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/airports")
    public String airports() {
        return this.restTemplate.getForEntity(this.bookingProperties.getAirports(), String.class).getBody();
    }
}
