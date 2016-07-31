package com.marcosbarbero.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Marcos Barbero
 */
@Data
@MappedSuperclass
public class AutoId {

    @Id
    @JsonIgnore
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
