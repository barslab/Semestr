package servlets.symptoms;

import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;
import models.Symptoms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class SymptomEditServlet extends HttpServlet {
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
            getServletContext().getRequestDispatcher("/JSP/edit_symptom.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у симптома");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        String name = req.getParameter("name");
        String more_information = req.getParameter("more_information");
        symptomsDao.changeSymptoms(symptom_id, name, more_information);
        req.setAttribute("text", "Симптом успешно изменен");
        req.setAttribute("symptoms", symptomsDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_symptom.jsp").forward(req, resp);
    }
}
