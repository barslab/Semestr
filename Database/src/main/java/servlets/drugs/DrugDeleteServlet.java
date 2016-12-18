package servlets.drugs;
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

public class DrugDeleteServlet extends HttpServlet {
    private int drug_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            drug_id = Integer.parseInt(path[path.length - 1]);
            if(drugDao.findDrug(drug_id)!=null) {
                text = "Лекарство "+drugDao.findDrug(drug_id).getName()+" успешно удалена";
                drugDao.deleteDrug(drug_id);
                req.setAttribute("drugs", drugDao.findAll());
                req.setAttribute("text", text);
                getServletContext().getRequestDispatcher("/JSP/all_drug.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("error", "Некорректный id у лекарства");
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