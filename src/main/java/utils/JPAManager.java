package utils;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JPAManager {
    private static final String PERSISTENCE_UNIT_NAME = "persistence";

    public static EntityManager getManager() {
        Map<String, String> properties = setProperties();
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
        return managerFactory.createEntityManager();
    }

    private static Map<String, String> setProperties() {
        Dotenv dotenv = Dotenv.configure().load();
        Map<String, String> properties = new HashMap<>();

        properties.put("javax.persistence.jdbc.url", dotenv.get("DB_URL"));
        properties.put("javax.persistence.jdbc.user", dotenv.get("DB_USERNAME"));
        properties.put("javax.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));

        return properties;
    }


}
