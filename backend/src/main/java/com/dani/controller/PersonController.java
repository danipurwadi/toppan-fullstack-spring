package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.Person;
import com.dani.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:8080")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public Person newPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new BadRequestException());
    }

}
