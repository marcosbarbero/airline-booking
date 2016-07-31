package com.marcosbarbero.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Booking representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@EqualsAndHashCode(callSuper = true)
public class Booking extends AutoId implements Serializable {
    private static final long serialVersionUID = 393412128505036550L;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn(name = "flight_class_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FlightClass flightClass;

}
