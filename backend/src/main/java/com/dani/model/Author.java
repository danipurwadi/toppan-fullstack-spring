package com.dani.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "author_id_seq")
    private Integer id;

    @NotNull
    @Column(columnDefinition = "varchar(255)")
    private String name;

    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @NotNull
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    Set<AuthorBook> authorBookSet;

    public Author(Integer id, String name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<AuthorBook> getAuthorBookSet() {
        return authorBookSet;
    }

    public void setAuthorBookSet(Set<AuthorBook> authorBookSet) {
        this.authorBookSet = authorBookSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(createdAt, author.createdAt) && Objects.equals(updatedAt, author.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
