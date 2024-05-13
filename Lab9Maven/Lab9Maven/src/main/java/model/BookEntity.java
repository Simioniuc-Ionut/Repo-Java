package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "books", schema = "db")
public class BookEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "page")
    private Integer page;

    @Column(name = "title", nullable = false)
    private String title;

    public BookEntity(String title, Integer page, String language, Integer year, Integer id) {
        this.title = title;
        this.page = page;
        this.language = language;
        this.year = year;
        this.id = id;
    }

    public BookEntity() {

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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(language, that.language) && Objects.equals(page, that.page) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, language, page, title);
    }
}