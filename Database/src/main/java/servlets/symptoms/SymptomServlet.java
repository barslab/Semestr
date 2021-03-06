package servlets.symptoms;

import dao.DesiaseDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class SymptomServlet extends HttpServlet {
    private int symptom_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            symptom_id = Integer.parseInt(path[path.length - 1]);
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
        System.out.println(symptom_id);
        if(symptomsDao.findSymptom(symptom_id)!=null) {
            req.setAttribute("symptom", symptomsDao.findSymptom(symptom_id));
            getServletContext().getRequestDispatcher("/JSP/symptom.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у симптома");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
