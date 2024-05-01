package org.example.service;

import org.example.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTestService {
    public void testJPA() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("ExamplePU");
            em = emf.createEntityManager();

            em.getTransaction().begin();
            Author author = new Author("Mark Twain");
            em.persist(author);

            Author a = (Author) em.createQuery(
                            "select e from Author e where e.name='Mark Twain'")
                    .getSingleResult();
            a.setName("Samuel Langhorne Clemens");
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
