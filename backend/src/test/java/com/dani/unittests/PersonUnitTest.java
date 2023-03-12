package com.dani.unittests;

import com.dani.model.Person;
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
class PersonUnitTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    EntityManager entityManager;

    // Positive Tests
    @Test
    @Transactional
    public void When_FieldsAreProper_Expect_PersonConstructed() {
        Person person = new Person();
        person.setName("John Loke");
        person.setUpdatedAt(new Date());
        person.setCreatedAt(new Date());

        entityManager.persist(person);
        entityManager.flush();

        assertNotNull(person.getId());
    }

    @Test
    @Transactional
    public void When_PersonsAreCreated_Expect_IdsToBeSequential() {
        Person person = new Person();
        person.setName("Harry Potter");
        person.setUpdatedAt(new Date());
        person.setCreatedAt(new Date());

        Person book2 = new Person();
        book2.setName("Lord of the Rings");
        book2.setUpdatedAt(new Date());
        book2.setCreatedAt(new Date());

        entityManager.persist(person);
        entityManager.persist(book2);

        assertEquals(1, book2.getId() - person.getId());
    }

    // Negative Tests
    @Test
    @Transactional
    public void When_FieldsAreNull_Expect_ConstraintError() {
        Person person = new Person();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(person);
            entityManager.flush();
        });
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=createdAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=updatedAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=name"));

    }

    @Test
    @Transactional
    public void When_NameExceedLimit_Expect_Error() {
        Person person = new Person();
        person.setName("VERY_LONG_TITLE".repeat(20));
        person.setUpdatedAt(new Date());
        person.setCreatedAt(new Date());

        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            entityManager.persist(person);
            entityManager.flush();
        });

        assertTrue(exception.getCause() instanceof DataException);
    }
}
