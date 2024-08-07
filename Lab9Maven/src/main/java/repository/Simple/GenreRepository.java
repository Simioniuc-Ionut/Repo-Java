package repository.simple;

import model.GenreEntity;
import repository.AbstractRepository;
import singleton.EntityManagerFactorySingleton;


public class GenreRepository extends AbstractRepository<GenreEntity,Integer> {
    public GenreRepository() {
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), GenreEntity.class);
    }


    //    private final EntityManagerFactory entityManagerFactory;
//
//    public GenreRepository(){
//        this.entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
//    }
//
//    public void create(GenreEntity genre) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(genre);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public GenreEntity findById(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        GenreEntity genre = entityManager.find(GenreEntity.class, id);
//        entityManager.close();
//        return genre;
//    }
//
//    public List<GenreEntity> findByName(String name) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNamedQuery("Genre.findByName");
//        query.setParameter("name", "%" + name + "%");
//        List<GenreEntity> genre = query.getResultList();
//        entityManager.close();
//        return genre;
//    }
//
//    public List<GenreEntity> findAll() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNamedQuery("Genre.findAll");
//        List<GenreEntity> genres = query.getResultList();
//        entityManager.close();
//        return  genres;
//    }

}
