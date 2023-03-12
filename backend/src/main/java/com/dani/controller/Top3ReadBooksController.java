package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.exception.NoResultException;
import com.dani.model.TopReadBook;
import com.dani.service.Top3BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class Top3ReadBooksController {

    @Autowired
    Top3BooksService top3BooksService;

    @GetMapping("/getTop3ReadBooks")
    public List<TopReadBook> getTop3ReadBooks(@RequestParam("country_code") String countryCode) {
        try {
            List<TopReadBook> result = top3BooksService.getTop3Books(countryCode);
            if (result.size() == 0) {
                throw new NoResultException();
            }
            return result;
        } catch(NullPointerException e) {
            throw new BadRequestException();
        }
    }
}
