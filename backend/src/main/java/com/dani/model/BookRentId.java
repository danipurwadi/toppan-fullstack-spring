package com.dani.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookRentId implements Serializable {
    private Long personId;
    private Long bookId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public BookRentId(Long personId, Long bookId, Date createdAt, Date updatedAt) {
        this.personId = personId;
        this.bookId = bookId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BookRentId() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRentId that = (BookRentId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(bookId, that.bookId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, bookId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BookRentId{" +
                "personId=" + personId +
                ", bookId=" + bookId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
