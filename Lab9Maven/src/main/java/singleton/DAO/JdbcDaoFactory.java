package singleton.dao;

import repository.interfaces.AuthorRepositoryInterface;
import repository.interfaces.BookRepositoryInterface;
import repository.interfaces.GenreRepositoryInterface;
import repository.jdbc.JDBCAuthorRepository;
import repository.jdbc.JDBCBookRepository;
import repository.jdbc.JDBCGenreRepository;

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


