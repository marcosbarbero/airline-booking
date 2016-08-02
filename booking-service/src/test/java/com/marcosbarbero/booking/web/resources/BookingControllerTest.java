package com.marcosbarbero.booking.web.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for ${@link BookingController}.
 *
 * @author Marcos Barbero
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).setControllerAdvice(this.exceptionHandler).build();

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testSaveBooking() throws Exception {
        BookingDTO bookingDTO = Given.bookingDTO();
        given(this.bookingRepository.save(any(Booking.class))).willReturn(BookingConverter.toBooking(bookingDTO));

        ResultActions result = this.mockMvc.perform(post(BookingController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(bookingDTO).getJson()));
        result.andExpect(status().isCreated())
                .andDo(print());
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
        given(this.bookingService.findByCustomerId(Given.booking().getId())).willReturn(Given.bookings());
        ResultActions result = this.mockMvc.perform(get(BookingController.URI + "/customer/{id}", Given.booking().getCustomer().getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
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

        ResultActions result = this.mockMvc.perform(get(BookingController.URI + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isOk());
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

        ResultActions result = this.mockMvc.perform(put(BookingController.URI + "/{id}/status/{status}", id, BookingStatus.PAID)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateStatus_NotFound() throws Exception {
        Integer id = -1;
        given(this.bookingService.updateStatus(id, BookingStatus.PAID)).willThrow(ResourceNotFoundException.class);

        ResultActions result = this.mockMvc.perform(put(BookingController.URI + "/{id}/status/{status}", id, BookingStatus.PAID)
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        result.andDo(print())
                .andExpect(status().isNotFound());
    }
}
