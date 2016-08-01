package com.marcosbarbero.booking.dto;

import com.esotericsoftware.kryo.NotNull;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO implements Serializable {
    private static final long serialVersionUID = -2646281810259013487L;

    @NotNull
    private Integer customerId;

    @NotNull
    private Integer flightClassId;

    @NotNull
    private BookingStatus status;

}
