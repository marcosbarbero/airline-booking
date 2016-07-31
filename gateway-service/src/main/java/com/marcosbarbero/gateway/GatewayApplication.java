package com.marcosbarbero.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * API Gateway application to route all requests to proper micro-service.
 *
 * @author Marcos Barbero
 */
@EnableZuulProxy
@EnableResourceServer
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String... args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
