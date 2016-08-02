package com.marcosbarbero.search.model.repository;

import com.marcosbarbero.search.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marcos Barbero
 */
public interface AirportRepository extends JpaRepository<Airport, String> {

}
