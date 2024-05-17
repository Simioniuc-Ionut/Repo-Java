package singleton.DAO;

import repository.Interfaces.AuthorRepositoryInterface;
import repository.Interfaces.BookRepositoryInterface;
import repository.Interfaces.GenreRepositoryInterface;
import repository.JPA.JPAAuthorRepository;
import repository.JPA.JPABookRepository;
import repository.JPA.JPAGenreRepository;

public class JpaDaoFactory extends AbstractDaoFactory{
    @Override
    public AuthorRepositoryInterface createAuthorRepository() {
        return new JPAAuthorRepository();
    }
    @Override
    public GenreRepositoryInterface createGenreRepository() {
        return new JPAGenreRepository();
    }

    @Override
    public BookRepositoryInterface createBookRepository() {
        return new JPABookRepository();
    }
    //...
}
