package org.example;

import singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactorySingleton factorySingleton = EntityManagerFactorySingleton.getInstance();
        EntityManagerFactory emf = factorySingleton.getEntityManagerFactory();

        // Pot crea EntityManager-uri folosind EntityManagerFactory
        EntityManager em = emf.createEntityManager();

        //EntityManager ma ajuta ca sa interacționez cu baza de date

    }
}
