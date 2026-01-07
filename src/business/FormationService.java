package business;

import java.util.List;
import model.Formation;
import model.Mode;

public interface FormationService {
   
    List<Formation> getAllFormations();

    List<Formation> getFormationsByCategory(String category);

    List<Formation> getFormationsByMode(Mode mode);

    List<Formation> searchFormations(String keyword);
}
