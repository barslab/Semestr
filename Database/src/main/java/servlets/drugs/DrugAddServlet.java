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

public class DrugAddServlet extends HttpServlet {

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
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String name = req.getParameter("name");
        String form = req.getParameter("form");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String contraindications = req.getParameter("contraindications");
        String overdose = req.getParameter("overdose");
        String[] side_effects_id = req.getParameterValues("side_effects2");
        String[] substitutes_drug_id = req.getParameterValues("drugs2");
        drugDao.putDrug(new Drug(quantity, name, form, contraindications, overdose));
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
