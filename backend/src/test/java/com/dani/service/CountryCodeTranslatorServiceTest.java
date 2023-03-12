package com.dani.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryCodeTranslatorServiceTest {

    @Autowired
    CountryCodeTranslatorService countryCodeTranslatorService;

    // Positive Tests
    @Test
    void When_getCountryCode_Expect_CorrectOutput() {
        Map<String, Long> randomCountryCodes = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("AF", 4L),
                new AbstractMap.SimpleEntry<>("SG", 702L),
                new AbstractMap.SimpleEntry<>("TH", 764L),
                new AbstractMap.SimpleEntry<>("ID", 360L),
                new AbstractMap.SimpleEntry<>("NZ", 554L)
        );

        for (String alphaCode : randomCountryCodes.keySet()) {
            assertEquals(randomCountryCodes.get(alphaCode), countryCodeTranslatorService.getCountryCode(alphaCode));
        }
    }

    @Test
    void When_getRandomCountryCode_Expect_CorrectOutput() {
        ArrayList<String> randomCodes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String randomCountryCode = countryCodeTranslatorService.getRandomCountryCode().get("country_code");
            assertNotNull(countryCodeTranslatorService.getCountryCode(randomCountryCode));
            randomCodes.add(randomCountryCode);
        }
        assertEquals(randomCodes.size(), 20);

        // Check that the 20 random codes are not all the same code
        // It is statistically impossible for all 20 random codes to be the same
        assertTrue(new HashSet<>(randomCodes).size() > 1);
    }

    // Negative Tests
    @Test
    void When_GivenInvalidAlphaCode_Expect_ExceptionThrown() {
        String invalidCode = "XYZ";
        assertThrows(NullPointerException.class, () -> countryCodeTranslatorService.getCountryCode(invalidCode));
    }
}