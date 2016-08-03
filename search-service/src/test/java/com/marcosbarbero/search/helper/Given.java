package com.marcosbarbero.search.helper;

import com.marcosbarbero.search.model.entity.*;
import com.marcosbarbero.search.model.entity.enums.FlightStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Marcos Barbero
 */
public class Given {

    public static List<Airport> airports() {
        return Arrays.asList(new Airport("GRU", "Guarulhos", new Country("BR", "Brazil")));
    }

    public static List<Flight> flights() {
        Schedule schedule = new Schedule();
        schedule.setArrivalTimeGmt(new Date());
        schedule.setDepartureTimeGmt(new Date());
        schedule.setId(1);
        schedule.setRoute(new Route(1, airports().get(0), airports().get(0)));
        return Arrays.asList(new Flight(1, schedule, UUID.randomUUID().toString(), FlightStatus.ACTIVE));
    }
}
