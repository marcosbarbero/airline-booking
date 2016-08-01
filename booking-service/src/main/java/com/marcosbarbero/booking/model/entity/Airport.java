package com.marcosbarbero.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Airport representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport implements Serializable {
    private static final long serialVersionUID = -6427986108292814616L;

    @Id
    @Column(nullable = false, unique = true, length = 3)
    private String iataCode;

    @Column(nullable = false, length = 150)
    private String name;

    @JoinColumn(name = "country_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
}
