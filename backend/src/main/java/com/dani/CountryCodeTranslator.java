package com.dani;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountryCodeTranslator {
    private HashMap<String, CountryCode> countryCodes;
    private static final String FILE_NAME = "ISOCountries.csv";

    public CountryCodeTranslator() {
        this.countryCodes = readCSV();
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
                result.put(row[1], new CountryCode(row[0], row[1], row[2]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public Long getCountryCode(String alphaCode) {
        return countryCodes.get(alphaCode).getCountryCode();
    }
}

class CountryCode {
    private String fullName;
    private String alphaCode;
    private Long countryCode;

    public CountryCode(String fullName, String alphaCode, String countryCode) {
        this.fullName = fullName;
        this.alphaCode = alphaCode;
        this.countryCode = Long.parseLong(countryCode);
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return "CountryCode{" +
                "alphaCode='" + alphaCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}