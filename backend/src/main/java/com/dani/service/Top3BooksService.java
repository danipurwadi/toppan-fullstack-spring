package com.dani.service;

import com.dani.model.Top3Books;
import com.dani.repository.BookRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top3BooksService {
    @Autowired
    BookRentRepository bookRentRepository;

    public List<Top3Books> getTop3Books(String countryCode) {
        char[] charArray = countryCode.toCharArray();
        long countryCodeInt = (long) charArray[0] * (100 + (long) charArray[1]);
        List<Top3Books> topBooks = bookRentRepository.getTop3Books(countryCodeInt, PageRequest.of(0, 3));
        return topBooks;
    }
}
