package com.dani.dto;

import java.util.Date;

public class AuthorBookDTO {
    private Integer authorId;
    private Integer bookId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public AuthorBookDTO(Integer authorId, Integer bookId, Date createdAt, Date updatedAt) {
        this.authorId = authorId;
        this.bookId = bookId;
        this.createdAt = createdAt;
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
    public String toString() {
        return "AuthorBookDTO{" +
                "authorId=" + authorId +
                ", bookId=" + bookId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
