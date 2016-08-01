package com.marcosbarbero.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Aircraft representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aircraft implements Serializable {
    private static final long serialVersionUID = 3073214252438179992L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String model;
}
