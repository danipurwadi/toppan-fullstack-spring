package com.dani.controller;

import com.dani.CountryCodeTranslator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class RandomCountryController {
    @GetMapping("/getRandomCountry")
    public Map<String, Map<String, String>> getRandomCountryAlphaCode() {
        CountryCodeTranslator countryCodeTranslator = new CountryCodeTranslator();
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("country", countryCodeTranslator.getRandomCountryCode())
        );
    }
}
