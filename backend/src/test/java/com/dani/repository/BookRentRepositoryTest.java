package com.dani.repository;

import com.dani.model.*;
import com.dani.util.PostgresqlContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testcontainers.containers.PostgreSQLContainer;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRentRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BookRentRepository bookRentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    private static final Pageable page = PageRequest.of(0, 3);
    private void createNewBookRents(Long personId, Long bookId, Integer num) {
        for (int i = 0; i < num; i++) {
            BookRent bookRent = new BookRent();
            bookRent.setPersonId(personId);
            bookRent.setBookId(bookId);
            bookRent.setCreatedAt(new Date());
            bookRent.setUpdatedAt(new Date());
            bookRentRepository.save(bookRent);
        }
    }

    @Test
    public void getAuthorFromBookId() {
        createNewBookRents(2L, 2L, 3);
        createNewBookRents(1L, 1L, 2);
        createNewBookRents(3L, 3L, 1);
        List<Long> top3BookIds = bookRentRepository.getTopBooksId(page);
        assertEquals(top3BookIds, List.of(2L, 1L, 3L));
    }

    @Test
    public void getTopBookBorrowerNamesInCountry() {
        Person person1 = new Person();
        person1.setName("John Loke");
        person1.setUpdatedAt(new Date());
        person1.setCreatedAt(new Date());
        person1.setCountryId(12L);
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setName("John Tan");
        person2.setUpdatedAt(new Date());
        person2.setCreatedAt(new Date());
        person2.setCountryId(23L);
        personRepository.save(person2);

        Person person3 = new Person();
        person3.setName("John Ho");
        person3.setUpdatedAt(new Date());
        person3.setCreatedAt(new Date());
        person3.setCountryId(23L);
        personRepository.save(person3);

        createNewBookRents((long) person1.getId(), 2L, 1);
        createNewBookRents((long) person2.getId(), 2L, 2);
        createNewBookRents((long) person3.getId(), 2L, 1);

        List<String> names = bookRentRepository.getTopBookBorrowerNamesInCountry(23L, 2L, page);
        assertEquals(List.of(person2.getName(), person3.getName()), names);
    }
}
