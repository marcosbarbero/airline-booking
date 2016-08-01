package com.marcosbarbero.customer.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinColumn(name = "country_code")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String fullName;

    @NotNull
    @Column(nullable = false, length = 45)
    private String phone;
}
