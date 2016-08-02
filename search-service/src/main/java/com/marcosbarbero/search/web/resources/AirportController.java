package com.marcosbarbero.search.web.resources;

import com.marcosbarbero.search.model.entity.Airport;
import com.marcosbarbero.search.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(AirportController.URI)
public class AirportController {

    protected static final String URI = "/airports";
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Collection<Airport>> get() {
        return ResponseEntity.ok(this.airportService.getAll());
    }
}
