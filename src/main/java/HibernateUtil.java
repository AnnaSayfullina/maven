import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

    public static EntityManager getEntityManager(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public static void closeEntityManagerFactory(){
        entityManagerFactory.close();
    }
}
