package com.marcosbarbero.booking.service;

import com.marcosbarbero.booking.BookingApplication;
import com.marcosbarbero.booking.exception.ResourceNotFoundException;
import com.marcosbarbero.booking.helper.Given;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.BDDMockito.given;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class)
public class BookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    private BookingService bookingService;

    @Before
    public void setUp() {
        this.bookingService = new BookingService(this.bookingRepository);
    }

    @Test
    public void testFindByCustomerId() {
        given(this.bookingRepository.findByCustomerId(Given.booking().getCustomer().getId())).willReturn(Given.bookings());

        Collection<Booking> bookings = this.bookingService.findByCustomerId(Given.booking().getCustomer().getId());
        Assert.notEmpty(bookings);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindByCustomerIdThrowsError() {
        Integer customerId = -1;
        given(this.bookingRepository.findByCustomerId(customerId)).willReturn(Collections.emptyList());
        this.bookingService.findByCustomerId(customerId);
    }

    @Test
    public void testUpdateStatus() {
        Integer id = 1;
        Booking given = Given.booking();
        Booking paid = Given.booking();
        paid.setStatus(BookingStatus.PAID);

        given(this.bookingRepository.findOne(id)).willReturn(given);
        given(this.bookingRepository.save(given)).willReturn(paid);

        Booking booking = this.bookingService.updateStatus(id, BookingStatus.PAID);
        Assert.isTrue(booking.getStatus() == paid.getStatus());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateStatus_bookingNotFound() {
        Integer id = -1;
        given(this.bookingRepository.findOne(id)).willReturn(null);
        this.bookingService.updateStatus(id, BookingStatus.PAID);
    }
}
