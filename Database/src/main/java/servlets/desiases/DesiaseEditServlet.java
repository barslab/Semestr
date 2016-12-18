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

public class DesiaseEditServlet extends HttpServlet {
    private int desiase_id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        RecomendedDrugsDaoImpl recomendedDrugsDao = new RecomendedDrugsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        OtherMethods others = new OtherMethods();
        String[] path = req.getPathInfo().split("/");
        desiase_id = Integer.parseInt(path[path.length - 1]);
        System.out.println(desiase_id);
        req.setAttribute("desiase", desiaseDao.find(desiase_id));
        req.setAttribute("symptoms_desiase", desiaseDao.find(desiase_id).getSymptoms());
        req.setAttribute("other_symptoms", symptomsDao.findSymptoms(others.deleter(symptomsDao.findAllId(), symptomsDao.findIds(desiaseDao.find(desiase_id).getSymptoms()))));

        req.setAttribute("procedures_desiase", desiaseDao.find(desiase_id).getProcedures());
        req.setAttribute("other_procedures", proceduresDao.findProcedures(others.deleter(proceduresDao.findAllId(), proceduresDao.findIds(desiaseDao.find(desiase_id).getProcedures()))));

        req.setAttribute("drug_desiase", drugDao.findDrugs(desiase_id));
        req.setAttribute("other_drug", drugDao.findDrugs(others.deleter(drugDao.findAllDrugsId(), recomendedDrugsDao.findIds(drugDao.findDrugs(desiase_id)))));

        getServletContext().getRequestDispatcher("/JSP/edit_desiase.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        RecomendedDrugsDao recomendedDrugsDao = new RecomendedDrugsDaoImpl(connection);
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        String name = req.getParameter("name");
        float chance_desiase_man = Float.parseFloat(req.getParameter("chance_desiase_man"));
        float chance_desiase_women = Float.parseFloat(req.getParameter("chance_desiase_women"));
        int average_age = Integer.parseInt(req.getParameter("average_age"));
        String[] symptoms_id = req.getParameterValues("symptoms2");
        String[] procedures_id = req.getParameterValues("procedures2");
        desiaseDao.changeDesiase(new Desiase(desiase_id, average_age, chance_desiase_man, chance_desiase_women, name));
        recomendedDrugsDao.deleteRecomendedDrugs(desiase_id);
        for (int i = 0; i < drugDao.findAllName().size(); i++) {
            int quanity = Integer.parseInt(req.getParameter(drugDao.findAllName().get(i)));
            if (quanity > 0) {
                desiaseDao.putDesiaseRecomendedDrug(desiaseDao.findDesiaseId(name), drugDao.findDrugId(drugDao.findAllName().get(i)), quanity);
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
        req.setAttribute("text", "Болезнь успешно изменен");
        req.setAttribute("desiases", desiaseDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
    }
}
