package com.dani.repository;

import com.dani.model.BookRent;
import com.dani.model.BookRentId;
import com.dani.model.Top3Books;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRentRepository extends JpaRepository<BookRent, BookRentId> {

    @Query("select new com.dani.model.Top3Books(br.bookId, COUNT(*)) FROM BookRent as br\n" +
            "    LEFT OUTER JOIN Person as p ON br.personId = p.id\n" +
            "    WHERE p.countryId = :countryId\n" +
            "    GROUP BY br.bookId ORDER BY COUNT(br.bookId) DESC")
    public List<Top3Books> getTop3Books(@Param("countryId") Long countryId, Pageable pageable);
}
