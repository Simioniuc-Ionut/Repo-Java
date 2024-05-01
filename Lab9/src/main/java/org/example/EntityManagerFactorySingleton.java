package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactorySingleton instance;
    private EntityManagerFactory emf;

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
