package com.dani.model;

import java.util.List;
import java.util.Objects;

public class TopReadBook {
    private String name;
    private String author;
    private List<String> borrowers;

    public TopReadBook(String name, String author, List<String> borrowers) {
        this.name = name;
        this.author = author;
        this.borrowers = borrowers;
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

    public List<String> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<String> borrowers) {
        this.borrowers = borrowers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopReadBook topReadBook = (TopReadBook) o;
        return Objects.equals(name, topReadBook.name) && Objects.equals(author, topReadBook.author) && Objects.equals(borrowers, topReadBook.borrowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, borrowers);
    }

    @Override
    public String toString() {
        return "Top3Books{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", borrowers=" + borrowers +
                '}';
    }
}
