package com.marcosbarbero.search.web.resources;

import com.marcosbarbero.search.helper.Given;
import com.marcosbarbero.search.service.AirportService;
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

import static com.marcosbarbero.search.web.resources.AirportController.URI;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    MockMvc mockMvc;
    @MockBean
    private AirportService airportService;
    private AirportController airportController;

    @Before
    public void setUp() throws Exception {
        this.airportController = new AirportController(this.airportService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(airportController)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void testGetAllAirports() throws Exception {
        given(this.airportService.getAll()).willReturn(Given.airports());
        this.mockMvc.perform(get(URI).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        responseFields(
                                fieldWithPath("[]").description("Array of airports"),
                                fieldWithPath("[].iataCode").description("The IATA code, e.g: GRU").type(STRING),
                                fieldWithPath("[].name").description("The airport name, e.g: São Paulo–Guarulhos International Airport").type(STRING),
                                fieldWithPath("[].country.code").description("The country code, e.g: BR").type(STRING),
                                fieldWithPath("[].country.name").description("The country name, e.g: Brazil").type(STRING)),
                        responseHeaders(headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type of the result entity (application/json by default)"))
                ));
    }

}