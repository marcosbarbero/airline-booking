package com.marcosbarbero.search.exception;

/**
 * @author Marcos Barbero
 */
public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists() {
        super("Duplicated entry, the resource already exists.");
    }
}
