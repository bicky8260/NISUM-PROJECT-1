package catalog.dao;

import catalog.model.Category;
import java.sql.*;

public class CategoryDAO {
    public static boolean insertCategory(int id, String name) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog_db2", "root", "root")) {
            String sql = "INSERT INTO Category (CategoryID, CategoryName) VALUES (?, ?)";
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
