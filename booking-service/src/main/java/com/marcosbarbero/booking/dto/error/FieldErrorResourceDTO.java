package com.marcosbarbero.booking.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

/**
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResourceDTO {

    private String resource;
    private String field;
    private String code;
    private String message;

    /**
     * Build the FieldErrorResourceDTO from ${@link FieldError} parameter.
     *
     * @param fieldError The ${@link FieldError}
     * @return FieldErrorResourceDTO
     */
    public static FieldErrorResourceDTO build(FieldError fieldError) {
        FieldErrorResourceDTO fieldErrorResource = new FieldErrorResourceDTO();
        fieldErrorResource.setResource(fieldError.getObjectName());
        fieldErrorResource.setField(fieldError.getField());
        fieldErrorResource.setCode(fieldError.getCode());
        fieldErrorResource.setMessage(fieldError.getDefaultMessage());
        return fieldErrorResource;
    }

    @Override
    public String toString() {
        return "FieldErrorResourceDTO{" +
                "resource='" + resource + '\'' +
                ", field='" + field + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
