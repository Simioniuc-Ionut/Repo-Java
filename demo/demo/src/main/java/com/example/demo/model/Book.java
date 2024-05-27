package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "books", schema = "db")

public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id" , nullable = false)
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "title" )
    private String title;

    @Column(name = "page" )
    private int page;


    public Book(String title, Integer page, String language, Integer year) {
        this.title = title;
        this.page = page;
        this.language = language;
        this.year = year;
    }

    //constructo gol necesar pt jpa
    public Book() {

    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setLanguage(String eng){
        this.language= eng;
    }
    public void setPage(int page){
        this.page = page;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
