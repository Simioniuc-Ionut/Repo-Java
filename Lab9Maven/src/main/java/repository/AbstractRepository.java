package repository;


import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import java.util.logging.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractRepository<T, ID extends Serializable> {

    protected final EntityManagerFactory entityManagerFactory;
    private final Class<T> entityClass;
    private static final Logger LOGGER = Logger.getLogger(AbstractRepository.class.getName());

    static {
        try {
            Handler consoleHandler = new ConsoleHandler();
            LOGGER.addHandler(consoleHandler);

            Handler fileHandler = new FileHandler("./app.log", true);
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Eroare la inițializarea logger-ului.", e);
        }
    }

    public AbstractRepository(EntityManagerFactory entityManagerFactory, Class<T> entityClass) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        long startTime = System.currentTimeMillis();
        em.persist(entity);
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru persistența entității: " + (endTime - startTime) + "ms");
        em.getTransaction().commit();
        em.close();
    }
    public T findById(ID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO,"Timpul de executie pentru cautare dupa ID :" + (endTime - startTime) + " ms");
        return entityManager.find(entityClass, id);
    }

    public List<T> findByName(String namePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        cq.where(cb.like(rootEntry.get("name"), "%" + namePattern + "%"));
        long startTime = System.currentTimeMillis();
        List<T> entities = entityManager.createQuery(cq).getResultList();
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru căutarea după nume: " + (endTime - startTime) + "ms");
        entityManager.close();
        return entities;

    }


    public List<T> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        long startTime = System.currentTimeMillis();
        List<T> result = entityManager.createQuery(all).getResultList();
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru găsirea tuturor entităților: " + (endTime - startTime) + "ms");
        return result;

    }

    public T save(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        long startTime = System.currentTimeMillis();
        entityManager.persist(entity);
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru salvarea entității: " + (endTime - startTime) + "ms");
        entityManager.getTransaction().commit();
        return entity;
    }

    public T update(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        long startTime = System.currentTimeMillis();
        T mergedEntity = entityManager.merge(entity);
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru actualizarea entității: " + (endTime - startTime) + "ms");
        entityManager.getTransaction().commit();
        return mergedEntity;
    }

    public void delete(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        long startTime = System.currentTimeMillis();
        entityManager.remove(entity);
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru ștergerea entității: " + (endTime - startTime) + "ms");
        entityManager.getTransaction().commit();
    }

    public void deleteById(ID id) {
        long startTime = System.currentTimeMillis();
        T entity = findById(id);
        if (entity != null) {
            delete(entity);
        }
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Timpul de execuție pentru ștergerea entității după ID: " + (endTime - startTime) + "ms");
    }
}
