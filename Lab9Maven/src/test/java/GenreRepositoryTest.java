
import model.GenreEntity;
import org.junit.Before;
import org.junit.Test;
import repository.simple.GenreRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GenreRepositoryTest {
    private GenreRepository genreRepository;

    @Before
    public void setUp() {
        genreRepository = new GenreRepository();
    }

    @Test
    public void testCreateAndFindById() {
        GenreEntity genreEntity = new GenreEntity("Test Gen");
        genreRepository.create(genreEntity);

        GenreEntity genreFound = genreRepository.findById(genreEntity.getId());
        assertEquals(genreEntity, genreFound);
    }
    @Test
    public void testFindByName() {
        GenreEntity genre1 = new GenreEntity("Test Gen1");
        GenreEntity genre2 = new GenreEntity("Test Gen2");
        genreRepository.create(genre1);
        genreRepository.create(genre2);

        List<GenreEntity> books = genreRepository.findByName("Test");
        assertTrue(books.contains(genre1));
        assertTrue(books.contains(genre2));
    }
}
