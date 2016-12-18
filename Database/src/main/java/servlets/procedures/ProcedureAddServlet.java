package servlets.procedures;

import dao.*;
import factories.ConnectionFactory;
import models.Desiase;
import models.Drug;
import models.Procedures;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedureAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/JSP/procedure_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String name = req.getParameter("name");
        String recommendation = req.getParameter("recommendation");
        try {
            proceduresDao.putProcedures(new Procedures(name, recommendation));
        } catch (SQLException e) {
            req.setAttribute("error", "Такая процедура уже существует");
            getServletContext().getRequestDispatcher("/JSP/procedure_add.jsp").forward(req, resp);
        }
        req.setAttribute("text", "Процедура " + name + " успешно добавлена");
        req.setAttribute("procedures", proceduresDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
    }
}
