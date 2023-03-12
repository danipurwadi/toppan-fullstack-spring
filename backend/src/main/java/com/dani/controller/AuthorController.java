package com.dani.controller;

import com.dani.model.Author;
import com.dani.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin("http://localhost:3000")
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
}
