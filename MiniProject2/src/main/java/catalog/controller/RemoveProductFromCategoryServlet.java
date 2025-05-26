package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveProductFromCategoryServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String productIdStr = request.getParameter("productId");
        if (productIdStr == null || productIdStr.isEmpty()) {
            response.getWriter().write("Product ID is required");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                // First delete from ProductCategory (if exists) to avoid FK constraints
                String deleteProdCatSQL = "DELETE FROM ProductCategory WHERE ProductID = ?";
                try (PreparedStatement ps = conn.prepareStatement(deleteProdCatSQL)) {
                    ps.setInt(1, productId);
                    ps.executeUpdate();
                }

                // Now delete from Product
                String deleteProductSQL = "DELETE FROM Product WHERE ProductID = ?";
                try (PreparedStatement ps = conn.prepareStatement(deleteProductSQL)) {
                    ps.setInt(1, productId);
                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        response.getWriter().write("Product removed successfully");
                    } else {
                        response.getWriter().write("No product found with ID: " + productId);
                    }
                }

            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid Product ID format");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error removing product: " + e.getMessage());
        }
    }
}
