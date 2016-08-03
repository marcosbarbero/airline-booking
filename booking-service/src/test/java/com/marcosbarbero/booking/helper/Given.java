package com.marcosbarbero.booking.helper;

import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.model.entity.*;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import com.marcosbarbero.booking.model.entity.enums.FlightStatus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

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
        customer.setUsername("user.name");
        return customer;
    }

    public static BookingDTO bookingDTO() {
        Integer customerId = 1;
        Integer flightClassId = 1;
        return new BookingDTO(customerId, flightClassId, BookingStatus.CONFIRMED);
    }

    public static Booking booking() {
        Customer customer = customer();
        customer.setId(1);

        AircraftClass aircraftClass = new AircraftClass();
        aircraftClass.setAircraft(new Aircraft(1, "Boeing 747"));
        aircraftClass.setId(1);
        aircraftClass.setTravelClass(new TravelClass(1, "First class"));

        Airport airport = new Airport("GRU", "Guarulhos", new Country("BR", "Brazil"));

        Schedule schedule = new Schedule();
        schedule.setArrivalTimeGmt(new Date());
        schedule.setDepartureTimeGmt(new Date());
        schedule.setId(1);
        schedule.setRoute(new Route(1, airport, airport));

        FlightClass flightClass = new FlightClass();
        flightClass.setId(1);
        flightClass.setAircraftClass(aircraftClass);
        flightClass.setFlight(new Flight(1, schedule, UUID.randomUUID().toString(), FlightStatus.ACTIVE));
        flightClass.setPriceInCents(50000L);

        Booking booking = new Booking();
        booking.setId(1);
        booking.setCustomer(customer);
        booking.setFlightClass(flightClass);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreationDate(new Date());
        return booking;
    }

    public static Collection<Booking> bookings() {
        return Arrays.asList(booking());
    }
}
