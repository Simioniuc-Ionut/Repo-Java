package repository;

import model.AuthorEntity;

import model.BookEntity;
import singleton.EntityManagerFactorySingleton;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class BookRepository extends AbstractRepository<BookEntity,Integer> {

   // private final EntityManagerFactory entityManagerFactory;

    public BookRepository(){
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), BookEntity.class);
    }
//    public BookRepository(){
//        entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
//    }
//    public void create(BookEntity book) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(book);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public BookEntity findById(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        BookEntity book = entityManager.find(BookEntity.class, id);
//        entityManager.close();
//        return book;
//    }
//
//    public List<BookEntity> findByTitle(String title) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Query query = entityManager.createNamedQuery("Book.findByTitle");
//        query.setParameter("title","%" + title + "%");
//        List<BookEntity> books = query.getResultList();
//        entityManager.close();
//        return books;
//    }
}
