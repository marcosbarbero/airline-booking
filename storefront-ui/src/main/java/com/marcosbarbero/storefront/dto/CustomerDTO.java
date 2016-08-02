package com.marcosbarbero.storefront.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 279354756028020631L;

    private String username;

    private String fullName;

    private String phone;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
