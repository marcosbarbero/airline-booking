package com.marcosbarbero.search.service;

import com.marcosbarbero.search.SearchApplication;
import com.marcosbarbero.search.exception.ResourceNotFoundException;
import com.marcosbarbero.search.helper.Given;
import com.marcosbarbero.search.model.entity.Airport;
import com.marcosbarbero.search.model.repository.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.BDDMockito.given;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class AirportServiceTest {

    @MockBean
    private AirportRepository repository;

    private AirportService airportService;

    @Before
    public void setUp() {
        this.airportService = new AirportService(this.repository);
    }

    @Test
    public void testGetAll() {
        given(this.repository.findAll()).willReturn(Given.airports());
        Collection<Airport> airports = this.airportService.getAll();
        Assert.notEmpty(airports);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetAll_emptyValue() {
        given(this.repository.findAll()).willReturn(Collections.<Airport>emptyList());
        Collection<Airport> airports = this.airportService.getAll();
        Assert.isTrue(airports.isEmpty());
    }
}
