package com.marcosbarbero.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The Schedule representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
public class Schedule implements Serializable {
    private static final long serialVersionUID = -6718405251387892891L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "route_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departureTimeGmt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrivalTimeGmt;
}
