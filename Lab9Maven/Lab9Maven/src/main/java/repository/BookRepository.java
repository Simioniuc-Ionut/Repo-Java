package repository;

import model.AuthorEntity;

import model.BookEntity;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(BookEntity book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public BookEntity findById(Integer id) {
        return entityManager.find(BookEntity.class, id);
    }

    public List<BookEntity> findByAuthor(AuthorEntity author) {
        return entityManager.createQuery(
                        "SELECT b FROM BookEntity b WHERE b.author = :author", BookEntity.class)
                .setParameter("author", author)
                .getResultList();
    }
}
