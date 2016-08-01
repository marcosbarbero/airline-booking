package com.marcosbarbero.booking.service;

import com.marcosbarbero.booking.exception.ResourceNotFoundException;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void updateStatus(Integer id, BookingStatus status) {
        Optional<Booking> optional = Optional.ofNullable(this.bookingRepository.findOne(id));
        optional.orElseThrow(() -> new ResourceNotFoundException(id));
        Booking booking = optional.get();
        booking.setStatus(status);
        this.bookingRepository.save(booking);
    }
}
