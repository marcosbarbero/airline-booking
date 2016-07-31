package com.marcosbarbero.booking.model.repository;

import com.marcosbarbero.booking.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to access ${@link Booking} data.
 *
 * @author Marcos Barbero
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
