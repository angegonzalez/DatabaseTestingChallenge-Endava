package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import model.entity.Company;
import model.entity.Institution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.JPAManager;

public class InstitutionDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private final EntityTransaction entityTransaction;
    private final Logger logger = LogManager.getLogger(InstitutionDAO.class);

    public InstitutionDAO() {
        this.entityManager = JPAManager.getManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public Institution create(Institution institution) {
        entityTransaction.begin();
        entityManager.persist(institution);
        try {
            logger.info("Creating new institution in database with: " + institution.toString());
            entityTransaction.commit();
            return institution;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("Database was not updated");
            return null;
        }
    }
}
