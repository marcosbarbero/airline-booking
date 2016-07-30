package com.marcosbarbero.authserver.web.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(UserController.URI)
public class UserController {

    protected static final String URI = "/users";

}
