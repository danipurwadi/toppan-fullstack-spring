package com.dani.model;

import com.dani.util.PostgresqlContainer;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookRentUnitTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    EntityManager entityManager;

    // Positive Tests
    @Test
    @Transactional
    public void When_FieldsAreProper_Expect_BookRentConstructed() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());
        entityManager.persist(book);

        Person person = new Person();
        person.setName("John Doe");
        person.setUpdatedAt(new Date());
        person.setCreatedAt(new Date());
        entityManager.persist(person);

        BookRent rentedBook = new BookRent();
        rentedBook.setBookId(1L);
        rentedBook.setPersonId(1L);
        rentedBook.setUpdatedAt(new Date());
        rentedBook.setCreatedAt(new Date());
        entityManager.persist(rentedBook);
        entityManager.flush();

        assertEquals(1L, rentedBook.getBookId());
        assertEquals(1L, rentedBook.getPersonId());
    }

    @Test
    @Transactional
    public void When_BookAndPersonIdDoesNotExist_Expect_BookRentConstructed() {
        BookRent rentedBook = new BookRent();
        rentedBook.setBookId(99L);
        rentedBook.setPersonId(99L);
        rentedBook.setUpdatedAt(new Date());
        rentedBook.setCreatedAt(new Date());

        entityManager.persist(rentedBook);
        entityManager.flush();

        assertEquals(99L, rentedBook.getBookId());
        assertEquals(99L, rentedBook.getPersonId());
    }

    // Negative Tests
    @Test
    @Transactional
    public void When_FieldsAreNull_Expect_ConstraintError() {
        BookRent rentedBook = new BookRent();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(rentedBook);
            entityManager.flush();
        });

        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=createdAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=updatedAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=bookId"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=personId"));
    }
}
