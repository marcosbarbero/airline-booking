package com.marcosbarbero.booking.model.repository;

import com.marcosbarbero.booking.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ${@link com.marcosbarbero.booking.model.entity.Customer} repository.
 *
 * @author Marcos Barbero
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
