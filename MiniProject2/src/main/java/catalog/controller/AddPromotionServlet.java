package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPromotionServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String promoCode = request.getParameter("promoCode");
        String promoTypeIdStr = request.getParameter("promoTypeId");
        String promoType = request.getParameter("promoType");
        String description = request.getParameter("description");
        String promoAmountStr = request.getParameter("promoAmount");

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO Promotions (PromoCode, PromoTypeId, PromoType, Description, PromoAmount) VALUES (?, ?, ?, ?, ?)"
                );
        ) {
            int promoTypeId = Integer.parseInt(promoTypeIdStr);
            double promoAmount = Double.parseDouble(promoAmountStr);

            ps.setString(1, promoCode);
            ps.setInt(2, promoTypeId);
            ps.setString(3, promoType);
            ps.setString(4, description);
            ps.setDouble(5, promoAmount);

            int rows = ps.executeUpdate();

            response.getWriter().write(rows > 0 ? "Promotion added successfully" : "Failed to add promotion");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
