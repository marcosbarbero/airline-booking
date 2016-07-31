package com.marcosbarbero.booking.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.booking.BookingApplication;
import com.marcosbarbero.booking.helper.Given;
import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for CustomerController.
 *
 * @author Marcos Barbero
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookingApplication.class)
public class CustomerControllerTest {

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void testSaveCustomer() throws Exception {
        final Customer customer = Given.customer();
        given(this.customerRepository.save(any(Customer.class))).willReturn(customer);
        ResultActions result = this.mockMvc.perform(post(CustomerController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(customer)));
        result.andExpect(status().isCreated())
                .andDo(print());
    }
}
