package com.marcosbarbero.storefront;

import com.marcosbarbero.storefront.config.BookingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marcos Barbero
 */
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableConfigurationProperties(BookingProperties.class)
public class StoreFrontApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String... args) {
        SpringApplication.run(StoreFrontApplication.class, args);
    }
}
