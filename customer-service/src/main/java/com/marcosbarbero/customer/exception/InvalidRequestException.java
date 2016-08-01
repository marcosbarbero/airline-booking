package com.marcosbarbero.customer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.Errors;

/**
 * @author Marcos Barbero
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(Errors errors) {
        super("Invalid Request");
        this.errors = errors;
    }
}
