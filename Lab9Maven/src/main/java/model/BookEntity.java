package model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books", schema = "db")
@NamedQueries({
        @NamedQuery(name = "Book.findAll",
        query = "SELECT e FROM BookEntity e ORDER BY e.name "),
        @NamedQuery(name = "Book.findByTitle",
        query = "SELECT a FROM BookEntity a WHERE a.name LIKE :name")
})
public class BookEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "page")
    private int page;

    @Column(name = "title", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "books")
    private Set<AuthorEntity> authors = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "books")
    private Set<GenreEntity> genres = new LinkedHashSet<>();


    public BookEntity(String title, Integer page, String language, Integer year, Integer id) {
        this.name = title;
        this.page = page;
        this.language = language;
        this.year = year;
        this.id = id;
    }

    public BookEntity() {

    }

    public BookEntity(int id, int year, String language, int page, String name) {
        this.id = id;
        this.year = year;
        this.language = language;
        this.page = page;
        this.name = name;
    }

    public BookEntity(int page, String language, int year, String name) {
        this.page = page;
        this.language = language;
        this.year = year;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(language, that.language) && Objects.equals(page, that.page) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, language, page, name);
    }
}