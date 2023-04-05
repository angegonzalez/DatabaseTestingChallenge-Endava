package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAManager {
    private static final String PERSISTENCE_UNIT_NAME = "persistence";

    public static EntityManager getManager(){
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return managerFactory.createEntityManager();
    }

}
