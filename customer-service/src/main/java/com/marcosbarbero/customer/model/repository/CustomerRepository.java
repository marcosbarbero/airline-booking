package com.marcosbarbero.customer.model.repository;

import com.marcosbarbero.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ${@link Customer} repository.
 *
 * @author Marcos Barbero
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
