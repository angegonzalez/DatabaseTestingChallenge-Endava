package service;

import model.dao.InstitutionDAO;
import model.entity.Institution;

public class InstitutionService {
    private final InstitutionDAO institutionDAO;

    public InstitutionService() {
        this.institutionDAO = new InstitutionDAO();
    }

    public Institution create(Institution institution) {
        return institutionDAO.create(institution);
    }
}
