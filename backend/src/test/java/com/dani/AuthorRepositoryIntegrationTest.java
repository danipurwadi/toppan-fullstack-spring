package com.dani;

import com.dani.model.Author;
import com.dani.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
class AuthorRepositoryIntegrationTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenFindByName_thenReturnAuthor() {
        Author dani = new Author(1, "dani", new Date(), new Date());
        authorRepository.save(dani);

        Optional<Author> query = authorRepository.findById(dani.getId());
        assertThat(query.isPresent()).isTrue();
        query.ifPresent(author -> assertThat(author.getId()).isEqualTo(dani.getId()));
    }
}
