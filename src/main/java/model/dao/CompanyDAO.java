package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import model.entity.Company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.JPAManager;

public class CompanyDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private final EntityTransaction entityTransaction;
    private final Logger logger = LogManager.getLogger(CompanyDAO.class);

    public CompanyDAO() {
        entityManager = JPAManager.getManager();
        entityTransaction = entityManager.getTransaction();
    }

    public Company create(Company company) {
        entityTransaction.begin();
        entityManager.persist(company);

        try {
            logger.info("Creating new company in database with: " + company.toString());
            entityTransaction.commit();
            return company;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("Database was not updated");
            return null;
        }
    }
}
