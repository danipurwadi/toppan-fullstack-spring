package com.dani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private java.util.Date createdAt;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
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
}