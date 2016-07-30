package com.marcosbarbero.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * API Gateway application to route all requests to proper microservice.
 *
 * @author Marcos Barbero
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String... args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
