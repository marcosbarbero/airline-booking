package com.marcosbarbero.customer.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.customer.helper.Given;
import com.marcosbarbero.customer.model.entity.Customer;
import com.marcosbarbero.customer.model.repository.CustomerRepository;
import com.marcosbarbero.customer.web.resources.error.ResponseExceptionHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(this.exceptionHandler)
                .apply(documentationConfiguration(this.restDocumentation)).build();

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    private MockHttpServletRequestBuilder doGet(String uri, Integer id, String... args) {
        MockHttpServletRequestBuilder doGet;
        if (id != null) {
            doGet = MockMvcRequestBuilders.get(uri, id);
        } else {
            doGet = MockMvcRequestBuilders.get(uri).param(args[0], args[1]);
        }
        return doGet;
    }

    @Test
    public void testPostCustomer() throws Exception {
        final Customer customer = Given.customer();
        given(this.repository.save(any(Customer.class))).willReturn(customer);

        ResultActions result = this.mockMvc.perform(post(CustomerController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(customer).getJson()));
        result.andExpect(status().isCreated())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("username").description("The customer username"),
                                fieldWithPath("fullName").description("The customer full name"),
                                fieldWithPath("phone").description("The customer phone number")
                        ),
                        responseHeaders(headerWithName("Location").description("The created resource location"))
                ));
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
    @SuppressWarnings("unchecked")
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
        customer.setId(id);
        given(this.repository.findOne(id)).willReturn(customer);

        ResultActions result = this.mockMvc.perform(doGet(CustomerController.URI + "/{id}", id));
        result.andExpect(status().isOk())
                .andDo(
                        document("{method-name}",
                                responseFields(
                                        fieldWithPath("id").description("The Id, e.g: 3786").type(NUMBER),
                                        fieldWithPath("username").description("The username, e.g: ironman").type(STRING),
                                        fieldWithPath("fullName").description("The full name, e.g: Tony Stark").type(STRING),
                                        fieldWithPath("phone").description("The customer phone number, e.g: +551198776633").type(STRING)
                                ),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)")
                                )
                        )
                );
    }

    @Test
    public void testGetCustomer_notFound() throws Exception {
        Integer id = -1;
        given(this.repository.findOne(id)).willReturn(null);

        ResultActions result = this.mockMvc.perform(doGet(CustomerController.URI + "/{id}", id));
        result.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    public void testGetCustomerByUsername() throws Exception {
        final Customer customer = Given.customer();
        customer.setId(1);
        given(this.repository.findOneByUsername(customer.getUsername())).willReturn(customer);

        ResultActions result = this.mockMvc.perform(doGet(CustomerController.URI, null, "username", customer.getUsername()));
        result.andExpect(status().isOk())
                .andDo(
                        document("{method-name}",
                                requestParameters(
                                        parameterWithName("username").description("String with the username to lookup")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("The Id, e.g: 3786").type(NUMBER),
                                        fieldWithPath("username").description("The username, e.g: ironman").type(STRING),
                                        fieldWithPath("fullName").description("The full name, e.g: Tony Stark").type(STRING),
                                        fieldWithPath("phone").description("The customer phone number, e.g: +551198776633").type(STRING)
                                ),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)")
                                )
                        )
                );
    }

    @Test
    public void testGetCustomerByUsername_notFound() throws Exception {
        final String username = "fail";
        given(this.repository.findOneByUsername(username)).willReturn(null);

        ResultActions result = this.mockMvc.perform(doGet(CustomerController.URI, null, "username", username));
        result.andExpect(status().isNotFound()).andDo(print());
    }
}
