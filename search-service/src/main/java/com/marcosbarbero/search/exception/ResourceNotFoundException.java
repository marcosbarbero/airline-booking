package com.marcosbarbero.search.exception;

/**
 * @author Marcos Barbero
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Integer id) {
        super(String.format("The resource with id '%s' was not found.", id.toString()));
    }

    public ResourceNotFoundException() {
        super("The resource was not found.");
    }
}
