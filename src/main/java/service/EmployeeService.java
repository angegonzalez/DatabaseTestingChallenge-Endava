package service;

import model.dao.EmployeeDAO;
import model.entity.Employee;

import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public Employee create(Employee employee) {
        return employeeDAO.create(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public List<Employee> getByLastName(String lastName) {
        return employeeDAO.getByLastName(lastName);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    public Employee deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }
}
