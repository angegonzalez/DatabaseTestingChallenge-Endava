package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import model.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.JPAManager;

import java.util.List;

public class EmployeeDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private final EntityTransaction entityTransaction;
    private final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    public EmployeeDAO() {
        this.entityManager = JPAManager.getManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public Employee create(Employee employee) {
        entityTransaction.begin();
        entityManager.persist(employee);

        try {
            logger.info("Creating new employee in database with: " + employee.toString());
            entityTransaction.commit();
            return employee;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("Database was not updated");
            return null;
        }
    }

    public Employee getEmployeeByID(int id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getAllEmployees() {
        logger.info("Getting all employees ...");
        return entityManager.createQuery("FROM Employee", Employee.class).getResultList();
    }

    public List<Employee> getByLastName(String lastName) {
        logger.info("Getting all employees with last name: " + lastName);
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName).getResultList();
    }

    public Employee updateEmployee(Employee employee) {
        if (employee == null) {
            return null;
        }

        entityTransaction.begin();
        entityManager.merge(employee);

        try {
            entityTransaction.commit();
            logger.info("Updating employee with id: " + employee.getId() + " with the following info: " + employee);
            return employee;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("Database was not updated");
            throw new RuntimeException(e);
        }
    }

    public Employee deleteEmployee(int id) {
        Employee employee = getEmployeeByID(id);
        if (employee == null) {
            logger.error("Employee with id: " + id + "  not exist");
            return null;
        }
        logger.info("Deleting employee with id: " + id + " with info: " + employee);
        entityTransaction.begin();
        entityManager.remove(employee);
        try {
            entityTransaction.commit();
            logger.info("Employee with id: " + employee.getId() + " with the following info: " + employee + " was removed!");
            return employee;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("Database was not updated");
            throw new RuntimeException(e);
        }
    }

}
