package com.marcosbarbero.authserver.web.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller to handle requests to authenticated user profile.
 *
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(ProfileController.URI)
public class ProfileController {

    protected static final String URI = "/me";

    /**
     * Return the authenticated user profile.
     *
     * @param principal The authenticated user
     * @return Principal
     */
    @RequestMapping(method = RequestMethod.GET)
    public Principal authenticatedUser(final Principal principal) {
        return principal;
    }
}
