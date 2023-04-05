package model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Company")
@Table(name = "Company")
public class Company {

    @Id
    @Column(name = "idCompany", nullable = false)
    private int id;

    @Column(name = "coName", length = 45, nullable = false)
    private String name;

    @Column(name = "phoneNumber", length = 45, nullable = false)
    private String phone;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "address", length = 45)
    private String address;


    private Company(CompanyBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.address = builder.address;
    }

    public Company() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static class CompanyBuilder {
        private final int id;
        private String name;
        private String phone;
        private String email;
        private String address;

        public CompanyBuilder(int id) {
            this.id = id;
        }

        public CompanyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CompanyBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CompanyBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Company build() {
            return new Company(this);
        }

    }
}
