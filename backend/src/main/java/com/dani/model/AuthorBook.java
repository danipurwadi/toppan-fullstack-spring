package com.dani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(AuthorBookId.class)
@Table(name = "author_books")
public class AuthorBook {
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @Id
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public AuthorBook(Date createdAt, Date updatedAt, Author author, Book book) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
        this.book = book;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBook that = (AuthorBook) o;
        return createdAt.equals(that.createdAt) && updatedAt.equals(that.updatedAt) && author.equals(that.author) && book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, updatedAt, author, book);
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", author=" + author +
                ", book=" + book +
                '}';
    }
}
