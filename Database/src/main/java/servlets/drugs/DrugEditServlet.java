package servlets.drugs;

import Util.OtherMethods;
import dao.*;
import factories.ConnectionFactory;
import models.Drug;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DrugEditServlet extends HttpServlet {
    private int drug_id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        OtherMethods others = new OtherMethods();
        String[] path = req.getPathInfo().split("/");
        drug_id = Integer.parseInt(path[path.length - 1]);
        System.out.println(drug_id);
        req.setAttribute("drug", drugDao.findDrug(drug_id));
        req.setAttribute("substitutes_drug", drugDao.findDrugsOfDrug(drug_id));
//        Все лекарства, которые не заменители
        req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), drugDao.findDrugsId(drug_id), drug_id)));
        req.setAttribute("side_drug", sideEffectsDao.find(drug_id));
        req.setAttribute("other_side", sideEffectsDao.findSideEffects(others.deleter(sideEffectsDao.findAllId(), sideEffectsDao.findId(sideEffectsDao.find(drug_id)))));
        getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String name = req.getParameter("name");
        String form = req.getParameter("form");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String contraindications = req.getParameter("contraindications");
        String overdose = req.getParameter("overdose");
        String[] side_effects_id = req.getParameterValues("side_effects2");
        String[] substitutes_drug_id = req.getParameterValues("drugs2");
        if (substitutes_drug_id != null)
            for (int i = 0; i < substitutes_drug_id.length; i++)
            System.out.println("Zameniteli id: "+substitutes_drug_id[i]);
        if (side_effects_id != null)
            for (int i = 0; i < side_effects_id.length; i++)
            System.out.println("Effecti id: "+side_effects_id[i]);
        drugDao.changeDrug(new Drug(drug_id, quantity, name, form, contraindications, overdose));
        substitutesDao.deleteAllSubstitutes(drug_id);
        if (substitutes_drug_id != null)
            for (int i = 0; i < substitutes_drug_id.length; i++) {
                int substitutes_id = Integer.parseInt(substitutes_drug_id[i]);
                substitutesDao.put(drug_id, substitutes_id);
            }
        sideEffectsDao.deleteAllEffects(drug_id);
        if (side_effects_id != null)
            for (int i = 0; i < side_effects_id.length; i++) {
                int effects_id = Integer.parseInt(side_effects_id[i]);
                drugDao.putSideEffectsDrug(drug_id, effects_id);
            }
        req.setAttribute("text", "Лекарство успешно изменено");
        req.setAttribute("drugs", drugDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_drug.jsp").forward(req, resp);
    }
}
