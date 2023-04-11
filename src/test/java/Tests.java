import model.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
    private CompanyService companyService;
    private InstitutionService institutionService;
    private EmployeeService employeeService;
    private Logger logger;
    private File newEmployee;
    private File existingEmployees;
    private File existingEmployeeLastName;
    private File updateEmployee;

    @BeforeMethod
    public void beforeMethod() {
        companyService = new CompanyService();
        institutionService = new InstitutionService();
        employeeService = new EmployeeService();
        logger = LogManager.getLogger(Tests.class);

        newEmployee = new File("src/test/resources/data/newEmployee.json");
        existingEmployees = new File("src/test/resources/data/existingEmployees.json");
        existingEmployeeLastName = new File("src/test/resources/data/existingEmployeeLastName.json");
        updateEmployee = new File("src/test/resources/data/updateEmployee.json");
    }

    @Test
    public void shouldValidateAllEmployees() {
        List<Employee> existingEmployeesList = List.of(Objects.requireNonNull(getPOJO(existingEmployees, Employee[].class)));
        List<Employee> existingEmployeesDB = employeeService.getAllEmployees();

        assertThat(existingEmployeesList.size()).isEqualTo(existingEmployeesDB.size());

        int counterValidEmployees = 0;

        for (Employee employee : existingEmployeesList) {
            for (Employee employeeDB : existingEmployeesDB) {
                if (employee.getId() == employeeDB.getId()
                        && Objects.equals(employee.getFirstName(), employeeDB.getFirstName())) {
                    counterValidEmployees += 1;
                }
            }
        }

        assertThat(counterValidEmployees).isEqualTo(existingEmployeesDB.size());
    }

    @Test
    public void shouldValidateLastNames() {
        Employee existingEmployee = Objects.requireNonNull(getPOJO(existingEmployeeLastName, Employee.class));
        List<Employee> existingEmployeesLastNameDB = employeeService.getByLastName(existingEmployee.getLastName());

        for (Employee employee : existingEmployeesLastNameDB) {
            assertThat(employee.getLastName()).isEqualTo(existingEmployee.getLastName());
        }
    }

    @Test
    public void shouldCreateANewEmployee() {
        Employee employee = Objects.requireNonNull(getPOJO(newEmployee, Employee.class));

        companyService.create(employee.getCompany());
        institutionService.create(employee.getInstitution());
        Employee createdEmployee = employeeService.create(employee);

        assertThat(createdEmployee).usingRecursiveComparison().isEqualTo(employee);

    }

    @Test
    public void shouldUpdateAnEmployee() {
        Employee employee = Objects.requireNonNull(getPOJO(updateEmployee, Employee.class));
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        assertThat(updatedEmployee.getId()).isEqualTo(employee.getId());
        assertThat(updatedEmployee.getCompany()).usingRecursiveComparison().isEqualTo(employee.getCompany());
        assertThat(updatedEmployee.getInstitution()).usingRecursiveComparison().isEqualTo(employee.getInstitution());
    }

    @Test
    public void shouldDeleteAnEmployee() {
        Employee employee = Objects.requireNonNull(getPOJO(newEmployee, Employee.class));
        Employee removedEmployee = employeeService.deleteEmployee(employee.getId());

        assertThat(removedEmployee.getId()).isEqualTo(employee.getId());
        assertThat(removedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(removedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(removedEmployee.getCompany()).usingRecursiveComparison().isEqualTo(employee.getCompany());
        assertThat(removedEmployee.getInstitution()).usingRecursiveComparison().isEqualTo(employee.getInstitution());
    }

    private <T> T getPOJO(File json, Class<T> tclass) {
        try {
            return JSONHelper.jsonToPOJO(json, tclass);
        } catch (IOException e) {
            logger.error("Error while converting to POJO: " + e.getMessage());
            return null;
        }
    }

}
