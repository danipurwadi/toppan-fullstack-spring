package com.dani.controller;

import com.dani.model.Person;
import com.dani.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@CrossOrigin("http://localhost:3000")
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
}
