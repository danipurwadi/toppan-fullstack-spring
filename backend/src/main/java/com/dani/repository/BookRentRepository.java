package com.dani.repository;

import com.dani.model.BookRent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentRepository extends JpaRepository<BookRent, Integer> {
}
