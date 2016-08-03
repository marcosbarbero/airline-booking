package com.marcosbarbero.search.web.resources;

import com.marcosbarbero.search.model.entity.Flight;
import com.marcosbarbero.search.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(FlightController.URI)
public class FlightController {

    protected static final String URI = "/flights";

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Collection<Flight>> get(@RequestParam String origin,
                                                  @RequestParam String dest,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
        return ResponseEntity.ok(this.flightService.get(origin, dest, departure));
    }
}
