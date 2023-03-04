package com.dani.repository;

import com.dani.model.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}
