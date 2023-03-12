package com.dani.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
public class CountryCodeTranslatorService {
    @Autowired
    CountryCode countryCode;

    private HashMap<String, CountryCode> countryCodes;
    private static final String FILE_NAME = "ISOCountries.csv";

    public CountryCodeTranslatorService() {
        countryCodes = readCSV();
    }
    private HashMap<String, CountryCode> readCSV() {
        HashMap<String, CountryCode> result = new HashMap<>();
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + FILE_NAME);
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                CountryCode countryCode = new CountryCode();
                countryCode.setFullName(row[0]);
                countryCode.setAlphaCode(row[1]);
                countryCode.setCountryCode(Long.parseLong(row[2]));
                result.put(row[1], countryCode);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public Long getCountryCode(String alphaCode) {
        return countryCodes.get(alphaCode).getCountryCode();
    }

    public Map<String, String> getRandomCountryCode() {
        Set<String> alphaCodes = countryCodes.keySet();
        String randomAlphaCode = alphaCodes.stream().skip(new Random().nextInt(alphaCodes.size())).findFirst().get();
        CountryCode randomCountry = countryCodes.get(randomAlphaCode);
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("full_name", randomCountry.getFullName()),
                new AbstractMap.SimpleEntry<>("country_code", randomCountry.getAlphaCode())
        );
    }

}
