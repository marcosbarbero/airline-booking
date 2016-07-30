package com.marcosbarbero.booking.model.repository;

import com.marcosbarbero.booking.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ${@link Country} repository.
 *
 * @author Marcos Barbero
 */
public interface CountryRepository extends JpaRepository<Country, String> {

}
