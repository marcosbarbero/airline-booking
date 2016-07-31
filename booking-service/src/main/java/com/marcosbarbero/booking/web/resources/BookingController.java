package com.marcosbarbero.booking.web.resources;

import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller to handle requests to ${@link com.marcosbarbero.booking.model.entity.Booking} resource.
 *
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(BookingController.URI)
public class BookingController extends BasicController<Booking, BookingRepository> {

    protected static final String URI = "";

    @Autowired
    public BookingController(final BookingRepository bookingRepository) {
        super(URI, bookingRepository);
    }

    /**
     * Return a ${@link Booking} for the given id.
     *
     * @param id The booking id
     * @return Status code 200 with Booking on response body
     */
    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<Booking> get(@PathVariable Integer id) {
        return super.get(id);
    }

    /**
     * Persist a ${@link Booking}.
     *
     * @param booking The Booking to persist
     * @param result  BindingResult
     * @param builder UriComponentsBuilder
     * @return StatusCode 200 with the header Location with new resource
     */
    @RequestMapping(method = POST)
    public ResponseEntity save(@RequestBody @Valid Booking booking, BindingResult result, UriComponentsBuilder builder) {
        return super.save(booking, result, builder);
    }
}
