package com.marcosbarbero.booking.web.resources;

import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.repository.CustomerRepository;
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
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(CustomerController.URI)
public class CustomerController extends BasicController<Customer, CustomerRepository> {

    protected static final String URI = "/customer";

    @Autowired
    public CustomerController(final CustomerRepository customerRepository) {
        super(URI, customerRepository);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Customer> get(@PathVariable Integer id) {
        return super.get(id);
    }

    @RequestMapping(method = POST)
    public ResponseEntity save(@RequestBody @Valid Customer booking, BindingResult result, UriComponentsBuilder builder) {
        return super.save(booking, result, builder);
    }
}
