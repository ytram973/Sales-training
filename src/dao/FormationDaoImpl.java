package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Formation;
import model.Mode;

public class FormationDaoImpl implements FormationDao {

    private static final String URL = "jdbc:mysql://localhost:4000/training_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "fms2025";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // =========================
    //  MAPPER COMMUN
    // =========================
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
