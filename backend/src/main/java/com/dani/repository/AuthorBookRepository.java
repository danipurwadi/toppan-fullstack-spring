package com.dani.repository;

import com.dani.model.Author;
import com.dani.model.AuthorBook;
import com.dani.model.AuthorBookId;
import com.dani.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, AuthorBookId> {

    @Query("select author.id from AuthorBook where book.id = :bookId")
    Integer getAuthorFromBookId(@Param("bookId") Integer bookId);
}
