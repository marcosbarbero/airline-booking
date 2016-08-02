package com.marcosbarbero.storefront.web.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Marcos Barbero
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public String userName(Principal principal) {
        return principal != null ? principal.getName() : null;
    }
}
