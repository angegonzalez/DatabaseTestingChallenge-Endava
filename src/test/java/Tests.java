
import model.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.CompanyService;
import service.EmployeeService;
import service.InstitutionService;
import utils.JSONHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Tests {

    private final CompanyService companyService = new CompanyService();
    private final InstitutionService institutionService = new InstitutionService();
    private final EmployeeService employeeService = new EmployeeService();
    private final Logger logger = LogManager.getLogger(Tests.class);
    private File newCompanyData;
    private File existingEmployees;
    private File existingEmployeeLastName;
    private File updateEmployee;

    @BeforeMethod
    public void beforeMethod() {
        newCompanyData = new File("src/test/resources/data/newEmployee.json");
        existingEmployees = new File("src/test/resources/data/existingEmployees.json");
        existingEmployeeLastName = new File("src/test/resources/data/existingEmployeeLastName.json");
        updateEmployee = new File("src/test/resources/data/updateEmployee.json");
    }

    @Test
    public void shouldValidateAllEmployees() {
        List<Employee> existingEmployeesList;
        try {
            existingEmployeesList = List.of(JSONHelper.jsonToPOJO(existingEmployees, Employee[].class));
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return;
        }

        List<Employee> existingEmployeesDB = employeeService.getAllEmployees();

        Assert.assertEquals(existingEmployeesList.size(), existingEmployeesDB.size());
        int counterValidEmployees = 0;

        for (Employee employee : existingEmployeesList) {
            for (Employee employeeDB : existingEmployeesDB) {
                if (employee.getId() == employeeDB.getId()
                        && Objects.equals(employee.getFirstName(), employeeDB.getFirstName())) {
                    counterValidEmployees += 1;
                }
            }
        }

        Assert.assertEquals(counterValidEmployees, existingEmployeesDB.size());
    }

    @Test
    public void shouldValidateLastNames(){
        Employee existingEmployee;
        try {
            existingEmployee = JSONHelper.jsonToPOJO(existingEmployeeLastName, Employee.class);
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return;
        }

        List<Employee> existingEmployeesLastNameDB = employeeService.getByLastName(existingEmployee.getLastName());

        for(Employee employee: existingEmployeesLastNameDB){
            Assert.assertEquals(employee.getLastName(), existingEmployee.getLastName());
        }
    }

    @Test
    public void shouldCreateANewEmployee() {
        Employee employee;
        try {
            employee = JSONHelper.jsonToPOJO(newCompanyData, Employee.class);
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return;
        }

        companyService.create(employee.getCompany());
        institutionService.create(employee.getInstitution());
        Employee createdEmployee = employeeService.create(employee);

        Assert.assertEquals(createdEmployee, employee);
    }

    @Test
    public void shouldUpdateAnEmployee(){
        Employee employee;
        try {
            employee = JSONHelper.jsonToPOJO(updateEmployee, Employee.class);
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return;
        }
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        Assert.assertEquals(updatedEmployee.getId(), employee.getId());
        Assert.assertEquals(updatedEmployee.getCompany(), employee.getCompany());
        Assert.assertEquals(updatedEmployee.getInstitution(), employee.getInstitution());
    }
    @Test
    public void shouldDeleteAnEmployee(){
        Employee employee;
        try {
            employee = JSONHelper.jsonToPOJO(updateEmployee, Employee.class);
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return;
        }
        Employee removedEmployee = employeeService.deleteEmployee(employee.getId());

        Assert.assertEquals(removedEmployee.getId(), employee.getId());
        Assert.assertEquals(removedEmployee.getFirstName(), employee.getFirstName());
        Assert.assertEquals(removedEmployee.getLastName(), employee.getLastName());
        Assert.assertEquals(removedEmployee.getCompany(), employee.getCompany());
        Assert.assertEquals(removedEmployee.getInstitution(), employee.getInstitution());
    }

}
