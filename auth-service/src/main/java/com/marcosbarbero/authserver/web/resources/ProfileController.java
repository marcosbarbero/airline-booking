package com.marcosbarbero.authserver.web.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(ProfileController.URI)
public class ProfileController {

    protected static final String URI = "/me";

    @RequestMapping(method = RequestMethod.GET)
    public Principal authenticatedUser(Principal principal) {
        return principal;
    }
}
