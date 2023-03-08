package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.AuthorBookRepository;
import com.dani.repository.AuthorRepository;
import com.dani.repository.BookRepository;
import com.dani.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/author-books")
@CrossOrigin("http://localhost:8080")
public class AuthorBookController {

    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public AuthorBook newAuthorBook(@RequestBody AuthorBook newAuthorBook) {
        return authorBookRepository.save(newAuthorBook);
    }

    @GetMapping
    public List<AuthorBook> getAllAuthorBooks() {
        return authorBookRepository.findAll();
    }

    @GetMapping
    public AuthorBook getAuthorBook(
            @RequestParam("book-id") Integer bookId,
            @RequestParam("author-id") Integer authorId
    ) {
        try {
            Author author = authorRepository.findById(authorId).get();
            Book book = bookRepository.findById(bookId).get();
            return authorBookRepository.findById(new AuthorBookId(author, book)).orElseThrow(() -> new BadRequestException());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
