package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.AuthorRepository;
import com.dani.repository.BookRentRepository;
import com.dani.repository.BookRepository;
import com.dani.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/book-rents")
@CrossOrigin("http://localhost:8080")
public class BookRentController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

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

    @GetMapping
    public BookRent getBookRent(
            @RequestParam("book-id") Long bookId,
            @RequestParam("person-id") Long personId,
            @RequestParam("created-at") Date createdAt,
            @RequestParam("updated-at") Date updatedAt
    ) {
        return bookRentRepository.findById(
                new BookRentId(personId, bookId, createdAt, updatedAt)
            ).orElseThrow(() -> new BadRequestException());
    }
}
