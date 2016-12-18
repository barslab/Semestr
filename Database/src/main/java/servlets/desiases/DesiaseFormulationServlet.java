package servlets.desiases;

import Util.OtherMethods;
import dao.DesiaseDaoImpl;
import dao.DrugDaoImpl;
import dao.ProceduresDaoImpl;
import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;
import models.Desiase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class DesiaseFormulationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        req.setAttribute("symptoms", symptomsDao.findAll());
        getServletContext().getRequestDispatcher("/JSP/desiaseformulation.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        OtherMethods other = new OtherMethods(connection);
        List<Desiase> desiase = new LinkedList<Desiase>();
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        String[] symptoms_id = req.getParameterValues("symptoms2");
        if (symptoms_id!=null) {
            for (Integer desiase_id : desiaseDao.findAllId()) {
                if (other.check(desiase_id, symptoms_id)) {
                    desiase.add(desiaseDao.find(desiase_id));
                }
            }
            if (desiase.size() != 0) {
                req.setAttribute("desiases", desiase);
                req.setAttribute("text", "По вашим симптомам найдены следующие лекарства");
                getServletContext().getRequestDispatcher("/JSP/all_desiase.jsp").forward(req, resp);
            }
            req.setAttribute("symptoms", symptomsDao.findAll());
            req.setAttribute("error", "Никаких болезней по данным симптомам не найдено");
            getServletContext().getRequestDispatcher("/JSP/desiaseformulation.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("symptoms", symptomsDao.findAll());
            req.setAttribute("error", "Симптомы не выбраны");
            getServletContext().getRequestDispatcher("/JSP/desiaseformulation.jsp").forward(req, resp);
        }
    }
}
