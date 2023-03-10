package com.dani.service;

import com.dani.CountryCodeTranslator;
import com.dani.model.Top3Books;
import com.dani.repository.BookRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top3BooksService {
    @Autowired
    BookRentRepository bookRentRepository;

    private CountryCodeTranslator countryCodeTranslator;

    public Top3BooksService() {
        this.countryCodeTranslator = new CountryCodeTranslator();
    }

    public List<Top3Books> getTop3Books(String alphaCode) {
        long countryCode = countryCodeTranslator.getCountryCode(alphaCode);
        List<Top3Books> topBooks = bookRentRepository.getTop3Books(countryCode, PageRequest.of(0, 3));
        return topBooks;
    }
}
