package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.BookRentRepository;
import com.dani.service.Top3BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
