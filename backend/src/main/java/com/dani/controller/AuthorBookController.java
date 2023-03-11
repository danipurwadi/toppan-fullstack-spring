package com.dani.controller;

import com.dani.dto.AuthorBookDTO;
import com.dani.exception.BadRequestException;
import com.dani.model.*;
import com.dani.repository.AuthorBookRepository;
import com.dani.repository.AuthorRepository;
import com.dani.repository.BookRepository;
import com.dani.repository.PersonRepository;
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

    @GetMapping("/{authorId}/{bookId}")
    public AuthorBook getAuthorBook(@PathVariable Integer authorId, @PathVariable Integer bookId) {
        try {
            Author author = authorRepository.findById(authorId).get();
            Book book = bookRepository.findById(bookId).get();
            return authorBookRepository.findById(new AuthorBookId(author, book)).get();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping("/{authorId}/{bookId}")
    public void deleteAuthorBook(@PathVariable Integer authorId, @PathVariable Integer bookId) {
        try {
            Author author = authorRepository.findById(authorId).get();
            Book book = bookRepository.findById(bookId).get();
            authorBookRepository.deleteById(new AuthorBookId(author, book));
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @PutMapping("/{authorId}/{bookId}")
    public AuthorBook updateAuthorBook(@RequestBody AuthorBook newAuthorBook, @PathVariable Integer authorId, @PathVariable Integer bookId) {
        try {
            Author author = authorRepository.findById(authorId).get();
            Book book = bookRepository.findById(bookId).get();
            return authorBookRepository.findById(new AuthorBookId(author, book))
                    .map(authorBook -> {
                        authorBook.setAuthor(newAuthorBook.getAuthor());
                        authorBook.setBook(newAuthorBook.getBook());
                        authorBook.setCreatedAt(newAuthorBook.getCreatedAt());
                        authorBook.setUpdatedAt(newAuthorBook.getUpdatedAt());
                        return authorBookRepository.save(authorBook);
                    }).get();
        } catch(Exception e) {
            throw new BadRequestException();
        }
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
