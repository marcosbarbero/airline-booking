package com.marcosbarbero.booking.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.dto.converter.BookingConverter;
import com.marcosbarbero.booking.exception.ResourceNotFoundException;
import com.marcosbarbero.booking.helper.Given;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.enums.BookingStatus;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import com.marcosbarbero.booking.service.BookingService;
import com.marcosbarbero.booking.web.resources.error.ResponseExceptionHandler;
import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for ${@link BookingController}.
 *
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    MockMvc mockMvc;
    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ResponseExceptionHandler exceptionHandler;

    private BookingController bookingController;

    private JacksonTester<BookingDTO> json;

    @Before
    public void setUp() {
        this.bookingController = new BookingController(this.bookingRepository, this.bookingService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).setControllerAdvice(this.exceptionHandler)
                .apply(documentationConfiguration(this.restDocumentation)).build();

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testPostBooking() throws Exception {
        BookingDTO bookingDTO = Given.bookingDTO();
        given(this.bookingRepository.save(any(Booking.class))).willReturn(BookingConverter.toBooking(bookingDTO));

        ResultActions result = this.mockMvc.perform(post(BookingController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(bookingDTO).getJson()));
        result.andExpect(status().isCreated())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("customerId").description("The customer id"),
                                fieldWithPath("flightClassId").description("The flight class id"),
                                fieldWithPath("status").description("The booking status")
                        ),
                        responseHeaders(headerWithName("Location").description("The created resource location"))
                ));
    }

    @Test
    @Ignore
    public void testSaveBooking_UnprocessableEntity() throws Exception {
        BookingDTO bookingDTO = Given.bookingDTO();
        bookingDTO.setCustomerId(null);
        given(this.bookingRepository.save(any(Booking.class))).willReturn(BookingConverter.toBooking(bookingDTO));

        ResultActions result = this.mockMvc.perform(post(BookingController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(bookingDTO).getJson()));
        result.andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSaveBooking_DataIntegrityViolation() throws Exception {
        BookingDTO bookingDTO = Given.bookingDTO();
        bookingDTO.setCustomerId(null);
        given(this.bookingRepository.save(any(Booking.class))).willThrow(DataIntegrityViolationException.class);

        ResultActions result = this.mockMvc.perform(post(BookingController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(bookingDTO).getJson()));
        result.andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void testGetByCustomerId() throws Exception {
        given(this.bookingService.findByCustomerId(Given.booking().getCustomer().getId())).willReturn(Given.bookings());

        this.mockMvc.perform(get(BookingController.URI + "/customer/{id}", Given.booking().getCustomer().getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(
                        document("{method-name}",
                                pathParameters(
                                        parameterWithName("id").description("Integer with Customer Id representation")
                                ),
                                responseFieldsSnippet(true),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)")
                                )
                        )
                );
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetByCustomerId_notFound() throws Exception {
        Integer customerId = -1;
        given(this.bookingService.findByCustomerId(customerId)).willThrow(ResourceNotFoundException.class);
        ResultActions result = this.mockMvc.perform(get(BookingController.URI + "/customer/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetById() throws Exception {
        Integer id = 1;
        Booking given = Given.booking();
        given(this.bookingRepository.findOne(id)).willReturn(given);

        this.mockMvc.perform(get(BookingController.URI + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(
                        document("{method-name}",
                                pathParameters(
                                        parameterWithName("id").description("Integer with Booking Id representation")
                                ),
                                responseFieldsSnippet(false),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)")
                                )
                        )
                );
    }

    @Test
    public void testGetById_NotFound() throws Exception {
        Integer id = -1;
        given(this.bookingRepository.findOne(id)).willReturn(null);

        ResultActions result = this.mockMvc.perform(get(BookingController.URI + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Integer id = 1;
        given(this.bookingService.updateStatus(id, BookingStatus.PAID)).willReturn(Given.booking());

        this.mockMvc.perform(put(BookingController.URI + "/{id}/status/{status}", id, BookingStatus.PAID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document("{method-name}",
                                pathParameters(
                                        parameterWithName("id").description("The booking ID representation."),
                                        parameterWithName("status").description("The status, available values are: CONFIRMED, CANCELED, PAID")
                                )
                        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateStatus_NotFound() throws Exception {
        Integer id = -1;
        given(this.bookingService.updateStatus(id, BookingStatus.PAID)).willThrow(ResourceNotFoundException.class);

        ResultActions result = this.mockMvc.perform(put(BookingController.URI + "/{id}/status/{status}", id, BookingStatus.PAID)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isNotFound());
    }

    private ResponseFieldsSnippet responseFieldsSnippet(boolean isArray) {
        String prefix = isArray ? "[]." : "";
        return responseFields(
                fieldWithPath(prefix + "id").description("The Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "creationDate").description("The creation date"),
                fieldWithPath(prefix + "status").description("The booking status, e.g: CONFIRMED").type(STRING),

                fieldWithPath(prefix + "customer.id").description("The customer id, e.g: 876").type(NUMBER),
                fieldWithPath(prefix + "customer.username").description("The username, e.g: ironman").type(STRING),
                fieldWithPath(prefix + "customer.fullName").description("The full name, e.g: Tony Stark").type(STRING),
                fieldWithPath(prefix + "customer.phone").description("The customer phone number, e.g: +551198776633").type(STRING),

                fieldWithPath(prefix + "flightClass.id").description("The flight class Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "flightClass.priceInCents").description("The price in cents, e.g: 5000").type(NUMBER),
                fieldWithPath(prefix + "flightClass.flight.id").description("The flight class Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "flightClass.flight.code").description("The flight code, e.g: J8098").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.status").description("The flight status, e.g: ACTIVE").type(STRING),

                fieldWithPath(prefix + "flightClass.flight.schedule.id").description("The schedule id, e.g: 10L").type(NUMBER),
                fieldWithPath(prefix + "flightClass.flight.schedule.departureTimeGmt").description("The schedule departure time"),
                fieldWithPath(prefix + "flightClass.flight.schedule.arrivalTimeGmt").description("The schedule departure time"),

                fieldWithPath(prefix + "flightClass.flight.schedule.route.id").description("The route id, e.g: 15L").type(NUMBER),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.origin.iataCode").description("The IATA code, e.g: GRU").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.origin.name").description("The airport name, e.g: São Paulo–Guarulhos International Airport").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.origin.country.code").description("The country code, e.g: BR").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.origin.country.name").description("The country name, e.g: Brazil").type(STRING),

                fieldWithPath(prefix + "flightClass.flight.schedule.route.destination.iataCode").description("The IATA code, e.g: GRU").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.destination.name").description("The airport name, e.g: São Paulo–Guarulhos International Airport").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.destination.country.code").description("The country code, e.g: BR").type(STRING),
                fieldWithPath(prefix + "flightClass.flight.schedule.route.destination.country.name").description("The country name, e.g: Brazil").type(STRING),

                fieldWithPath(prefix + "flightClass.aircraftClass.id").description("The aircraft class Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "flightClass.aircraftClass.aircraft.id").description("The aircraft Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "flightClass.aircraftClass.aircraft.model").description("The aircraft model, e.g: Boeing 747").type(STRING),
                fieldWithPath(prefix + "flightClass.aircraftClass.travelClass.id").description("The travel class Id, e.g: 3786").type(NUMBER),
                fieldWithPath(prefix + "flightClass.aircraftClass.travelClass.name").description("The travel class name, e.g: First class").type(STRING)
        );
    }
}
