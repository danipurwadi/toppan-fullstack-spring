package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.Author;
import com.dani.model.Book;
import com.dani.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin("http://localhost:8080")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public Author newAuthor(@RequestBody Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException());
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        try {
            authorRepository.deleteById(id);
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@RequestBody Author newAuthor, @PathVariable Integer id) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(newAuthor.getName());
                    author.setCreatedAt(newAuthor.getCreatedAt());
                    author.setUpdatedAt(newAuthor.getUpdatedAt());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new BadRequestException());
    }
}
