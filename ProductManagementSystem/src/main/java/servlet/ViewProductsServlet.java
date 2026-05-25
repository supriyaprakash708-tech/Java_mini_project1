package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewProducts")
public class ViewProductsServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<h2>All Products</h2>");

        out.println("<table border='1' cellpadding='10'>");

        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Price</th>");
        out.println("<th>Quantity</th>");
        out.println("</tr>");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/productdb",
                    "root",
                    "root");

            PreparedStatement ps = con.prepareStatement(
                    "select * from products");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getDouble(3) + "</td>");
                out.println("<td>" + rs.getInt(4) + "</td>");

                out.println("</tr>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");

        out.println("<br>");

        out.println("<a href='index.jsp'>Home</a>");

        out.println("</body></html>");
    }

}
