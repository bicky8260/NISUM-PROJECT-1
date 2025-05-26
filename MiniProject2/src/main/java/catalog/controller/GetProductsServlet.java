package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductsServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sql = "SELECT ProductID, name, SKU, categoryName, Size, Price, Discount, DiscountPrice FROM Product";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            StringBuilder sb = new StringBuilder();
            sb.append("<table border='1'>");
            sb.append("<tr>")
                    .append("<th>ProductID</th>")
                    .append("<th>Name</th>")
                    .append("<th>SKU</th>")
                    .append("<th>Category</th>")
                    .append("<th>Size</th>")
                    .append("<th>Price ($)</th>")
                    .append("<th>Discount (%)</th>")
                    .append("<th>Discount Amount ($)</th>")
                    .append("</tr>");

            while (rs.next()) {
                sb.append("<tr>");
                sb.append("<td>").append(rs.getInt("ProductID")).append("</td>");
                sb.append("<td>").append(rs.getString("name")).append("</td>");
                sb.append("<td>").append(rs.getString("SKU")).append("</td>");
                sb.append("<td>").append(rs.getString("categoryName")).append("</td>");
                sb.append("<td>").append(rs.getString("Size")).append("</td>");
                sb.append("<td>").append(rs.getDouble("Price")).append("</td>");
                sb.append("<td>").append(rs.getInt("Discount")).append("</td>");
                sb.append("<td>").append(rs.getDouble("DiscountPrice")).append("</td>");
                sb.append("</tr>");
            }

            sb.append("</table>");

            response.setContentType("text/html");
            response.getWriter().write(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
