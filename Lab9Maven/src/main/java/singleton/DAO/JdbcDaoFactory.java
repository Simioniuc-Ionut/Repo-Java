package singleton.DAO;

import repository.Interfaces.AuthorRepositoryInterface;
import repository.Interfaces.BookRepositoryInterface;
import repository.Interfaces.GenreRepositoryInterface;
import repository.JDBC.JDBCAuthorRepository;
import repository.JDBC.JDBCBookRepository;
import repository.JDBC.JDBCGenreRepository;

public class JdbcDaoFactory extends AbstractDaoFactory {
    @Override
    public AuthorRepositoryInterface createAuthorRepository() {
        return new JDBCAuthorRepository();
    }
    @Override
    public GenreRepositoryInterface createGenreRepository() {
        return new JDBCGenreRepository();
    }

    @Override
    public BookRepositoryInterface createBookRepository() {
        return new JDBCBookRepository();
    }


    //...
}


