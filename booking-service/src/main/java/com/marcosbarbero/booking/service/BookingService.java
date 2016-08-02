package com.marcosbarbero.booking.service;

import com.marcosbarbero.booking.exception.ResourceNotFoundException;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Booking Service layer.
 *
 * @author Marcos Barbero
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking updateStatus(Integer id, BookingStatus status) {
        Optional<Booking> optional = Optional.ofNullable(this.bookingRepository.findOne(id));
        optional.orElseThrow(() -> new ResourceNotFoundException(id));
        Booking booking = optional.get();
        booking.setStatus(status);
        return this.bookingRepository.save(booking);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Collection<Booking> findByCustomerId(Integer id) {
        Collection<Booking> bookings = this.bookingRepository.findByCustomerId(id);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return bookings;
    }
}
