package com.dani.controller;

import com.dani.exception.BadRequestException;
import com.dani.model.Author;
import com.dani.model.Person;
import com.dani.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
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

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        try {
            personRepository.deleteById(id);
        } catch(Exception e) {
            throw new BadRequestException();
        }
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setCreatedAt(newPerson.getCreatedAt());
                    person.setUpdatedAt(newPerson.getUpdatedAt());
                    person.setCountryId(newPerson.getCountryId());
                    return personRepository.save(person);
                })
                .orElseThrow(() -> new BadRequestException());
    }
}
