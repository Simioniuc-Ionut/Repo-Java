package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll",
                query = "select e from Book e order by e.title"),
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    // Alte atribute ale cărții

    // Constructor
    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Metode suplimentare, dacă sunt necesare

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
