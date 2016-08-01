package com.marcosbarbero.booking.dto.converter;

import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.entity.FlightClass;

/**
 * Booking converter from/to BookingDTO
 *
 * @author Marcos Barbero
 */
public class BookingConverter {

    /**
     * Convert BookingDTO to Booking.
     *
     * @param dto The BookingDTO
     * @return The Booking
     */
    public static Booking toBooking(final BookingDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());

        FlightClass flightClass = new FlightClass();
        flightClass.setId(dto.getFlightClassId());

        return new Booking(customer, flightClass, dto.getStatus(), null, null);
    }
}
