package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "authors", schema = "db")

public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    // Constructor care primește un șir de caractere pentru numele autorului
    public Author(String name) {
        this.name = name;
    }

    // Constructor gol necesar pentru JPA
    public Author() {
    }

    // Getters și setters pentru celelalte atribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}