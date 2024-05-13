package repository;

import model.GenreEntity;
import model.PublishingHouseEntity;
import singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;



public class PublishingHouseRepository extends AbstractRepository<PublishingHouseEntity,Integer>{

    public PublishingHouseRepository(){
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(),PublishingHouseEntity.class );
    }
    //    private final EntityManagerFactory entityManagerFactory;
//
//    public PublishingHouseRepository(){
//        this.entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
//    }
//    public void create(PublishingHouseEntity publish) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(publish);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public PublishingHouseEntity findById(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        PublishingHouseEntity publish = entityManager.find(PublishingHouseEntity.class, id);
//        entityManager.close();
//        return publish;
//    }
//
//    public List<PublishingHouseEntity> findByName(String name) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNamedQuery("Publish.findByLabelName");
//        query.setParameter("labelName", "%" + name + "%");
//        List<PublishingHouseEntity> genre = query.getResultList();
//        entityManager.close();
//        return genre;
//    }
//
//    public List<PublishingHouseEntity> findAll() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNamedQuery("Publish.findAll");
//        List<PublishingHouseEntity> genres = query.getResultList();
//        entityManager.close();
//        return  genres;
//    }
}
