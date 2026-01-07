package business;

import java.util.List;

import dao.FormationDao;
import dao.FormationDaoImpl;
import model.Formation;
import model.Mode;

public class FormationServiceImpl implements FormationService {

    private final FormationDao formationDao;

    public FormationServiceImpl() {
        this.formationDao = new FormationDaoImpl();
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationDao.findAll();
    }

    @Override
    public List<Formation> getFormationsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return formationDao.findAll();
        }
        return formationDao.findByCategory(category.trim());
    }

    @Override
    public List<Formation> getFormationsByMode(Mode mode) {
        return formationDao.findByMode(mode);
    }

    @Override
    public List<Formation> searchFormations(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return formationDao.findAll();
        }
        return formationDao.findByKeyword(keyword.trim());
    }
}
