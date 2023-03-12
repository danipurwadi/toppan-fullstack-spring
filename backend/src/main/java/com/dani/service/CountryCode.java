package com.dani.service;

import org.springframework.stereotype.Component;

@Component
public class CountryCode {
    private String fullName;
    private String alphaCode;
    private Long countryCode;

    public CountryCode() { }

    public String getAlphaCode() {
        return alphaCode;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
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
