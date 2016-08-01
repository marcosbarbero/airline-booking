package com.marcosbarbero.customer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * The Country representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
public class Country implements Serializable {
    private static final long serialVersionUID = -6688891731503040796L;

    @Size(min = 2, max = 2)
    @NotNull
    @Id
    @Column(unique = true, nullable = false, length = 2)
    private String code;

    @Column(nullable = false, length = 45)
    private String name;

}
