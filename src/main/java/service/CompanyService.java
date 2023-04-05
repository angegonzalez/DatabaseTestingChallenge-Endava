package service;

import model.dao.CompanyDAO;
import model.entity.Company;

public class CompanyService {
    private final CompanyDAO companyDAO;

    public CompanyService(){
        this.companyDAO = new CompanyDAO();
    }

    public Company create(Company company) {
        return companyDAO.create(company);
    }
}
