package org.example.repository;

import org.example.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AuthorRepository(EntityManager em) {
    }

    public void create(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public Author findById(Integer id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findByName(String namePattern) {
        return entityManager.createQuery(
                        "SELECT a FROM Author a WHERE a.name LIKE :namePattern", Author.class)
                .setParameter("namePattern", "%" + namePattern + "%")
                .getResultList();
    }
}
