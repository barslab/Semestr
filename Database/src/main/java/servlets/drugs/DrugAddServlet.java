package servlets.drugs;

import dao.*;
import factories.ConnectionFactory;
import models.Desiase;
import models.Drug;
import models.Procedures;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DrugAddServlet extends HttpServlet {
    private int quantity;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        req.setAttribute("side_effects", sideEffectsDao.findAll());
        req.setAttribute("drugs", drugDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/drug_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        req.setAttribute("side_effects", sideEffectsDao.findAll());
        req.setAttribute("drugs", drugDao.findAll());
        String name = req.getParameter("name");
        if (name.length()>45) {
            req.setAttribute("error", "Название лекарства не может превышать 45 символов");
            getServletContext().getRequestDispatcher("/JSP/drug_add.jsp").forward(req, resp);
        }
        else {
            String form = req.getParameter("form");
            try {
                quantity = Integer.parseInt(req.getParameter("quantity"));
                if (quantity < 0) {
                    req.setAttribute("error", "Количество лекарства не может быть меньше 0");
                    getServletContext().getRequestDispatcher("/JSP/drug_add.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Некорректно введено значение в поле количества лекарства");
                getServletContext().getRequestDispatcher("/JSP/drug_add.jsp").forward(req, resp);
            }
            System.out.println("ne doljno bit`");
            String contraindications = req.getParameter("contraindications");
            String overdose = req.getParameter("overdose");
            if (overdose.length() > 60) {
                req.setAttribute("error", "Передозировка не может превышать 60 символов");
                getServletContext().getRequestDispatcher("/JSP/drug_add.jsp").forward(req, resp);
            }
            String[] side_effects_id = req.getParameterValues("side_effects2");
            String[] substitutes_drug_id = req.getParameterValues("drugs2");
            try {
                drugDao.putDrug(new Drug(quantity, name, form, contraindications, overdose));
            } catch (SQLException e) {
                req.setAttribute("error", "Такое лекарство уже существует");
                req.setAttribute("side_effects", sideEffectsDao.findAll());
                req.setAttribute("drugs", drugDao.findAll());
                getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
            }
            if (side_effects_id != null)
                for (int i = 0; i < side_effects_id.length; i++) {
                    int effects_id = Integer.parseInt(side_effects_id[i]);
                    drugDao.putSideEffectsDrug(drugDao.findDrugId(name), effects_id);
                }
            if (substitutes_drug_id != null)
                for (int i = 0; i < substitutes_drug_id.length; i++) {
                    int substitutes_id = Integer.parseInt(side_effects_id[i]);
                    substitutesDao.put(drugDao.findDrugId(name), substitutes_id);
                }
            req.setAttribute("text", "Лекарство " + name + " успешно добавлено");
            req.setAttribute("drugs", drugDao.findAll());
            getServletContext().getRequestDispatcher("/JSP/all_drug.jsp").forward(req, resp);
        }
    }
}
