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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        int procedures_id = Integer.parseInt(path[path.length-1]);
        System.out.println(procedures_id);
        text = "Процедура "+proceduresDao.findProcedure(procedures_id).getName()+" успешно удалена";
        proceduresDao.deleteProcedures(procedures_id);
        req.setAttribute("procedures", proceduresDao.findAll());
        req.setAttribute("text", text);
        getServletContext().getRequestDispatcher("/JSP/all_procedure.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}