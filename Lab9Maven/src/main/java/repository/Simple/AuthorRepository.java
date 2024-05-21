package repository.simple;

import model.AuthorEntity;
import repository.AbstractRepository;
import singleton.EntityManagerFactorySingleton;

public class AuthorRepository extends AbstractRepository<AuthorEntity,Integer> {
   // private final EntityManagerFactory entityManagerFactory;
  //  private static final Logger LOGGER = Logger.getLogger(AuthorRepository.class.getName());


    public AuthorRepository(){
        super(EntityManagerFactorySingleton.getInstance().getEntityManagerFactory(), AuthorEntity.class);
    }

    //partea de looger si de metode din AuthorRepository,implementarea fara AbstractRepository
    //    static {
//        try {
//            Handler consoleHandler = new ConsoleHandler();
//            LOGGER.addHandler(consoleHandler);
//
//            Handler fileHandler = new FileHandler("./app.log", true);
//            LOGGER.addHandler(fileHandler);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Eroare la inițializarea logger-ului.", e);
//        }
//    }

//    public AuthorRepository() {
//        entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
//    }

//    public void create(AuthorEntity author) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        long startTime = System.currentTimeMillis();
//        em.persist(author);
//        long endTime = System.currentTimeMillis();
//        LOGGER.log(Level.INFO, "Timpul de execuție pentru persistența autorului: " + (endTime - startTime) + "ms");
//        em.getTransaction().commit();
//        em.close();
//    }
//
//    public AuthorEntity findById(int id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        long startTime = System.currentTimeMillis();
//        AuthorEntity author = em.find(AuthorEntity.class, id);
//        long endTime = System.currentTimeMillis();
//        LOGGER.log(Level.INFO, "Timpul de execuție pentru căutarea autorului după ID: " + (endTime - startTime) + "ms");
//        em.close();
//        return author;
//    }
//
//    public List<AuthorEntity> findByName(String namePattern) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        Query query = em.createNamedQuery("Author.findByName");
//        query.setParameter("name", "%" + namePattern + "%");
//        long startTime = System.currentTimeMillis();
//        List<AuthorEntity> authors = query.getResultList();
//        long endTime = System.currentTimeMillis();
//        LOGGER.log(Level.INFO, "Timpul de execuție pentru căutarea autorilor după nume: " + (endTime - startTime) + "ms");
//        em.close();
//        return authors;
//    }
}
