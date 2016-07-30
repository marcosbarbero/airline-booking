package com.marcosbarbero.authserver.web.resources;

import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(UserController.URI)
public class UserController {

    protected static final String URI = "/users";
    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity save(@RequestBody User user, UriComponentsBuilder uriBuilder) {
        final User savedUser = this.userRepository.save(user);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path(URI + "/{id}").buildAndExpand(savedUser.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
