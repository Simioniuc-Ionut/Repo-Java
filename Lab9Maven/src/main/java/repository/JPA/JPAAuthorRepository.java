package repository.JPA;

import model.AuthorEntity;
import repository.AbstractRepository;
import repository.Interfaces.AuthorRepositoryInterface;
import singleton.EntityManagerFactorySingleton;

public class JPAAuthorRepository extends AbstractRepository<AuthorEntity,Integer> implements AuthorRepositoryInterface {
    public JPAAuthorRepository() {
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), AuthorEntity.class);
    }
}
