package com.marcosbarbero.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The FlightClass representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
public class FlightClass implements Serializable {
    private static final long serialVersionUID = 3290167466951066043L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "flight_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    @JoinColumn(name = "aircraft_class_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AircraftClass aircraftClass;

    @Column(nullable = false)
    private Long priceInCents;
}
