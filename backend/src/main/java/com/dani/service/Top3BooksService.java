package com.dani.service;

import com.dani.CountryCodeTranslator;
import com.dani.exception.NoResultException;
import com.dani.model.Person;
import com.dani.model.TopReadBook;
import com.dani.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Top3BooksService {
    @Autowired
    BookRentRepository bookRentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorBookRepository authorBookRepository;

    private CountryCodeTranslator countryCodeTranslator;
    private Pageable page;

    public Top3BooksService() {
        this.countryCodeTranslator = new CountryCodeTranslator();
        this.page = PageRequest.of(0, 3);
    }

    public List<TopReadBook> getTop3Books(String alphaCode) {
        List<TopReadBook> top3Books = new ArrayList<>();
        long countryCode = countryCodeTranslator.getCountryCode(alphaCode);
        List<Long> topBooks = bookRentRepository.getTop3BooksId(page);
        boolean hasResult = false;
        for (Long bookId : topBooks) {
            Integer bookIdInt = bookId.intValue();
            String bookName = bookRepository.findById(bookIdInt).get().getName();
            Integer authorId = authorBookRepository.getAuthorFromBookId(bookIdInt);
            String authorName = authorRepository.findById(authorId).get().getName();
            List<String> topBorrowers = bookRentRepository.getTop3BookBorrowersInCountry(countryCode,bookId, page);
            if (topBorrowers.size() > 0) {
                hasResult = true;
            }
            top3Books.add(new TopReadBook(bookName, authorName, topBorrowers));
        }

        if (hasResult) {
            return top3Books;
        } else {
            throw new NoResultException();
        }
    }
}
