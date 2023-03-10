package com.dani.controller;

import com.dani.dto.BookRentDTO;
import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.AuthorRepository;
import com.dani.repository.BookRentRepository;
import com.dani.repository.BookRepository;
import com.dani.repository.PersonRepository;
import com.dani.service.Top3BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8080")
public class BookRentController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRentRepository bookRentRepository;

    @Autowired
    private Top3BooksService top3BooksService;

    @PostMapping("/book-rents")
    public BookRent newBookRent(@RequestBody BookRent newBookRent) {
        return bookRentRepository.save(newBookRent);
    }

    @GetMapping("/book-rents")
    public List<BookRent> getAllBookRents() {
        return bookRentRepository.findAll();
    }

    @GetMapping("/{bookId}/{personId}")
    public BookRent getBookRent(
            @PathVariable Long bookId,
            @PathVariable Long personId,
            @RequestParam("created-at") String createdAtString,
            @RequestParam("updated-at") String updatedAtString
    ) {
        try {
            Date createdAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(createdAtString);
            Date updatedAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(updatedAtString);
            return bookRentRepository.findById(new BookRentId(personId, bookId, createdAt, updatedAt)).get();
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @GetMapping("/getTop3ReadBooks")
    public List<Top3Books> getTop3ReadBooks(@RequestParam("country_code") String countryCode) {
        try {
            return top3BooksService.getTop3Books(countryCode);
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @GetMapping("/getBookRentByCountry")
    public BookRent getBookRentByCountry(@RequestParam("country_code") String countryCode) {
        try {
            char[] charArray = countryCode.toCharArray();
            long countryCodeInt = (long) charArray[0] * (100 + (long) charArray[1]);
            System.out.println(countryCodeInt);
            BookRent bookRent = null;
            System.out.println(bookRent);
            return bookRent;
        } catch(Exception e) {
            System.out.println(e);
            throw new BadRequestException();
        }
    }


    @DeleteMapping("/{bookId}/{personId}")
    public void deleteBookRent(
            @PathVariable Long bookId,
            @PathVariable Long personId,
            @RequestParam("created-at") String createdAtString,
            @RequestParam("updated-at") String updatedAtString
    ) {
        try {
            Date createdAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(createdAtString);
            Date updatedAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(updatedAtString);
            bookRentRepository.deleteById(new BookRentId(personId, bookId, createdAt, updatedAt));
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @PutMapping("/{bookId}/{personId}")
    public BookRent updateBookRent(
            @RequestBody BookRent newBookRent,
            @PathVariable Long bookId,
            @PathVariable Long personId,
            @RequestParam("created-at") String createdAtString,
            @RequestParam("updated-at") String updatedAtString
    ) {
        try {
            Date createdAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(createdAtString);
            Date updatedAt = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(updatedAtString);
            return bookRentRepository.findById(new BookRentId(personId, bookId, createdAt, updatedAt))
                    .map(bookRent -> {
                        bookRent.setBookId(newBookRent.getBookId());
                        bookRent.setPersonId(newBookRent.getPersonId());
                        bookRent.setCreatedAt(newBookRent.getCreatedAt());
                        bookRent.setUpdatedAt(newBookRent.getUpdatedAt());
                        return bookRentRepository.save(bookRent);
                    }).get();
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }
}
