package com.marcosbarbero.customer.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Marcos Barbero
 */
@Data
@MappedSuperclass
public class AutoId {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
