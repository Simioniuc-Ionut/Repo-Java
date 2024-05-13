package repository;

import model.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AuthorRepository {
    private final EntityManagerFactory entityManagerFactory;

    public AuthorRepository() {
        entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
    }

    public void create(AuthorEntity author) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    public AuthorEntity findById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        AuthorEntity author = em.find(AuthorEntity.class, id);
        em.close();
        return author;
    }

    public List<AuthorEntity> findByName(String namePattern) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createNamedQuery("Author.findByName");
        query.setParameter("name", "%" + namePattern + "%");
        List<AuthorEntity> authors = query.getResultList();
        em.close();
        return authors;
    }
}
