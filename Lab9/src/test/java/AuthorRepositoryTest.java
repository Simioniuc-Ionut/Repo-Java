import static org.junit.Assert.assertEquals;

import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AuthorRepositoryTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("ExamplePU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testCreateAuthor() {
        // Crearea unui autor
        Author author = new Author();
        author.setName("Test Author");

        // Salvarea autorului în baza de date
        AuthorRepository authorRepository = new AuthorRepository(em);
        authorRepository.create(author);

        // Verificarea inserarii corecte a autorului
        Author savedAuthor = authorRepository.findById(author.getId());
        assertEquals("Test Author", savedAuthor.getName());
    }

    @Test
    public void testFindByName() {
        // Inserarea unor autori în baza de date
        AuthorRepository authorRepository = new AuthorRepository(em);

        Author author1 = new Author();
        author1.setName("Test Author 1");
        authorRepository.create(author1);

        Author author2 = new Author();
        author2.setName("Test Author 2");
        authorRepository.create(author2);

        Author author3 = new Author();
        author3.setName("Another Author");
        authorRepository.create(author3);

        // Cautarea autorilor dupa un sablon de nume
        List<Author> foundAuthors = authorRepository.findByName("Test Author");

        // Verificarea rezultatelor cautarii
        assertEquals(2, foundAuthors.size());
        assertEquals("Test Author 1", foundAuthors.get(0).getName());
        assertEquals("Test Author 2", foundAuthors.get(1).getName());
    }
}
