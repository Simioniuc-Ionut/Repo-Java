package repository.Interfaces;


import model.GenreEntity;

import java.util.List;

public interface GenreRepositoryInterface {
    void create(GenreEntity a);
    GenreEntity findById(Integer id);
    List<GenreEntity> findByName(String namePattern);
}
