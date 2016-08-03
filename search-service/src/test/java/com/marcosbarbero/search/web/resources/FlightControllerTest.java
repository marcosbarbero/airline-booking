package com.marcosbarbero.search.web.resources;

import com.marcosbarbero.search.SearchApplication;
import com.marcosbarbero.search.helper.Given;
import com.marcosbarbero.search.service.FlightService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.marcosbarbero.search.web.resources.FlightController.URI;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SearchApplication.class)
public class FlightControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    MockMvc mockMvc;
    @MockBean
    private FlightService flightService;
    private FlightController flithController;

    @Before
    public void setUp() throws Exception {
        this.flithController = new FlightController(this.flightService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(flithController)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void testGetFlights() throws Exception {
        String origin = "GRU";
        String dest = "ALB";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String departureAsString = simpleDateFormat.format(new Date());
        Date departure = simpleDateFormat.parse(departureAsString);
        given(this.flightService.get(origin, dest, departure)).willReturn(Given.flights());

        this.mockMvc.perform(get(URI).param("origin", origin).param("dest", dest)
                .param("departure", departureAsString)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestParameters(
                                parameterWithName("origin").description("The airport origin IATA code"),
                                parameterWithName("dest").description("The airport destination IATA code"),
                                parameterWithName("departure").description("The departure date")
                        ),
                        responseFields(
                                fieldWithPath("[]").description("Array of flights"),
                                fieldWithPath("[].id").description("The fight id, e.g: 1L").type(NUMBER),
                                fieldWithPath("[].code").description("The flight code, e.g: J8098").type(STRING),
                                fieldWithPath("[].status").description("The flight status, e.g: ACTIVE").type(STRING),
                                fieldWithPath("[].schedule.id").description("The schedule id, e.g: 10L").type(NUMBER),
                                fieldWithPath("[].schedule.departureTimeGmt").description("The schedule departure time"),
                                fieldWithPath("[].schedule.arrivalTimeGmt").description("The schedule departure time"),
                                fieldWithPath("[].schedule.route.id").description("The route id, e.g: 15L").type(NUMBER),
                                fieldWithPath("[].schedule.route.origin.iataCode").description("The IATA code, e.g: GRU").type(STRING),
                                fieldWithPath("[].schedule.route.origin.name").description("The airport name, e.g: São Paulo–Guarulhos International Airport").type(STRING),
                                fieldWithPath("[].schedule.route.origin.country.code").description("The country code, e.g: BR").type(STRING),
                                fieldWithPath("[].schedule.route.origin.country.name").description("The country name, e.g: Brazil").type(STRING),
                                fieldWithPath("[].schedule.route.destination.iataCode").description("The IATA code, e.g: GRU").type(STRING),
                                fieldWithPath("[].schedule.route.destination.name").description("The airport name, e.g: São Paulo–Guarulhos International Airport").type(STRING),
                                fieldWithPath("[].schedule.route.destination.country.code").description("The country code, e.g: BR").type(STRING),
                                fieldWithPath("[].schedule.route.destination.country.name").description("The country name, e.g: Brazil").type(STRING)),
                        responseHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)"))
                ));
    }
}
