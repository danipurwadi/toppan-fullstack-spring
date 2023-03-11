package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.Book;
import com.dani.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        try {
            bookRepository.deleteById(id);
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Integer id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    book.setCreatedAt(newBook.getCreatedAt());
                    book.setUpdatedAt(newBook.getUpdatedAt());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BadRequestException());
    }
}
