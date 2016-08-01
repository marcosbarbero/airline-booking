package com.marcosbarbero.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Aircraft and Travel class relation representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking", uniqueConstraints = @UniqueConstraint(columnNames = {"aircraft_id", "travel_class_id"}))
public class AircraftClass implements Serializable {
    private static final long serialVersionUID = 653687593278323856L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "aircraft_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aircraft aircraft;

    @JoinColumn(name = "travel_class_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TravelClass travelClass;
}
