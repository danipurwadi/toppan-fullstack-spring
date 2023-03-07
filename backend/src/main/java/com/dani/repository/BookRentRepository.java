package com.dani.repository;

import com.dani.model.BookRent;
import com.dani.model.BookRentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentRepository extends JpaRepository<BookRent, BookRentId> {
}
