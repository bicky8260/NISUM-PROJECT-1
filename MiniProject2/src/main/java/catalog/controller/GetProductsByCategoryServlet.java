package catalog.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalog.model.Product;
import com.google.gson.Gson;  // For JSON response, add gson library to your project

public class GetProductsByCategoryServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdStr = request.getParameter("categoryId");
        if (categoryIdStr == null || categoryIdStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("categoryId parameter is required");
            return;
        }

        try {
            int categoryId = Integer.parseInt(categoryIdStr);

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

                String sql = "SELECT p.ProductID, p.name, p.SKU, p.categoryName, p.Size, p.Price, p.Discount, p.DiscountPrice " +
                        "FROM Product p " +
                        "JOIN ProductCategory pc ON p.ProductID = pc.ProductID " +
                        "WHERE pc.CategoryID = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, categoryId);

                    try (ResultSet rs = ps.executeQuery()) {
                        List<Product> products = new ArrayList<>();

                        while (rs.next()) {
                            Product prod = new Product();
                            prod.setProductID(rs.getInt("ProductID"));
                            prod.setName(rs.getString("name"));
                            prod.setSku(rs.getString("SKU"));
                            prod.setCategoryName(rs.getString("categoryName"));
                            prod.setSize(rs.getString("Size"));
                            prod.setPrice(rs.getBigDecimal("Price"));
                            prod.setDiscount(rs.getInt("Discount"));
                            prod.setDiscountPrice(rs.getBigDecimal("DiscountPrice"));

                            products.add(prod);
                        }

                        // Convert list to JSON
                        String json = new Gson().toJson(products);

                        response.setContentType("application/json");
                        response.getWriter().write(json);
                    }
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid categoryId format");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }
}
