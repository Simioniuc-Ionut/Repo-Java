import model.AuthorEntity;
import model.BookEntity;
import model.GenreEntity;
import org.junit.Before;
import org.junit.Test;
import repository.Interfaces.AuthorRepositoryInterface;
import repository.Interfaces.BookRepositoryInterface;
import repository.Interfaces.GenreRepositoryInterface;

import java.util.List;

public class DAOFactoryProviderTest {
    private DaoFactoryProvider daoFactoryProvider;

    @Before
    public void setUp() {
        daoFactoryProvider = new DaoFactoryProvider();
    }

    @Test
    public void CreateAndFindByNameAuthorDAOTest() {
        AuthorRepositoryInterface authorRepository = DaoFactoryProvider.getDaoFactory().createAuthorRepository();
        authorRepository.create(new AuthorEntity("Nume Autor"));
        List<AuthorEntity> foundAuthor = authorRepository.findByName("Nume Autor");
        System.out.println(foundAuthor);

    }


    @Test
    public void CreateAndFindByNameGenreDAOTest(){
        GenreRepositoryInterface genreRepository = DaoFactoryProvider.getDaoFactory().createGenreRepository();
        genreRepository.create(new GenreEntity("Nume Gen"));
        List<GenreEntity> foundgendre = genreRepository.findByName("Nume Gen");
        System.out.println(foundgendre);
    }

    @Test
    public void CreateAndFindByNameBookDAOTest(){
        BookRepositoryInterface bookRepository = DaoFactoryProvider.getDaoFactory().createBookRepository();
        bookRepository.create(new BookEntity(1000,"Romanian",0,"Nume Book"));
        List<BookEntity> foundBook = bookRepository.findByName("Nume Book");
        System.out.println(foundBook);
    }
}
