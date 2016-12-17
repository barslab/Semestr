package servlets.procedures;

import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class ProcedureEditServlet extends HttpServlet {
    private int procedure_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        procedure_id = Integer.parseInt(path[path.length-1]);
        System.out.println(procedure_id);
        req.setAttribute("procedure", proceduresDao.findProcedure(procedure_id));
        getServletContext().getRequestDispatcher("/JSP/edit_procedure.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String name = req.getParameter("name");
        String recommendations = req.getParameter("recommendations");
        proceduresDao.changeProcedures(procedure_id, name, recommendations);
        req.setAttribute("text", "Процедура успешно изменена");
        req.setAttribute("procedures", proceduresDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
    }
}