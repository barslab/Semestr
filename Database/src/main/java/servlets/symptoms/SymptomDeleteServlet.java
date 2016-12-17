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

public class SymptomDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        String[] path = req.getPathInfo().split("/");
        int symptom_id = Integer.parseInt(path[path.length-1]);
        System.out.println(symptom_id);
        text = "Симптом "+symptomsDao.findSymptom(symptom_id).getName()+" успешно удалена";
        symptomsDao.deleteSymptoms(symptom_id);
        req.setAttribute("symptoms", symptomsDao.findAll());
        req.setAttribute("text", text);
        getServletContext().getRequestDispatcher("/JSP/all_symptom.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}