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
import java.sql.SQLException;

public class SymptomDeleteServlet extends HttpServlet {
    private int symptom_id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        String text = null;
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
            text = "Симптом "+symptomsDao.findSymptom(symptom_id).getName()+" успешно удалена";
            try {
                symptomsDao.deleteSymptoms(symptom_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("symptoms", symptomsDao.findAll());
            req.setAttribute("text", text);
            getServletContext().getRequestDispatcher("/JSP/all_symptom.jsp").forward(req, resp);
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