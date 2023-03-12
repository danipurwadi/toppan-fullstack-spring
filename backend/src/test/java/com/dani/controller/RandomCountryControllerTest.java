package com.dani.controller;

import com.dani.service.CountryCodeTranslatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.AbstractMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RandomCountryController.class)
public class RandomCountryControllerTest {

    @MockBean
    private CountryCodeTranslatorService countryCodeTranslatorService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void GetRandomCountry_When_APICalled_Expect_ServiceCalled() throws Exception {
        Map<String, String> result = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("full_name", "Singapore"),
                new AbstractMap.SimpleEntry<>("country_code", "702")
        );

        given(countryCodeTranslatorService.getRandomCountryCode()).willReturn(result);

        mvc.perform(get("/getRandomCountry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country.full_name", is(result.get("full_name"))))
                .andExpect(jsonPath("$.country.country_code", is(result.get("country_code"))));
    }

}
