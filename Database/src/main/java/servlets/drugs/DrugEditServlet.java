package servlets.drugs;

import Util.OtherMethods;
import dao.*;
import factories.ConnectionFactory;
import models.Drug;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DrugEditServlet extends HttpServlet {
    private int drug_id,quantity;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        OtherMethods others = new OtherMethods(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            drug_id = Integer.parseInt(path[path.length - 1]);
            if(drugDao.findDrug(drug_id)!=null) {
                req.setAttribute("drug", drugDao.findDrug(drug_id));
                req.setAttribute("substitutes_drug", drugDao.findDrugsOfDrug(drug_id));
//        Все лекарства, которые не заменители
                req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), drugDao.findDrugsId(drug_id), drug_id)));
                req.setAttribute("side_drug", sideEffectsDao.find(drug_id));
                req.setAttribute("other_side", sideEffectsDao.findSideEffects(others.deleter(sideEffectsDao.findAllId(), sideEffectsDao.findId(sideEffectsDao.find(drug_id)))));
                getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
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
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        SubstitutesDaoImpl substitutesDao = new SubstitutesDaoImpl(connection);
        SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
        OtherMethods others = new OtherMethods(connection);
        req.setAttribute("drug", drugDao.findDrug(drug_id));
        req.setAttribute("substitutes_drug", drugDao.findDrugsOfDrug(drug_id));
        req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), drugDao.findDrugsId(drug_id), drug_id)));
        req.setAttribute("side_drug", sideEffectsDao.find(drug_id));
        req.setAttribute("other_side", sideEffectsDao.findSideEffects(others.deleter(sideEffectsDao.findAllId(), sideEffectsDao.findId(sideEffectsDao.find(drug_id)))));
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String name = req.getParameter("name");
        if (name.length()>45) {
            req.setAttribute("error", "Название лекарства не может превышать 45 символов");
            getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
        }
        else {
            String form = req.getParameter("form");
            try {
                quantity = Integer.parseInt(req.getParameter("quantity"));
                if (quantity < 0) {
                    req.setAttribute("error", "Количество лекарства не может быть меньше 0");

                    getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
                }
                else {
                    String contraindications = req.getParameter("contraindications");
                    String overdose = req.getParameter("overdose");
                    if (overdose.length() > 60) {
                        req.setAttribute("error", "Передозировка не может превышать 60 символов");
                        getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
                    } else {
                        String[] side_effects_id = req.getParameterValues("side_effects2");
                        String[] substitutes_drug_id = req.getParameterValues("drugs2");
                        try {
                            drugDao.changeDrug(new Drug(drug_id, quantity, name, form, contraindications, overdose));
                        } catch (SQLException e) {
                            req.setAttribute("error", "Такое лекарство уже существует");
                            req.setAttribute("side_effects", sideEffectsDao.findAll());
                            req.setAttribute("drugs", drugDao.findAll());
                            getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
                        }
                        sideEffectsDao.deleteAllEffects(drug_id);
                        if (side_effects_id != null)
                            for (int i = 0; i < side_effects_id.length; i++) {
                                int effects_id = Integer.parseInt(side_effects_id[i]);
                                drugDao.putSideEffectsDrug(drug_id, effects_id);
                            }
                        substitutesDao.deleteAllSubstitutes(drug_id);
                        if (substitutes_drug_id != null)
                            for (int i = 0; i < substitutes_drug_id.length; i++) {
                                int substitutes_id = Integer.parseInt(side_effects_id[i]);
                                substitutesDao.put(drug_id, substitutes_id);
                            }
                        req.setAttribute("text", "Лекарство успешно изменено");
                        req.setAttribute("drugs", drugDao.findAll());
                        getServletContext().getRequestDispatcher("/JSP/all_drug.jsp").forward(req, resp);
                    }
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Некорректно введено значение в поле количества лекарства");
                getServletContext().getRequestDispatcher("/JSP/edit_drug.jsp").forward(req, resp);
            }
        }
    }
}
