package com.dani.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AuthorBookId implements Serializable {
    private Long authorId;
    private Long bookId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public AuthorBookId(Long authorId, Long bookId, Date createdAt, Date updatedAt) {
        this.authorId = authorId;
        this.bookId = bookId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBookId that = (AuthorBookId) o;
        return authorId.equals(that.authorId) && bookId.equals(that.bookId) && createdAt.equals(that.createdAt) && updatedAt.equals(that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId, createdAt, updatedAt);
    }
}