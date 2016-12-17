package servlets.desiases;
import dao.DesiaseDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DesiaseDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        int desiase_id = Integer.parseInt(path[path.length-1]);
        System.out.println(desiase_id);
        text = "Болезнь "+desiaseDao.find(desiase_id).getName()+" успешно удалена";
        desiaseDao.deleteDesiase(desiase_id);
        req.setAttribute("desiases", desiaseDao.findAll());
        req.setAttribute("text", text);
        getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}