package com.dani;

import com.dani.model.*;
import com.dani.repository.*;
import com.dani.service.Top3BooksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class Top3BooksServiceTest {

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
    public void getTop3Books_When_ParametersAreValid_Expect_CorrectReturn() {
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


        List<TopReadBook> topBooks = top3BooksService.getTop3Books("SG");
        System.out.println(topBooks);

        TopReadBook rank1 = new TopReadBook(book1.getName(), author1.getName(), List.of(person2.getName(), person1.getName()));
        TopReadBook rank2 = new TopReadBook(book3.getName(), author2.getName(), List.of(person2.getName()));
        TopReadBook rank3 = new TopReadBook(book2.getName(), author2.getName(), List.of(person1.getName()));

        assertEquals(topBooks, List.of(rank1, rank2, rank3));
    }

    @Test
    public void getTop3Books_When_NoBorrowersPresent_Expect_EmptyList() {
        List<TopReadBook> result = top3BooksService.getTop3Books("SG");
        assertEquals(result.size(), 0);
    }

    @Test
    public void getTop3Books_When_CountryCodeIsInvalid_Expect_NullPointerException() {
        assertThrows(NullPointerException.class, () -> top3BooksService.getTop3Books("XYZ"));
    }

}
