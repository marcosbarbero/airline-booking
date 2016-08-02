package com.marcosbarbero.booking.helper;

import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.entity.FlightClass;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;

import java.util.Arrays;
import java.util.Collection;

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
        Customer customer = new Customer();
        customer.setId(1);

        FlightClass flightClass = new FlightClass();
        flightClass.setId(1);

        Booking booking = new Booking();
        booking.setId(1);
        booking.setCustomer(customer);
        booking.setFlightClass(flightClass);
        booking.setStatus(BookingStatus.CONFIRMED);
        return booking;
    }

    public static Collection<Booking> bookings() {
        return Arrays.asList(booking());
    }
}
