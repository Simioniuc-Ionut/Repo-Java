package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "publishing_house", schema = "db")
@NamedQueries({
        @NamedQuery(name = "Publish.findAll",
                query = "SELECT e FROM PublishingHouseEntity e ORDER BY e.name "),
        @NamedQuery(name = "Publish.findByLabelName",
                query = "SELECT a FROM PublishingHouseEntity a WHERE a.name LIKE :name")
})
public class PublishingHouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "label_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    public Integer getId() {
        return id;
    }

    public PublishingHouseEntity(String labelName) {
        this.name = labelName;
    }

    public PublishingHouseEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return name;
    }

    public void setLabelName(String labelName) {
        this.name = labelName;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseEntity that = (PublishingHouseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, book);
    }
}