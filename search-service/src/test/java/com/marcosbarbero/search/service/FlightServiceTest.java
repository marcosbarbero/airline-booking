package com.marcosbarbero.search.service;

import com.marcosbarbero.search.SearchApplication;
import com.marcosbarbero.search.exception.ResourceNotFoundException;
import com.marcosbarbero.search.helper.Given;
import com.marcosbarbero.search.model.entity.Flight;
import com.marcosbarbero.search.model.repository.FlightRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static org.mockito.BDDMockito.given;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class FlightServiceTest extends TestCase {

    @MockBean
    private FlightRepository flightRepository;

    private FlightService flightService;

    @Before
    public void setUp() {
        this.flightService = new FlightService(this.flightRepository);
    }

    @Test
    public void testGet() throws Exception {
        String origin = "GRU";
        String dest = "ALB";
        Date departure = new Date();
        given(this.flightRepository.findBy(origin, dest, departure)).willReturn(Given.flights());

        Collection<Flight> flights = this.flightService.get(origin, dest, departure);
        Assert.notEmpty(flights);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGet_notFound() throws Exception {
        String origin = "GRU";
        String dest = "ALB";
        Date departure = new Date();
        given(this.flightRepository.findBy(origin, dest, departure)).willReturn(Collections.<Flight>emptyList());

        Collection<Flight> flights = this.flightService.get(origin, dest, departure);
        Assert.notEmpty(flights);
    }
}