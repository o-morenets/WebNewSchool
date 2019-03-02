
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class MyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Добавлен</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Добавлнен в класс из " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //processRequest(request, response);

        String action = request.getParameter("actionname");

        if (action.equals("addstudent")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            try {
                insertstudent(name, surname);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (action.equals("RemoveStudent")) {
            int id = parseInt(request.getParameter("id"));

            try {
                delete(id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }


    public String insertstudent(String name, String surname) throws SQLException {

        String sql = "INSERT INTO student (name, surname) VALUES(?,?);";
        Connection con = null;
        PreparedStatement prep = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db_newnew?useTimezone=true&serverTimezone=GMT", "root", "root");
            prep = con.prepareStatement(sql);
            prep.setString(1, name);
            prep.setString(2, surname);

            prep.executeUpdate();
            prep.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "successfully added";
    }

    public String delete(int id) throws SQLException {

        String sql = "DELETE FROM student WHERE id = ?;";
        Connection con = null;
        PreparedStatement prep = null;

        try {
            Class.forName("com.mysql.jdbc."
                    + "Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db_newnew?useTimezone=true&serverTimezone=GMT", "root", "root");
            prep = con.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            prep.close();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "successfully delete";
    }

}


