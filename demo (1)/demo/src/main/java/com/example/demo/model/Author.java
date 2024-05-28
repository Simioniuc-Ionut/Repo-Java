package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
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

    public Author(String name) {
        this.name = name;
    }

    // Constructor gol necesar pentru JPA
    public Author() {
    }


}