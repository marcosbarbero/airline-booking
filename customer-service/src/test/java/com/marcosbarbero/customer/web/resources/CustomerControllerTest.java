package com.marcosbarbero.customer.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.customer.helper.Given;
import com.marcosbarbero.customer.model.entity.Customer;
import com.marcosbarbero.customer.model.repository.CustomerRepository;
import com.marcosbarbero.customer.web.resources.error.ResponseExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for CustomerController.
 *
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    MockMvc mockMvc;

    @MockBean
    private CustomerRepository repository;

    @Autowired
    private ResponseExceptionHandler exceptionHandler;

    private CustomerController controller;

    private JacksonTester<Customer> json;

    @Before
    public void setUp() {
        this.controller = new CustomerController(this.repository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(this.exceptionHandler).build();

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testSaveCustomer() throws Exception {
        final Customer customer = Given.customer();
        given(this.repository.save(any(Customer.class))).willReturn(customer);
        ResultActions result = this.mockMvc.perform(post(CustomerController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(customer).getJson()));
        result.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testSaveCustomer_unprocessableEntity() throws Exception {
        final Customer customer = Given.customer();
        customer.setUsername(null);
        given(this.repository.save(any(Customer.class))).willReturn(customer);
        ResultActions result = this.mockMvc.perform(post(CustomerController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(customer).getJson()));
        result.andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testSaveCustomer_dataIntegrityViolation() throws Exception {
        given(this.repository.save(any(Customer.class))).willThrow(DataIntegrityViolationException.class);
        ResultActions result = this.mockMvc.perform(post(CustomerController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(Given.customer()).getJson()));
        result.andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void testGetCustomer() throws Exception {
        Integer id = 1;
        final Customer customer = Given.customer();
        given(this.repository.findOne(id)).willReturn(customer);

        ResultActions result = this.mockMvc.perform(get(CustomerController.URI + "/{id}", id));
        result.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetCustomer_notFound() throws Exception {
        Integer id = -1;
        given(this.repository.findOne(id)).willReturn(null);

        ResultActions result = this.mockMvc.perform(get(CustomerController.URI + "/{id}", id));
        result.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    public void testGetCustomerByUsername() throws Exception {
        final Customer customer = Given.customer();
        given(this.repository.findOneByUsername(customer.getUsername())).willReturn(customer);

        ResultActions result = this.mockMvc.perform(get(CustomerController.URI).param("username", customer.getUsername()));
        result.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetCustomerByUsername_notFound() throws Exception {
        final String username = "fail";
        given(this.repository.findOneByUsername(username)).willReturn(null);

        ResultActions result = this.mockMvc.perform(get(CustomerController.URI).param("username", username));
        result.andExpect(status().isNotFound()).andDo(print());
    }
}
