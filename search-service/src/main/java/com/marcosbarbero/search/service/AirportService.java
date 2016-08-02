package com.marcosbarbero.search.service;

import com.marcosbarbero.search.exception.ResourceNotFoundException;
import com.marcosbarbero.search.model.entity.Airport;
import com.marcosbarbero.search.model.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Marcos Barbero
 */
@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // TODO: implement cache
    public Collection<Airport> getAll() {
        List<Airport> airports = this.airportRepository.findAll();
        if (airports.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return airports;
    }
}
