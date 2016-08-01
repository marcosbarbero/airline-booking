package com.marcosbarbero.booking.web.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosbarbero.booking.BookingApplication;
import com.marcosbarbero.booking.dto.BookingDTO;
import com.marcosbarbero.booking.dto.converter.BookingConverter;
import com.marcosbarbero.booking.helper.Given;
import com.marcosbarbero.booking.model.entity.Booking;
import com.marcosbarbero.booking.model.entity.Customer;
import com.marcosbarbero.booking.model.repository.BookingRepository;
import com.marcosbarbero.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for ${@link BookingController}.
 *
 * @author Marcos Barbero
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class)
public class BookingControllerTest {

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private BookingRepository bookingRepository = mock(BookingRepository.class);

    private BookingService bookingService = mock(BookingService.class);

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void testSaveBooking() throws Exception {
        BookingDTO bookingDTO = Given.bookingDTO();
        given(this.bookingRepository.save(any(Booking.class))).willReturn(BookingConverter.toBooking(bookingDTO));

        ResultActions result = this.mockMvc.perform(post(BookingController.URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bookingDTO)));
        result.andExpect(status().isCreated())
                .andDo(print());
    }
}
