package com.marcosbarbero.booking.web.resources;

import com.marcosbarbero.booking.exception.InvalidRequestException;
import com.marcosbarbero.booking.exception.ResourceAlreadyExists;
import com.marcosbarbero.booking.exception.ResourceNotFoundException;
import com.marcosbarbero.booking.model.entity.AutoId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Generic controller to perform default controller actions over resources.
 *
 * @author Marcos Barbero
 */
public class BasicController<T extends AutoId, R extends JpaRepository> implements InitializingBean {

    protected R repository;
    protected String uri;

    public BasicController(final String uri, final R repository) {
        this.repository = repository;
        this.uri = uri;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.repository, "The repository cannot be null.");
        Assert.notNull(this.uri, "The 'uri' cannot be null.");
    }

    /**
     * Default get method to find by Integer id.
     *
     * @param id The id˚
     * @return The response.
     */
    @SuppressWarnings("unchecked")
    protected ResponseEntity<T> get(Integer id) {
        Optional<T> found = Optional.ofNullable((T) this.repository.findOne(id));
        found.orElseThrow(() -> new ResourceNotFoundException(id));
        return ResponseEntity.ok(found.get());
    }


    /**
     * Save the given T object.
     *
     * @param toSave  The T object to save
     * @param result  The BindingResult
     * @param builder The uri builder
     * @return StatusCode 200 with resource in header Location
     */
    @SuppressWarnings("unchecked")
    protected ResponseEntity save(T toSave, BindingResult result, UriComponentsBuilder builder) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        T save;
        try {
            save = (T) this.repository.save(toSave);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExists();
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path(this.uri + "/{id}").buildAndExpand(save.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
