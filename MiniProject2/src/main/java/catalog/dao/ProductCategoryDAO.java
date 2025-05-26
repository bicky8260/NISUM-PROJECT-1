package catalog.dao;

import catalog.model.ProductCategory;
import java.sql.*;

public class ProductCategoryDAO {
    public static void insertProductCategory(ProductCategory product) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog_db2", "root", "root")) {
            String sql = "INSERT INTO ProductCategory (CategoryID, ProductID, SKU, Price, Discount, DiscountPrice) VALUES (?, ?, ?, ?, ?, ?)";
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getCategoryID());
            stmt.setInt(2, product.getProductID());
            stmt.setString(3, product.getSku());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getDiscount());
            stmt.setDouble(6, product.getDiscountPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
