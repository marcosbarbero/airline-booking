package com.marcosbarbero.customer.web.resources;

import com.marcosbarbero.customer.exception.ResourceNotFoundException;
import com.marcosbarbero.customer.model.entity.Customer;
import com.marcosbarbero.customer.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Marcos Barbero
 */
@RestController
@RequestMapping(CustomerController.URI)
public class CustomerController extends BasicController<Customer, CustomerRepository> {

    protected static final String URI = "";

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(final CustomerRepository customerRepository) {
        super(URI, customerRepository);
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Customer> get(@PathVariable Integer id) {
        return super.get(id);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Customer> get(@RequestParam String username) {
        Optional<Customer> optional = Optional.ofNullable(this.customerRepository.findOneByUsername(username));
        optional.orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(optional.get());
    }

    @RequestMapping(method = POST)
    public ResponseEntity save(@RequestBody @Valid Customer customer, BindingResult result, UriComponentsBuilder builder) {
        return super.save(customer, result, builder);
    }
}
