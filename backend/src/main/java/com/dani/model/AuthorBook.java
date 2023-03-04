package com.dani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(AuthorBookId.class)
@Table(name = "author_books")
public class AuthorBook {

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private java.util.Date createdAt;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private java.util.Date updatedAt;

    @NotNull
    @Column(name = "author_id")
    private Integer authorId;

    @NotNull
    @Column(name = "book_id")
    private Integer bookId;

    public AuthorBook(Date createdAt, Date updatedAt, Integer authorId, Integer bookId) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public AuthorBook() {
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBook that = (AuthorBook) o;
        return createdAt.equals(that.createdAt) && updatedAt.equals(that.updatedAt) && authorId.equals(that.authorId) && bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, updatedAt, authorId, bookId);
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }
}
