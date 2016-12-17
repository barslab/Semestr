package servlets.procedures;
import dao.DesiaseDaoImpl;
import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AllProcedureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        req.setAttribute("procedures", proceduresDao.findAll());
        req.setAttribute("text", null);
        System.out.println(proceduresDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
