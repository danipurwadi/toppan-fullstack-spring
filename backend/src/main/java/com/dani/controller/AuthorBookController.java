package com.dani.controller;

import com.dani.dto.AuthorBookDTO;
import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.AuthorBookRepository;
import com.dani.repository.AuthorRepository;
import com.dani.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author-books")
@CrossOrigin("http://localhost:3000")
public class AuthorBookController {

    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public AuthorBook newAuthorBook(@RequestBody AuthorBookDTO newAuthorBook) {
        return authorBookRepository.save(convertToEntity(newAuthorBook));
    }

    @GetMapping
    public List<AuthorBook> getAllAuthorBooks() {
        return authorBookRepository.findAll();
    }

    private AuthorBook convertToEntity(AuthorBookDTO authorBookDTO) {
        try {
            Author author = authorRepository.findById(authorBookDTO.getAuthorId()).get();
            Book book = bookRepository.findById(authorBookDTO.getBookId()).get();
            return new AuthorBook(author, book, authorBookDTO.getCreatedAt(), authorBookDTO.getUpdatedAt());
        } catch (Exception e) {
            throw new BadRequestException();
        }

    }
}
