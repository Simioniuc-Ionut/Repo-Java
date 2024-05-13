package repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactorySingleton instance = new EntityManagerFactorySingleton();
    private final EntityManagerFactory emf;

    private EntityManagerFactorySingleton() {
        //initializez EntitYManager
        emf = Persistence.createEntityManagerFactory("ExamplePU");
    }

    public static EntityManagerFactorySingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactorySingleton();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
