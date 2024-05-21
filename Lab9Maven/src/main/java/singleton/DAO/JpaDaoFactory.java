package singleton.dao;

import repository.interfaces.AuthorRepositoryInterface;
import repository.interfaces.BookRepositoryInterface;
import repository.interfaces.GenreRepositoryInterface;
import repository.jpa.JPAAuthorRepository;
import repository.jpa.JPABookRepository;
import repository.jpa.JPAGenreRepository;

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
