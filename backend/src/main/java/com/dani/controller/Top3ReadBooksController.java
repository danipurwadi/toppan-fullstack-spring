package com.dani.controller;

import com.dani.CountryCodeTranslator;
import com.dani.exception.BadRequestException;
import com.dani.exception.NoResultException;
import com.dani.model.TopReadBook;
import com.dani.service.Top3BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class Top3ReadBooksController {

    @Autowired
    Top3BooksService top3BooksService;

    @GetMapping("/getTop3ReadBooks")
    public List<TopReadBook> getTop3ReadBooks(@RequestParam("country_code") String countryCode) {
        try {
            return top3BooksService.getTop3Books(countryCode);
        } catch(NoResultException noResultException) {
            throw noResultException;
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }
}
