package com.marcosbarbero.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "booking")
@EqualsAndHashCode(callSuper = true)
public class Payment extends AutoId implements Serializable {
    private static final long serialVersionUID = -3332024343237031083L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(nullable = false, length = 255)
    private String creditCardNumber;

    @Column(nullable = false, length = 255)
    private String creditCardName;

    @PreUpdate
    @PrePersist
    public void encryptCardNumber() {
        // TODO: encrypt the card number before store it
    }

}
