package servlets.procedures;

import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;
import models.Procedures;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedureEditServlet extends HttpServlet {
    private int procedure_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            procedure_id = Integer.parseInt(path[path.length - 1]);
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
        System.out.println(procedure_id);
        if(proceduresDao.findProcedure(procedure_id)!=null) {
            req.setAttribute("procedures", proceduresDao.findProcedure(procedure_id));
            getServletContext().getRequestDispatcher("/JSP/edit_procedure.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у процедуры");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        req.setCharacterEncoding("UTF-8");
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String name = req.getParameter("name");
        if(name.length()>45) {
            req.setAttribute("error", "Название процедуры не может превышать 45 символов");
            getServletContext().getRequestDispatcher("/JSP/edit_procedure.jsp").forward(req, resp);
        }
        else {
            String recommendation = req.getParameter("recommendation");
            try {
                proceduresDao.changeProcedures(procedure_id, name, recommendation);
                req.setAttribute("text", "Процедура успешно изменена");
                req.setAttribute("procedures", proceduresDao.findAll());
                getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("error", "Такая процедура уже существует");
                req.setAttribute("procedures", proceduresDao.findProcedure(procedure_id));
                getServletContext().getRequestDispatcher("/JSP/edit_procedure.jsp").forward(req, resp);
            }
        }
    }
}
