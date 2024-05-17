package model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres", schema = "db")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll",
                query = " SELECT e FROM GenreEntity e ORDER BY e.name"),
        @NamedQuery(name = "Genre.findByName",
                query = "SELECT a FROM GenreEntity a WHERE a.name LIKE :name")
})
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "book_genres" ,
            joinColumns = @JoinColumn(name = "genres_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookEntity> books = new LinkedHashSet<>();

    public GenreEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    public GenreEntity() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }
}