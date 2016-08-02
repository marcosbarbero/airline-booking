package com.marcosbarbero.search.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcosbarbero.search.model.entity.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Flight representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight implements Serializable {
    private static final long serialVersionUID = -6845496904405475804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @JoinColumn(name = "schedule_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Schedule schedule;

    @Column(nullable = false, length = 45)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private FlightStatus status;

}
