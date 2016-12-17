package servlets.procedures;

import dao.DesiaseDaoImpl;
import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class ProcedureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl procedureDao = new ProceduresDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        int procedure_id = Integer.parseInt(path[path.length-1]);
        System.out.println(procedure_id);
        req.setAttribute("procedures", procedureDao.findProcedure(procedure_id));
        getServletContext().getRequestDispatcher("/JSP/procedure.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
