package com.dani.controller;

import com.dani.service.CountryCodeTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class RandomCountryController {
    @Autowired
    CountryCodeTranslatorService countryCodeTranslatorService;

    @GetMapping("/getRandomCountry")
    public Map<String, Map<String, String>> getRandomCountryAlphaCode() {
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("country", countryCodeTranslatorService.getRandomCountryCode())
        );
    }
}
