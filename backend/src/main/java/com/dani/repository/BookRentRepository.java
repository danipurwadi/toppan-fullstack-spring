package com.dani.repository;

import com.dani.model.BookRent;
import com.dani.model.BookRentId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRentRepository extends JpaRepository<BookRent, BookRentId> {

    @Query("select br.bookId FROM BookRent as br GROUP BY br.bookId ORDER BY COUNT(br.bookId) DESC")
    List<Long> getTopBooksId(Pageable pageable);

    @Query("select p.name FROM BookRent as br " +
            "LEFT OUTER JOIN Person as p ON br.personId = p.id " +
            "WHERE p.countryId = :countryId AND br.bookId = :bookId " +
            "GROUP BY br.personId, p.name ORDER BY COUNT(br.personId) DESC")
    List<String> getTopBookBorrowerNamesInCountry(@Param("countryId") Long countryId, @Param("bookId") Long bookId, Pageable pageable);
}
