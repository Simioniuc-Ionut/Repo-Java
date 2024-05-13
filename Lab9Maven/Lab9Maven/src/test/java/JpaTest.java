import model.AuthorEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JpaTest {

    @Test
    public void testJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<AuthorEntity> authors = em.createQuery(
                        "select e from AuthorEntity e where e.name='Mark Twain'")
                .getResultList();

        if (authors.size() != 1) {
            throw new IllegalStateException("Mai mult de un autor cu numele 'Mark Twain' a fost găsit!");
        }

        AuthorEntity a = authors.get(0);
        a.setName("Samuel Langhorne Clemens");

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}