package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToCategoryServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdStr = request.getParameter("categoryId");
        String productIdStr = request.getParameter("productId");

        if (categoryIdStr == null || productIdStr == null || categoryIdStr.isEmpty() || productIdStr.isEmpty()) {
            response.getWriter().write("CategoryID and ProductID are required");
            return;
        }

        try {
            int categoryId = Integer.parseInt(categoryIdStr);
            int productId = Integer.parseInt(productIdStr);

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                // Check if the product and category exist (optional but recommended)
                String checkCategorySQL = "SELECT 1 FROM Category WHERE CategoryID = ?";
                String checkProductSQL = "SELECT 1 FROM Product WHERE ProductID = ?";

                try (PreparedStatement psCat = conn.prepareStatement(checkCategorySQL);
                     PreparedStatement psProd = conn.prepareStatement(checkProductSQL)) {

                    psCat.setInt(1, categoryId);
                    psProd.setInt(1, productId);

                    try (ResultSet rsCat = psCat.executeQuery();
                         ResultSet rsProd = psProd.executeQuery()) {

                        if (!rsCat.next()) {
                            response.getWriter().write("CategoryID does not exist");
                            return;
                        }
                        if (!rsProd.next()) {
                            response.getWriter().write("ProductID does not exist");
                            return;
                        }
                    }
                }

                // Insert into ProductCategory
                String insertSQL = "INSERT INTO ProductCategory (CategoryID, ProductID) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                    ps.setInt(1, categoryId);
                    ps.setInt(2, productId);
                    ps.executeUpdate();
                }

                response.getWriter().write("Product added to category successfully");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid number format for CategoryID or ProductID");
        } catch (SQLIntegrityConstraintViolationException e) {
            response.getWriter().write("This product is already associated with the category");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }
}
