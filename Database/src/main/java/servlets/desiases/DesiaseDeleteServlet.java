package servlets.desiases;
import dao.DesiaseDaoImpl;
import dao.DrugDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DesiaseDeleteServlet extends HttpServlet {
    private int desiase_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            desiase_id = Integer.parseInt(path[path.length - 1]);
            if(desiaseDao.find(desiase_id)!=null) {
                text = "Болезнь "+desiaseDao.find(desiase_id).getName()+" успешно удалена";
                drugDao.changeDrugQuantity(desiase_id);
                desiaseDao.deleteDesiase(desiase_id);
                req.setAttribute("desiases", desiaseDao.findAll());
                req.setAttribute("text", text);
                req.setAttribute("error_drug", null);
                getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("error", "Некорректный id у болезни");
                getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
            }
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}