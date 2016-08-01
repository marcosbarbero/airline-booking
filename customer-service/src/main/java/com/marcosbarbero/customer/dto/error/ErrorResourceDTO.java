package com.marcosbarbero.customer.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Marcos Barbero
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResourceDTO {

    private String code;
    private String message;
    private List<FieldErrorResourceDTO> fieldErrors;

    public ErrorResourceDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResourceDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", fieldErrors=" + fieldErrors +
                '}';
    }
}
