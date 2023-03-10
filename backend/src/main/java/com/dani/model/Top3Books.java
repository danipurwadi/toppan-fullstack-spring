package com.dani.model;

import java.util.Objects;

public class Top3Books implements Comparable<Top3Books> {
    private Long bookId;
    private Long bookCount;

    public Top3Books(Long bookId, Long bookCount) {
        this.bookId = bookId;
        this.bookCount = bookCount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Top3Books top3Books = (Top3Books) o;
        return Objects.equals(bookId, top3Books.bookId) && Objects.equals(bookCount, top3Books.bookCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookCount);
    }

    @Override
    public String toString() {
        return "Top3Books{" +
                "bookId=" + bookId +
                ", bookCount=" + bookCount +
                '}';
    }

    public int compareTo(Top3Books book) {
        return Math.toIntExact(book.getBookCount()) - Math.toIntExact(this.getBookCount());
    }
}
