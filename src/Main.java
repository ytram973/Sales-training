import java.util.List;
import java.util.Scanner;

import business.FormationService;
import business.FormationServiceImpl;
import model.Formation;
import model.Mode;

public class Main {

    public static void main(String[] args) {

        FormationService formationService = new FormationServiceImpl();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("=== MENU FORMATIONS ===");
            System.out.println("1. Afficher toutes les formations");
            System.out.println("2. Filtrer par catégorie");
            System.out.println("3. Filtrer par mode (PRESENTIEL/DISTANCIEL)");
            System.out.println("4. Rechercher par mot-clé");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Formation> all = formationService.getAllFormations();
                    afficherFormations(all);
                    break;
                case "2":
                    System.out.print("Catégorie : ");
                    String category = scanner.nextLine();
                    afficherFormations(formationService.getFormationsByCategory(category));
                    break;
                case "3":
                    System.out.print("Mode (PRESENTIEL/DISTANCIEL) : ");
                    String modeStr = scanner.nextLine().toUpperCase();
                    Mode mode = Mode.valueOf(modeStr);
                    afficherFormations(formationService.getFormationsByMode(mode));
                    break;
                case "4":
                    System.out.print("Mot clé : ");
                    String keyword = scanner.nextLine();
                    afficherFormations(formationService.searchFormations(keyword));
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        scanner.close();
        System.out.println("Au revoir !");
    }

    private static void afficherFormations(List<Formation> formations) {
        if (formations.isEmpty()) {
            System.out.println("Aucune formation trouvée.");
        } else {
            for (Formation f : formations) {
                System.out.println(f);
            }
        }
        System.out.println("-------------------------");
    }
}
