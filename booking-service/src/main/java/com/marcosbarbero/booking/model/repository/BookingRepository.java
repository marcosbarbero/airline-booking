package com.marcosbarbero.booking.model.repository;

import com.marcosbarbero.booking.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Repository to access ${@link Booking} data.
 *
 * @author Marcos Barbero
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b where b.customer.id = :customerId")
    Collection<Booking> findByCustomerId(@Param("customerId") Integer customerId);
}
