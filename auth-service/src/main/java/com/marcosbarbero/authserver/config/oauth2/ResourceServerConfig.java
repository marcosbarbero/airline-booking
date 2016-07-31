package com.marcosbarbero.authserver.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String LOGOUT = "/logout";

    @Autowired
    private TokenStore tokenStore;

    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        if (this.logoutSuccessHandler == null) {
            this.logoutSuccessHandler = new LogoutSuccessHandler(this.tokenStore);
        }
        return this.logoutSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter: off
        http.authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutUrl(LOGOUT)
                    .logoutSuccessHandler(this.logoutSuccessHandler());
        // @formatter: on
    }
}
