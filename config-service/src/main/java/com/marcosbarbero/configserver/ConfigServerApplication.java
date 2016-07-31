package com.marcosbarbero.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Configuration Server provider.
 *
 * @author Marcos Barbero
 */
@EnableConfigServer
@SpringCloudApplication
public class ConfigServerApplication {

    public static void main(String... args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
