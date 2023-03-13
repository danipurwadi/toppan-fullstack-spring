package com.dani.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TopReadBook {
    private String name;
    private String author;
    private List<String> borrower;

    public TopReadBook(String name, String author, List<String> borrower) {
        this.name = name;
        this.author = author;
        this.borrower = borrower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getBorrower() {
        return borrower;
    }

    public void setBorrower(List<String> borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopReadBook topReadBook = (TopReadBook) o;
        return Objects.equals(name, topReadBook.name) && Objects.equals(author, topReadBook.author) && Objects.equals(borrower, topReadBook.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, borrower);
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\"=\"" + name + '\"' +
                ",\"author\"=\"" + author + '\"' +
                ",\"borrower\"=\"" + borrower +
                '}';
    }

    public String convertToJsonStringFormat() {
        String borrowerJsonString = "[" + borrower.stream().map(s -> "\"" + s + "\"").map(Object::toString).collect(Collectors.joining(","))+ "]";
        return "{" +
                "\"name\":\"" + name + '\"' +
                ",\"author\":\"" + author + '\"' +
                ",\"borrower\":" + borrowerJsonString +
                '}';
    }
}
