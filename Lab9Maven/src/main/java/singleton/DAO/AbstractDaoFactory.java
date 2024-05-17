package singleton.DAO;

import repository.Interfaces.AuthorRepositoryInterface;
import repository.Interfaces.BookRepositoryInterface;
import repository.Interfaces.GenreRepositoryInterface;

public abstract class AbstractDaoFactory {
    public abstract AuthorRepositoryInterface createAuthorRepository();

    public abstract GenreRepositoryInterface createGenreRepository();

    public abstract BookRepositoryInterface createBookRepository();
    //...
}
