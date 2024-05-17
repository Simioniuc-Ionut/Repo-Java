    import model.AuthorEntity;
    import org.junit.Before;
    import org.junit.Test;
    import repository.Simple.AuthorRepository;

    import java.util.List;

    import static org.junit.Assert.*;

    public class AuthorRepositoryTest {
        private AuthorRepository authorRepository;

        @Before
        public void setUp() {
            authorRepository = new AuthorRepository();
        }

        @Test
        public void testCreateAndFindById() {
            AuthorEntity authorEntity = new AuthorEntity("Mark Twain");
            authorRepository.create(authorEntity);

            AuthorEntity foundAuthor = authorRepository.findById(authorEntity.getId());
            assertEquals(authorEntity, foundAuthor);
        }

        @Test
        public void testFindByName() {
            AuthorEntity author1 = new AuthorEntity("Mark Twain");
            AuthorEntity author2 = new AuthorEntity("Mark Antony");
            authorRepository.create(author1);
            authorRepository.create(author2);

            List<AuthorEntity> authors = authorRepository.findByName("Mark");
            assertTrue(authors.contains(author1));
            assertTrue(authors.contains(author2));
        }

        @Test
        public void testFindAll() {
            AuthorEntity author1 = new AuthorEntity("Mark Twain");
            AuthorEntity author2 = new AuthorEntity("Mark Antony");
            authorRepository.create(author1);
            authorRepository.create(author2);
            List<AuthorEntity> authors = authorRepository.findAll();
            assertTrue(authors.contains(author1));
            assertTrue(authors.contains(author2));

        }
    }
