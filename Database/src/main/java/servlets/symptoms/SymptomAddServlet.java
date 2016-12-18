package servlets.symptoms;

import dao.*;
import factories.ConnectionFactory;
import models.Desiase;
import models.Drug;
import models.Procedures;
import models.Symptoms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SymptomAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/JSP/symptom_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        String name = req.getParameter("name");
        if(name.length()>45) {
            req.setAttribute("error", "Название симптома не может превышать 45 символов");
            getServletContext().getRequestDispatcher("/JSP/symptom_add.jsp").forward(req, resp);
        }
        else {
            String more_information = req.getParameter("more_information");
            try {
                symptomsDao.putSymptoms(new Symptoms(name, more_information));
                req.setAttribute("text", "Симптом " + name + " успешно добавлен");
                req.setAttribute("symptoms", symptomsDao.findAll());
                getServletContext().getRequestDispatcher("/JSP/all_symptom.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error", "Такой симптом уже существует");
                getServletContext().getRequestDispatcher("/JSP/symptom_add.jsp").forward(req, resp);
                e.printStackTrace();
            }
        }
    }
}
