package com.dani.unittests;

import com.dani.model.Author;
import com.dani.model.Book;
import com.dani.model.AuthorBook;
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
class AuthorBookUnitTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    EntityManager entityManager;

    // Positive Tests
    @Test
    @Transactional
    public void When_FieldsAreProper_Expect_AuthorBookConstructed() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());
        entityManager.persist(book);

        Author author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());
        entityManager.persist(author);

        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBook.setUpdatedAt(new Date());
        authorBook.setCreatedAt(new Date());
        entityManager.persist(authorBook);

        assertEquals(author, authorBook.getAuthor());
        assertEquals(book, authorBook.getBook());
    }

    @Test
    @Transactional
    public void When_DeletingBook_Expect_AuthorBookToBeDeleted() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());
        entityManager.persist(book);

        Author author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());
        entityManager.persist(author);

        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBook.setUpdatedAt(new Date());
        authorBook.setCreatedAt(new Date());
        entityManager.persist(authorBook);
        entityManager.flush();

        entityManager.createQuery("DELETE FROM Book").executeUpdate();

        // Book and AuthorBook Table should be empty
        assertEquals(0, entityManager.createQuery("SELECT b FROM Book b").getResultList().size());
        assertEquals(0, entityManager.createQuery("SELECT ab FROM AuthorBook ab").getResultList().size());
    }

    @Test
    @Transactional
    public void When_DeletingAuthorBook_Expect_BookAndAuthorToNotBeDeleted() {
        Book book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());
        entityManager.persist(book);

        Author author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());
        entityManager.persist(author);

        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBook.setUpdatedAt(new Date());
        authorBook.setCreatedAt(new Date());
        entityManager.persist(authorBook);
        entityManager.flush();

        entityManager.createQuery("DELETE FROM AuthorBook").executeUpdate();

        // Author and Book tables should not be affected
        assertEquals(1, entityManager.createQuery("SELECT b FROM Book b").getResultList().size());
        assertEquals(1, entityManager.createQuery("SELECT a FROM Author a").getResultList().size());
    }

    // Negative Tests
    @Test
    @Transactional
    public void When_FieldsAreNull_Expect_ConstraintError() {
        AuthorBook authorBook = new AuthorBook();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(authorBook);
            entityManager.flush();
        });

        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=createdAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=updatedAt"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=author"));
        assertTrue(exception.getMessage().contains("interpolatedMessage='must not be null', propertyPath=book"));
    }
}
