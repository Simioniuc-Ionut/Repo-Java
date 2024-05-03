package org.example;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private List<String> authors;
    private List<String> genres;
    private int pages;
    private int year;
    public String language;

    public Book(int id, int year, String language, int pages, String title, List<String> authors, List<String> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.pages = pages;
        this.year = year;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", pages=" + pages +
                ", year=" + year +
                ", language='" + language + '\'' +
                '}';
    }
}
