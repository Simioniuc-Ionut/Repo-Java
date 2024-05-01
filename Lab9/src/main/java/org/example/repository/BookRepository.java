package org.example.repository;

import org.example.entity.Author;
import org.example.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findByAuthor(Author author) {
        return entityManager.createQuery(
                        "SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }
}
