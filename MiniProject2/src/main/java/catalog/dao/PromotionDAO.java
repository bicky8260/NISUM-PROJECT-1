package catalog.dao;

import catalog.model.Promotion;
import java.sql.*;

public class PromotionDAO {
    public static void insertPromotion(Promotion promotion) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/catalog_db2", "root", "root")){
            String sql = "INSERT INTO Promotions (PromoCode, PromoTypeId, Description, PromoAmount) VALUES (?, ?, ?, ?)";
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, promotion.getPromoCode());
            stmt.setInt(2, promotion.getPromoTypeId());
            stmt.setString(3, promotion.getDescription());
            stmt.setDouble(4, promotion.getPromoAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
