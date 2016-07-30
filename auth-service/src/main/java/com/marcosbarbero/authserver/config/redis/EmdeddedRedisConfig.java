package com.marcosbarbero.authserver.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.Socket;

/**
 * Embedded Redis configuration.
 *
 * @author Marcos Barbero
 */
@Configuration
public class EmdeddedRedisConfig {

    RedisServer redisServer;

    private static boolean available(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException {
        redisServer = new RedisServer(Protocol.DEFAULT_PORT);
        if (available(Protocol.DEFAULT_PORT)) {
            redisServer.start();
        }
        return new JedisConnectionFactory();
    }

    @PreDestroy
    public void destroy() {
        redisServer.stop();
    }
}
