package com.marcosbarbero.customer.helper;

import com.marcosbarbero.customer.model.entity.Customer;

/**
 * Helper class to build unit tests beans.
 *
 * @author Marcos Barbero
 */
public class Given {

    public static Customer customer() {
        Customer customer = new Customer();
        customer.setFullName("Full Name");
        customer.setPhone("+5511999999999");
        customer.setUsername("user_name");
        return customer;
    }
}
