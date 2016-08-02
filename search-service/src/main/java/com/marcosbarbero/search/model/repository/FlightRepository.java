package com.marcosbarbero.search.model.repository;

import com.marcosbarbero.search.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

/**
 * @author Marcos Barbero
 */
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    String NATIVE_QUERY = "select f.* from booking.flight f " +
            "inner join booking.schedule s on s.id = f.schedule_id " +
            "inner join booking.route r on r.id = s.route_id " +
            "where r.origin_airport_iata_code = :origin and r.dest_airport_iata_code = :dest " +
            "and date_format(s.departure_time_gmt,'yyyy-MM-dd') = date_format(:departure,'yyyy-MM-dd')";

    @Query(value = NATIVE_QUERY, nativeQuery = true)
    Collection<Flight> findBy(@Param("origin") String origin, @Param("dest") String dest, @Param("departure") Date departure);
}
