package model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "Employee")
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "idEmployee", nullable = false)
    private int id;

    @Column(name = "firstName", length = 45, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "phoneNumber", length = 45, nullable = false)
    private String phone;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "Salary", nullable = false)
    private String salary;

    @Column(name = "birthDate", nullable = false)
    private Date birth;

    @OneToOne
    @JoinColumn(name = "idCompany")
    private Company company;

    @OneToOne
    @JoinColumn(name = "idInstitution")
    private Institution institution;


    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.salary = builder.salary;
        this.birth = builder.birth;
        this.company = builder.company;
        this.institution = builder.institution;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", salary='").append(salary).append('\'');
        sb.append(", birth=").append(birth);
        sb.append(", company=").append(company);
        sb.append(", institution=").append(institution);
        sb.append('}');
        return sb.toString();
    }

    public static class EmployeeBuilder {
        private final int id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String salary;
        private Date birth;
        private Company company;
        private Institution institution;

        public EmployeeBuilder(int id) {
            this.id = id;
        }

        public EmployeeBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder setSalary(String salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder setBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public EmployeeBuilder setIdCompany(Company company) {
            this.company = company;
            return this;
        }

        public EmployeeBuilder setIdInstitution(Institution institution) {
            this.institution = institution;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
