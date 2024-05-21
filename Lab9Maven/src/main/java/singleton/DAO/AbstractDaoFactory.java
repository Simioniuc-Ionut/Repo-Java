package singleton.dao;

import repository.interfaces.AuthorRepositoryInterface;
import repository.interfaces.BookRepositoryInterface;
import repository.interfaces.GenreRepositoryInterface;

public abstract class AbstractDaoFactory {
    public abstract AuthorRepositoryInterface createAuthorRepository();

    public abstract GenreRepositoryInterface createGenreRepository();

    public abstract BookRepositoryInterface createBookRepository();
    //...
}
