package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddProductServlet reached!");
        String name = request.getParameter("name");
        String sku = request.getParameter("sku");
        String categoryName = request.getParameter("categoryName");
        String size = request.getParameter("size");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount");
        String discountPriceStr = request.getParameter("discountPrice");
        System.out.println("Paramater set!");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        ) {
            System.out.println("✅ Database connection successful!");
            double price = Double.parseDouble(priceStr);
            int discount = Integer.parseInt(discountStr);
            double discountPrice = Double.parseDouble(discountPriceStr);

            conn.setAutoCommit(false);

            String sqlProduct = "INSERT INTO Product (name, SKU, categoryName, Size, Price, Discount, DiscountPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
            System.out.println("after insert");
            try (PreparedStatement psProduct = conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS)) {
                psProduct.setString(1, name);
                psProduct.setString(2, sku);
                psProduct.setString(3, categoryName);
                psProduct.setString(4, size);
                psProduct.setDouble(5, price);
                psProduct.setInt(6, discount);
                psProduct.setDouble(7, discountPrice);
                System.out.println("after set");
                psProduct.executeUpdate();
                System.out.println("after execute");
                try (ResultSet rs = psProduct.getGeneratedKeys()) {
                    if (rs.next()) {
                        int productId = rs.getInt(1);

                        String sqlGetCategory = "SELECT CategoryID FROM Category WHERE CategoryName = ?";
                        try (PreparedStatement psCategory = conn.prepareStatement(sqlGetCategory)) {
                            psCategory.setString(1, categoryName);

                            try (ResultSet rsCategory = psCategory.executeQuery()) {
                                if (rsCategory.next()) {
                                    int categoryId = rsCategory.getInt("CategoryID");

                                    String sqlProdCat = "INSERT INTO ProductCategory (CategoryID, ProductID) VALUES (?, ?)";
                                    try (PreparedStatement psProdCat = conn.prepareStatement(sqlProdCat)) {
                                        psProdCat.setInt(1, categoryId);
                                        psProdCat.setInt(2, productId);
                                        psProdCat.executeUpdate();
                                        conn.commit();
                                        response.getWriter().write("Product added and linked to category successfully");
                                        return;
                                    }
                                } else {
                                    throw new SQLException("Category not found: " + categoryName);
                                }
                            }
                        }
                    } else {
                        throw new SQLException("Product ID not generated.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
