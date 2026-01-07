package dao;

import java.util.List;
import model.Formation;
import model.Mode;


public interface FormationDao {
    List<Formation> findAll();
    List<Formation> findByCategory(String category);
    List<Formation> findByMode(Mode mode);
    List<Formation> findByKeyword(String keyword);
}
