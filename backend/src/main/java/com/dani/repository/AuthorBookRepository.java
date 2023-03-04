package com.dani.repository;

import com.dani.model.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}
