package servlets.desiases;

import dao.DesiaseDaoImpl;
import dao.DrugDaoImpl;
import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DesiaseServlet extends HttpServlet {
    private int desiase_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            desiase_id = Integer.parseInt(path[path.length - 1]);
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
        System.out.println(desiase_id);
        if(desiaseDao.find(desiase_id)!=null) {
            req.setAttribute("desiase", desiaseDao.find(desiase_id));
            getServletContext().getRequestDispatcher("/JSP/desiase.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у болезни");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
