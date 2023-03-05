package com.dani.repository;

import com.dani.model.Author;
import com.dani.model.AuthorBook;
import com.dani.model.AuthorBookId;
import com.dani.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, AuthorBookId> {
}
