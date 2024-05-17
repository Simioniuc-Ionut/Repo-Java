package repository.Interfaces;

import model.BookEntity;

import java.util.List;

public interface BookRepositoryInterface {
    void create(BookEntity a);
    BookEntity findById(Integer id);
    List<BookEntity> findByName(String namePattern);
}
