package com.dani.unittests;

import com.dani.model.Book;
import com.dani.util.PostgresqlContainer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.exception.DataException;
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
class BookUnitTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    EntityManager entityManager;

    // Positive Tests
    @Test
    @Transactional
    public void When_FieldsAreProper_Expect_BookConstructed() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());

        entityManager.persist(book);
        entityManager.flush();

        assertNotNull(book.getId());
    }

    @Test
    @Transactional
    public void When_BooksAreCreated_Expect_IdsToBeSequential() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());

        Book book2 = new Book();
        book2.setName("Lord of the Rings");
        book2.setUpdatedAt(new Date());
        book2.setCreatedAt(new Date());

        entityManager.persist(book);
        entityManager.persist(book2);

        assertEquals(1, book2.getId() - book.getId());
    }

    // Negative Tests
    @Test
    @Transactional
    public void When_FieldsAreNull_Expect_ConstraintError() {
        Book book = new Book();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(book);
            entityManager.flush();
        });
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=createdAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=updatedAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=name"));

    }

    @Test
    @Transactional
    public void When_NameExceedLimit_Expect_Error() {
        Book book = new Book();
        book.setName("VERY_LONG_TITLE".repeat(20));
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());

        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            entityManager.persist(book);
            entityManager.flush();
        });

        assertTrue(exception.getCause() instanceof DataException);
    }
}
