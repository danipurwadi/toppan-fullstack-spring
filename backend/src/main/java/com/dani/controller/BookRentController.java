package com.dani.controller;

import com.dani.model.*;
import com.dani.repository.BookRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-rents")
@CrossOrigin("http://localhost:3000")
public class BookRentController {

    @Autowired
    private BookRentRepository bookRentRepository;

    @PostMapping
    public BookRent newBookRent(@RequestBody BookRent newBookRent) {
        return bookRentRepository.save(newBookRent);
    }

    @GetMapping
    public List<BookRent> getAllBookRents() {
        return bookRentRepository.findAll();
    }
}
