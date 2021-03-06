package com.marcosbarbero.customer.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * The customer representation.
 *
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer extends AutoId implements Serializable {
    private static final long serialVersionUID = 7028106544314377885L;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String fullName;

    @Column(nullable = true, length = 45)
    private String phone;
}
