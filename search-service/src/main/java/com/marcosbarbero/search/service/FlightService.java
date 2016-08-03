package com.marcosbarbero.search.service;

import com.marcosbarbero.search.exception.ResourceNotFoundException;
import com.marcosbarbero.search.model.entity.Flight;
import com.marcosbarbero.search.model.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * @author Marcos Barbero
 */
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private void validate(Collection<Flight> flights) {
        if (flights.isEmpty()) {
            throw new ResourceNotFoundException();
        }
    }

    public Collection<Flight> get(String origin, String dest, Date departure) {
        Collection<Flight> flights = this.flightRepository.findBy(origin, dest, departure);
        validate(flights);
        return flights;
    }
}
