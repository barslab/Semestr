package servlets.desiases;

import Util.OtherMethods;
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
import java.util.LinkedList;
import java.util.List;

public class DesiaseEditServlet extends HttpServlet {
    private int desiase_id, old_quanity;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        RecomendedDrugsDaoImpl recomendedDrugsDao = new RecomendedDrugsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        OtherMethods others = new OtherMethods(connection);
        String[] path = req.getPathInfo().split("/");
        try {
            desiase_id = Integer.parseInt(path[path.length - 1]);
        }
        catch (NumberFormatException e) {
            getServletContext().getRequestDispatcher("/JSP/invalidaddress.jsp").forward(req, resp);
        }
        System.out.println(desiase_id);
        if(desiaseDao.find(desiase_id)!=null) {
            req.setAttribute("desiase", desiaseDao.find(desiase_id));
            req.setAttribute("symptoms_desiase", desiaseDao.find(desiase_id).getSymptoms());
            req.setAttribute("other_symptoms", symptomsDao.findSymptoms(others.deleter(symptomsDao.findAllId(), symptomsDao.findIds(desiaseDao.find(desiase_id).getSymptoms()))));
            req.setAttribute("procedures_desiase", desiaseDao.find(desiase_id).getProcedures());
            req.setAttribute("other_procedures", proceduresDao.findProcedures(others.deleter(proceduresDao.findAllId(), proceduresDao.findIds(desiaseDao.find(desiase_id).getProcedures()))));
            req.setAttribute("drug_desiase", drugDao.findDrugs(desiase_id));
            req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), recomendedDrugsDao.findIds(drugDao.findDrugs(desiase_id)))));
            getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "Некорректный id у болезни");
            getServletContext().getRequestDispatcher("/JSP/invalidid.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        req.setCharacterEncoding("UTF-8");
        List<String> error = new LinkedList<String>();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        RecomendedDrugsDao recomendedDrugsDao = new RecomendedDrugsDaoImpl(connection);
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        OtherMethods others = new OtherMethods(connection);
        req.setAttribute("desiase", desiaseDao.find(desiase_id));
        req.setAttribute("symptoms_desiase", desiaseDao.find(desiase_id).getSymptoms());
        req.setAttribute("other_symptoms", symptomsDao.findSymptoms(others.deleter(symptomsDao.findAllId(), symptomsDao.findIds(desiaseDao.find(desiase_id).getSymptoms()))));
        req.setAttribute("procedures_desiase", desiaseDao.find(desiase_id).getProcedures());
        req.setAttribute("other_procedures", proceduresDao.findProcedures(others.deleter(proceduresDao.findAllId(), proceduresDao.findIds(desiaseDao.find(desiase_id).getProcedures()))));
        req.setAttribute("drug_desiase", drugDao.findDrugs(desiase_id));
        req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), recomendedDrugsDao.findIds(drugDao.findDrugs(desiase_id)))));
        String name = req.getParameter("name");
        if (name.length() > 45) {
            req.setAttribute("error", "Название болезни не может превышать 45 симвлов");
            getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
        } else {
            try {
                float chance_desiase_man = Float.parseFloat(req.getParameter("chance_desiase_man"));
                if (chance_desiase_man > 1.00 || chance_desiase_man < 0.00) {
                    req.setAttribute("error", "Вероятность заболевания мужчины может быть только в пределах от 0 до 1 лет включительно");
                    getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                } else {
                    try {
                        float chance_desiase_women = Float.parseFloat(req.getParameter("chance_desiase_women"));
                        if (chance_desiase_women > 1.00 || chance_desiase_women < 0.00) {
                            req.setAttribute("error", "Вероятность заболевания женщины может быть только в пределах от 0 до 1 лет включительно");
                            getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                        } else {
                            try {
                                int average_age = Integer.parseInt(req.getParameter("average_age"));
                                if (average_age < 0 || average_age > 130) {
                                    req.setAttribute("error", "Средний возраст больного может быть только в пределах от 0 до 130 лет включительно");
                                    getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                                } else {
                                    String[] symptoms_id = req.getParameterValues("symptoms2");
                                    String[] procedures_id = req.getParameterValues("procedures2");
                                    try {
                                        desiaseDao.changeDesiase(new Desiase(desiase_id, average_age, chance_desiase_man, chance_desiase_women, name));
                                        List<String> names = drugDao.findAllName();
                                        for (int i = 0; i < names.size(); i++) {
                                            int quanity = Integer.parseInt(req.getParameter(names.get(i)));
//                                            System.out.println(names + " " + quanity);
                                            old_quanity = recomendedDrugsDao.find(desiase_id, names.get(i));
                                            recomendedDrugsDao.deleteRecomendedDrugs(desiase_id, names.get(i));
                                            drugDao.changeDrugQuantity(drugDao.findDrugId(names.get(i)), drugDao.findDrug(drugDao.findDrugId(names.get(i))).getQuantity() + old_quanity);
                                            if (quanity > 0) {
                                                System.out.println(old_quanity + drugDao.findDrug(drugDao.findDrugId(names.get(i))).getQuantity() + " " + names.get(i));
                                                if (quanity <= old_quanity + drugDao.findDrug(drugDao.findDrugId(names.get(i))).getQuantity()) {
                                                    desiaseDao.putDesiaseRecomendedDrug(desiaseDao.findDesiaseId(name), drugDao.findDrugId(names.get(i)), quanity);
                                                    drugDao.changeDrugQuantity(drugDao.findDrugId(names.get(i)), drugDao.findDrug(drugDao.findDrugId(names.get(i))).getQuantity() + old_quanity-quanity);
                                                } else {
                                                    error.add("Лекарство " + names.get(i) + " отсутствует в нужном количестве нa складе");
                                                }
                                            }
                                        }
                                        symptomsDao.deleteSymptomsOfDesiase(desiase_id);
                                        if (symptoms_id != null)
                                            for (int i = 0; i < symptoms_id.length; i++) {
                                                desiaseDao.putDesiaseSymptoms(desiaseDao.findDesiaseId(name), Integer.parseInt(symptoms_id[i]));
                                            }
                                        proceduresDao.deleteProceduresOfDesiase(desiase_id);
                                        if (procedures_id != null)
                                            for (int i = 0; i < procedures_id.length; i++) {
                                                desiaseDao.putDesiaseProcedure(desiaseDao.findDesiaseId(name), Integer.parseInt(procedures_id[i]));
                                            }
                                        System.out.println(name + " " + chance_desiase_man + " " + chance_desiase_women + " " + average_age);
                                        req.setAttribute("text", "Болезнь успешно изменена");
                                        req.setAttribute("desiases", desiaseDao.findAll());
                                        req.setAttribute("error_drug", error);
                                        getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
                                    } catch (SQLException e) {
                                        req.setAttribute("error", "Такая болезнь уже существует");
                                        getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                                    }
                                }
                            } catch (NumberFormatException e3) {
                                req.setAttribute("error", "Некорректно введено значение в поле для среднего возраста больного");
                                getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                            }
                        }
                    } catch (NumberFormatException e2) {
                        req.setAttribute("error", "Некорректно введено значение в поле для вероятности заболевания женщины");
                        getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
                    }
                }
            } catch (NumberFormatException e1) {
                req.setAttribute("error", "Некорректно введено значение в поле для вероятности заболевания мужчины");
                getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
            }
        }
    }
}
