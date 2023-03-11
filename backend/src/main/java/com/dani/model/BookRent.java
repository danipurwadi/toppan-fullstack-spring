package com.dani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(BookRentId.class)
@Table(name = "book_rents")
public class BookRent {

    @Id
    @NotNull
    @Column(name = "person_id")
    private Long personId;

    @Id
    @NotNull
    @Column(name = "book_id")
    private Long bookId;

    @Id
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`createdAt`")
    private java.util.Date createdAt;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`updatedAt`")
    @NotNull
    private java.util.Date updatedAt;

    public BookRent(Long personId, Long bookId, Date createdAt, Date updatedAt) {
        this.personId = personId;
        this.bookId = bookId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BookRent() { }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRent bookRent = (BookRent) o;
        return personId.equals(bookRent.personId) && bookId.equals(bookRent.bookId) && createdAt.equals(bookRent.createdAt) && updatedAt.equals(bookRent.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, bookId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BookRent{" +
                "personId=" + personId +
                ", bookId=" + bookId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}