package repository.interfaces;

import model.AuthorEntity;

import java.util.List;

public interface AuthorRepositoryInterface {
    void create(AuthorEntity a);
    AuthorEntity findById(Integer id);
    List<AuthorEntity> findByName(String namePattern);
}
