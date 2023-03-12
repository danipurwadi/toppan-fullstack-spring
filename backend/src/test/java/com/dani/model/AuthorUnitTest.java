package com.dani.model;

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
class AuthorUnitTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    EntityManager entityManager;

    // Positive Tests
    @Test
    @Transactional
    public void When_FieldsAreProper_Expect_AuthorConstructed() {
        Author author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());

        entityManager.persist(author);
        entityManager.flush();

        assertNotNull(author.getId());
    }

    @Test
    @Transactional
    public void When_AuthorsAreCreated_Expect_IdsToBeSequential() {
        Author author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());

        Author book2 = new Author();
        book2.setName("JRR Tolkein");
        book2.setUpdatedAt(new Date());
        book2.setCreatedAt(new Date());

        entityManager.persist(author);
        entityManager.persist(book2);

        assertEquals(1, book2.getId() - author.getId());
    }

    // Negative Tests
    @Test
    @Transactional
    public void When_FieldsAreNull_Expect_ConstraintError() {
        Author author = new Author();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(author);
            entityManager.flush();
        });
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=createdAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=updatedAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=name"));
    }

    @Test
    @Transactional
    public void When_NameExceedLimit_Expect_Error() {
        Author author = new Author();
        author.setName("VERY_LONG_TITLE".repeat(20));
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());

        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            entityManager.persist(author);
            entityManager.flush();
        });

        assertTrue(exception.getCause() instanceof DataException);
    }
}
