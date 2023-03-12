package com.dani.repository;

import com.dani.model.Author;
import com.dani.model.AuthorBook;
import com.dani.model.Book;
import com.dani.util.PostgresqlContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorBookRepositoryTest {
    @Autowired
    AuthorBookRepository authorBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    private Book book;
    private Author author;

    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("Harry Potter");
        book.setUpdatedAt(new Date());
        book.setCreatedAt(new Date());
        bookRepository.save(book);

        author = new Author();
        author.setName("JK Rowling");
        author.setUpdatedAt(new Date());
        author.setCreatedAt(new Date());
        authorRepository.save(author);

        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBook.setUpdatedAt(new Date());
        authorBook.setCreatedAt(new Date());
        authorBookRepository.save(authorBook);
    }

    @Test
    public void getAuthorFromBookId_When_GivenBookId_Expect_ReturnsCorrectAuthorId() {
        assertEquals(authorBookRepository.getAuthorIdFromBookId(book.getId()), author.getId());
    }

    @Test
    public void getAuthorFromBookId_When_GivenInvalidBookId_Expect_ReturnsNull() {
        assertNull(authorBookRepository.getAuthorIdFromBookId(999));
    }
}
