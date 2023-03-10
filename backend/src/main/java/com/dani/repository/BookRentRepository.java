package com.dani.repository;

import com.dani.dto.BookRentDTO;
import com.dani.model.BookRent;
import com.dani.model.BookRentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRentRepository extends JpaRepository<BookRent, BookRentId> {

    @Query(value = "select br.book_id FROM book_rents as br\n" +
            "    LEFT OUTER JOIN people as p ON br.person_id = p.id\n" +
            "    WHERE country_id = :countryId\n" +
            "    GROUP BY book_id\n" +
            "    ; ", nativeQuery = true)
    public List<Object[]> getTop3Books(@Param("countryId") Long countryId);
}
