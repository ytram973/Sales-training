package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Formation;
import model.Mode;

/**
 * Implémentation JDBC du DAO {@link FormationDao}.
 * <p>
 * Cette classe est responsable de l'accès aux données de la table {@code formation}
 * dans la base de données MariaDB.
 * Elle permet de récupérer les formations selon différents critères :
 * - toutes les formations
 * - par catégorie
 * - par mode (présentiel / distanciel)
 * - par mot-clé (nom ou description)
 * </p>
 */
public class FormationDaoImpl implements FormationDao {

    private static final String URL = "jdbc:mysql://localhost:4000/training_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "fms2025";

    /**
     * Crée et retourne une connexion JDBC vers la base de données.
     *
     * @return une connexion JDBC valide
     * @throws SQLException si la connexion échoue
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Transforme une ligne du {@link ResultSet} en objet {@link Formation}.
     * <p>
     * Cette méthode centralise le mapping entre la base de données
     * et l'entité métier afin d'éviter la duplication de code.
     * </p>
     *
     * @param rs le ResultSet positionné sur une ligne
     * @return une instance de Formation
     * @throws SQLException si une erreur survient lors de la lecture
     */
    private Formation mapRow(ResultSet rs) throws SQLException {
        return new Formation(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("duration_days"),
                Mode.valueOf(rs.getString("mode")),
                rs.getDouble("price"),
                rs.getString("category")
        );
    }

    /**
     * Récupère toutes les formations disponibles.
     *
     * @return la liste de toutes les formations
     */
    @Override
    public List<Formation> findAll() {
        List<Formation> formations = new ArrayList<>();

        String sql = "SELECT id, name, description, duration_days, mode, price, category FROM formation";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                formations.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error (findAll): " + e.getMessage(), e);
        }

        return formations;
    }

    /**
     * Récupère les formations appartenant à une catégorie donnée.
     * La recherche est insensible à la casse.
     *
     * @param category la catégorie recherchée
     * @return la liste des formations correspondantes
     */
    @Override
    public List<Formation> findByCategory(String category) {
        List<Formation> formations = new ArrayList<>();

        String sql = "SELECT id, name, description, duration_days, mode, price, category " +
                     "FROM formation " +
                     "WHERE LOWER(category) = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, category.toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    formations.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error (findByCategory): " + e.getMessage(), e);
        }

        return formations;
    }

    /**
     * Récupère les formations selon leur mode (présentiel ou distanciel).
     *
     * @param mode le mode de formation
     * @return la liste des formations correspondantes
     */
    @Override
    public List<Formation> findByMode(Mode mode) {
        List<Formation> formations = new ArrayList<>();

        String sql = "SELECT id, name, description, duration_days, mode, price, category " +
                     "FROM formation " +
                     "WHERE mode = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, mode.name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    formations.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error (findByMode): " + e.getMessage(), e);
        }

        return formations;
    }

    /**
     * Recherche les formations dont le nom ou la description
     * contient le mot-clé fourni.
     * La recherche est insensible à la casse.
     *
     * @param keyword le mot-clé à rechercher
     * @return la liste des formations correspondantes
     */
    @Override
    public List<Formation> findByKeyword(String keyword) {
        List<Formation> formations = new ArrayList<>();

        String sql = "SELECT id, name, description, duration_days, mode, price, category " +
                     "FROM formation " +
                     "WHERE LOWER(name) LIKE ? " +
                     "   OR LOWER(description) LIKE ?";

        String pattern = "%" + keyword.toLowerCase() + "%";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pattern);
            stmt.setString(2, pattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    formations.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error (findByKeyword): " + e.getMessage(), e);
        }

        return formations;
    }
}
