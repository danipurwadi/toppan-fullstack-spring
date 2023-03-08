package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.Author;
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
}
