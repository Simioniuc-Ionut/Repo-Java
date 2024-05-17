package repository.JPA;


import model.GenreEntity;
import repository.AbstractRepository;
import repository.Interfaces.GenreRepositoryInterface;
import singleton.EntityManagerFactorySingleton;

public class JPAGenreRepository extends AbstractRepository<GenreEntity,Integer> implements GenreRepositoryInterface {
    public JPAGenreRepository() {
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), GenreEntity.class);
    }
}
