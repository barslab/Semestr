package servlets.desiases;
import dao.DesiaseDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AllDesiaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        req.setAttribute("desiases", desiaseDao.findAll());
        req.setAttribute("text", null);
        req.setAttribute("error_drug", null);
        getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
