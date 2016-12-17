package servlets.desiases;

import dao.*;
import factories.ConnectionFactory;
import models.Desiase;
import models.Procedures;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DesiaseAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        req.setAttribute("symptoms", symptomsDao.findAll());
        req.setAttribute("procedures", proceduresDao.findAll());
        req.setAttribute("drugs", drugDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String name = req.getParameter("name");
        float chance_desiase_man = Float.parseFloat(req.getParameter("chance_desiase_man"));
        float chance_desiase_women = Float.parseFloat(req.getParameter("chance_desiase_women"));
        int average_age = Integer.parseInt(req.getParameter("average_age"));
        String[] symptoms_id = req.getParameterValues("symptoms2");
        String[] procedures_id = req.getParameterValues("procedures2");
        desiaseDao.putDesiase(new Desiase(6, average_age, chance_desiase_man, chance_desiase_women, name));
        for (int i = 0; i < drugDao.findAllName().size(); i++) {
            int quanity = Integer.parseInt(req.getParameter(drugDao.findAllName().get(i)));
            if (quanity > 0) {
                desiaseDao.putDesiaseRecomendedDrug(desiaseDao.findDesiaseId(name), drugDao.findDrugId(drugDao.findAllName().get(i)), quanity);
            }
        }
        if (symptoms_id != null)
            for (int i = 0; i < symptoms_id.length; i++) {
                desiaseDao.putDesiaseSymptoms(desiaseDao.findDesiaseId(name), Integer.parseInt(symptoms_id[i]));
            }
        if (procedures_id != null)
            for (int i = 0; i < procedures_id.length; i++) {
                desiaseDao.putDesiaseProcedure(desiaseDao.findDesiaseId(name), Integer.parseInt(procedures_id[i]));
            }
        System.out.println(name + " " + chance_desiase_man + " " + chance_desiase_women + " " + average_age);
        req.setAttribute("text", "Болезнь " + name + " успешно добавлена");
        req.setAttribute("desiases", desiaseDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
    }
}
