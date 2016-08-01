package com.marcosbarbero.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Route representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(schema = "booking", uniqueConstraints = @UniqueConstraint(columnNames = {"origin_airport_iata_code", "dest_airport_iata_code"}))
public class Route implements Serializable {
    private static final long serialVersionUID = -7471386277192531343L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "origin_airport_iata_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport origin;

    @JoinColumn(name = "dest_airport_iata_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport destination;
}
