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

public class ProcedureDeleteServlet extends HttpServlet {
    private int procedures_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            procedures_id = Integer.parseInt(path[path.length - 1]);
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
        System.out.println(procedures_id);
        if(proceduresDao.findProcedure(procedures_id)!=null) {
            text = "Процедура "+proceduresDao.findProcedure(procedures_id).getName()+" успешно удалена";
            proceduresDao.deleteProcedures(procedures_id);
            req.setAttribute("procedures", proceduresDao.findAll());
            req.setAttribute("text", text);
            getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у процедуры");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}