
import model.BookEntity;
import org.junit.Before;
import org.junit.Test;
import repository.simple.BookRepository;

import java.util.List;

import static org.junit.Assert.*;

public class BookRepositoryTest {
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    public void testCreateAndFindById() {
        BookEntity bookEntity = new BookEntity("Test Title" , 9,"En_Test",2003,6002);
        bookRepository.create(bookEntity);

        BookEntity foundBook = bookRepository.findById(bookEntity.getId());
        assertEquals(bookEntity, foundBook);
    }
    @Test
    public void testFindByName() {
        BookEntity book1 = new BookEntity("Test Title1" , 9,"En_Test",2003,6111);
        BookEntity book2 = new BookEntity("Test Title2" , 9,"En_Test",2003,6112);
        bookRepository.create(book1);
        bookRepository.create(book2);

        List<BookEntity> books = bookRepository.findByName("Test");
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));

    }
}
