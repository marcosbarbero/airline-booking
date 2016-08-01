package com.marcosbarbero.customer.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.customer.CustomerApplication;
import com.marcosbarbero.customer.helper.Given;
import com.marcosbarbero.customer.model.entity.Customer;
import com.marcosbarbero.customer.model.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class)
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
