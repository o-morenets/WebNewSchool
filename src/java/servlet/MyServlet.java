package servlet;

import dao.DatabaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

public class MyServlet extends HttpServlet {

    private DatabaseDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DatabaseDAO();
    }

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
            dao.insertStudentIntoDB(name, surname);
        }

        if (action.equals("RemoveStudent")) {
            int id = parseInt(request.getParameter("id"));
            dao.deleteStudentFromDB(id);
        }
    }
}