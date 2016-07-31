package com.marcosbarbero.authserver.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout handler.
 *
 * @author Marcos Barbero
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private static final BearerTokenExtractor tokenExtractor = new BearerTokenExtractor();

    private final TokenStore tokenStore;

    public LogoutSuccessHandler(final TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Authentication auth = tokenExtractor.extract(request);
        if (auth != null) {
            final OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(auth.getPrincipal().toString());
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }
    }
}
