package com.marcosbarbero.booking.helper;

import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.model.entity.Country;
import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;

/**
 * Helper class to build unit tests beans.
 *
 * @author Marcos Barbero
 */
public class Given {

    public static Customer customer() {
        Customer customer = new Customer();
        customer.setCountry(new Country("BR", null));
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
}
