package com.marcosbarbero.storefront.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marcos Barbero
 */
@Configuration
public class SocialConfiguration {

    @Autowired
    RestTemplate restTemplate;

    @Value("${booking.customers.endpoint}")
    private String endpoint;

    @Bean
    public SignInAdapter authSignInAdapter() {
        return (userId, connection, request) -> {
            AuthUtil.authenticate(connection);
            return null;
        };
    }
}
