package repository.JPA;

import model.BookEntity;
import repository.AbstractRepository;
import repository.Interfaces.BookRepositoryInterface;
import singleton.EntityManagerFactorySingleton;

public class JPABookRepository extends AbstractRepository<BookEntity,Integer> implements BookRepositoryInterface {

    public JPABookRepository() {
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), BookEntity.class);
    }
}
