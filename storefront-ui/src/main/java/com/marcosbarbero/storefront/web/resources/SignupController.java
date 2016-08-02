package com.marcosbarbero.storefront.web.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcosbarbero.storefront.config.AuthUtil;
import com.marcosbarbero.storefront.service.AsyncCustomerRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Marcos Barbero
 */
@Controller
public class SignupController {

    private final ProviderSignInUtils signInUtils;
    private final AsyncCustomerRegistration customerRegistration;

    @Autowired
    public SignupController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository,
                            AsyncCustomerRegistration customerRegistration) {
        this.customerRegistration = customerRegistration;
        signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup")
    public String signUp(WebRequest request) throws JsonProcessingException {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);
        if (connection != null) {
            UserProfile userProfile = AuthUtil.authenticate(connection);
            signInUtils.doPostSignUp(connection.getDisplayName(), request);
            this.customerRegistration.execute(userProfile);
        }
        return "redirect:/";
    }
}
