package catalog.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCategoriesServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog_db2";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sql = "SELECT CategoryID, CategoryName FROM Category";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            StringBuilder sb = new StringBuilder();
            sb.append("<table border='1'><tr><th>CategoryID</th><th>CategoryName</th></tr>");
            while (rs.next()) {
                sb.append("<tr>");
                sb.append("<td>").append(rs.getInt("CategoryID")).append("</td>");
                sb.append("<td>").append(rs.getString("CategoryName")).append("</td>");
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
