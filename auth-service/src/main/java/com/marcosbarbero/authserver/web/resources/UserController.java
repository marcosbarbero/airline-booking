package com.marcosbarbero.authserver.web.resources;

import com.marcosbarbero.authserver.exception.InvalidRequestException;
import com.marcosbarbero.authserver.exception.ResourceNotFoundException;
import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller to handle requests to ${@link User} resource.
 *
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

    /**
     * Find a User by it's id.
     *
     * @param id The user Id
     * @return The User with status 200.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findOne(id));
        user.orElseThrow(() -> new ResourceNotFoundException(id));
        return ResponseEntity.ok(user.get());
    }

    /**
     * Save a User.
     *
     * @param user       The User
     * @param result     BindingResult to validate errors
     * @param uriBuilder The UriComponentsBuilder to build the location header
     * @return Status 200 with Location header to the resource
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity save(@RequestBody @Valid User user, BindingResult result, UriComponentsBuilder uriBuilder) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        final User savedUser = this.userRepository.save(user);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path(URI + "/{id}").buildAndExpand(savedUser.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
