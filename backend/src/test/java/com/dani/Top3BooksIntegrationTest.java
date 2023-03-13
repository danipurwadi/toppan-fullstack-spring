package com.dani;

import com.dani.model.*;
import com.dani.repository.*;
import com.dani.service.Top3BooksService;
import com.dani.util.PostgresqlContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class Top3BooksIntegrationTest {
    @ClassRule
    public static PostgreSQLContainer<PostgresqlContainer> postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    Top3BooksService top3BooksService;

    @Autowired
    BookRentRepository bookRentRepository;

    @Autowired
    AuthorBookRepository authorBookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private MockMvc mvc;

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
    public void When_ParametersAreValid_Expect_CorrectReturn() throws Exception {
        Person person1 = new Person();
        person1.setName("John Loke");
        person1.setUpdatedAt(new Date());
        person1.setCreatedAt(new Date());
        person1.setCountryId(702L);
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setName("John Tan");
        person2.setUpdatedAt(new Date());
        person2.setCreatedAt(new Date());
        person2.setCountryId(702L);
        personRepository.save(person2);

        Author author1 = new Author();
        author1.setName("JK Rowling");
        author1.setCreatedAt(new Date());
        author1.setUpdatedAt(new Date());
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("JRR Tolkein");
        author2.setCreatedAt(new Date());
        author2.setUpdatedAt(new Date());
        authorRepository.save(author2);

        Book book1 = new Book();
        book1.setName("Harry Potter");
        book1.setCreatedAt(new Date());
        book1.setUpdatedAt(new Date());
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setName("The Hobbit");
        book2.setCreatedAt(new Date());
        book2.setUpdatedAt(new Date());
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setName("Lord of the Rings");
        book3.setCreatedAt(new Date());
        book3.setUpdatedAt(new Date());
        bookRepository.save(book3);

        authorBookRepository.saveAll(List.of(
                new AuthorBook(author1, book1, new Date(), new Date()),
                new AuthorBook(author2, book2, new Date(), new Date()),
                new AuthorBook(author2, book3, new Date(), new Date())
        ));

        createNewBookRents((long) person1.getId(), (long) book1.getId(), 1);
        createNewBookRents((long) person2.getId(), (long) book1.getId(), 2);
        createNewBookRents((long) person1.getId(), (long) book2.getId(), 1);
        createNewBookRents((long) person2.getId(), (long) book3.getId(), 2);

        MvcResult result = mvc.perform(get("/getTop3ReadBooks?country_code=SG")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String parsedResult = result.getResponse().getContentAsString();

        TopReadBook rank1 = new TopReadBook(book1.getName(), author1.getName(), List.of(person2.getName(), person1.getName()));
        TopReadBook rank2 = new TopReadBook(book3.getName(), author2.getName(), List.of(person2.getName()));
        TopReadBook rank3 = new TopReadBook(book2.getName(), author2.getName(), List.of(person1.getName()));

        String expectedResult = "[" + rank1.convertToJsonStringFormat() + "," + rank2.convertToJsonStringFormat() + "," + rank3.convertToJsonStringFormat() + "]";
        assertEquals(expectedResult, parsedResult);
    }

    @Test
    public void When_CountryCodeIsInvalid_Expect_InvalidParameterResponse() throws Exception {
        MvcResult result = mvc.perform(get("/getTop3ReadBooks?country_code=XYZ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String parsedResult = result.getResponse().getContentAsString();
        String expectedResult = "{\"message\":\"invalid parameter\"}";
        assertEquals(expectedResult, parsedResult);
    }

    @Test
    public void When_CountryCodeHasNoResult_Expect_NoResultResponse() throws Exception {
        MvcResult result = mvc.perform(get("/getTop3ReadBooks?country_code=ID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String parsedResult = result.getResponse().getContentAsString();
        String expectedResult = "{\"message\":\"no results\"}";
        assertEquals(expectedResult, parsedResult);
    }
}
