package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Institution")
@Table(name = "Institution")
public class Institution {
    @Id
    @Column(name = "idInstitution", nullable = false)
    private int id;
    @Column(name = "insName", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone", nullable = false)
    private String phone;

    public Institution() {

    }

    private Institution(InstitutionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Institution{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class InstitutionBuilder {
        private final int id;
        private String name;
        private String address;
        private String phone;

        public InstitutionBuilder(int id) {
            this.id = id;
        }

        public InstitutionBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public InstitutionBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public InstitutionBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Institution build() {
            return new Institution(this);
        }
    }
}
