package com.marcosbarbero.booking.dto;

import com.esotericsoftware.kryo.NotNull;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    @NotNull
    private Integer customerId;

    @NotNull
    private Integer flightClassId;

    @NotNull
    private BookingStatus status;

}
