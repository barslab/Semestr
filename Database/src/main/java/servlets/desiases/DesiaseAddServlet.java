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
import java.sql.SQLException;

public class DesiaseAddServlet extends HttpServlet {
    private float chance_desiase_women, chance_desiase_man;
    private int average_age;
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
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);
        DesiaseDaoImpl desiaseDao = new DesiaseDaoImpl(connection);
        req.setAttribute("symptoms", symptomsDao.findAll());
        req.setAttribute("procedures", proceduresDao.findAll());
        req.setAttribute("drugs", drugDao.findAll());
        String name = req.getParameter("name");
        if (name.length() > 45) {
            req.setAttribute("error", "Название болезни не может превышать 45 симвлов");
            getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
        } else {
            try {
                chance_desiase_man = Float.parseFloat(req.getParameter("chance_desiase_man"));
                if (chance_desiase_man > 1.00 || chance_desiase_man < 0.00) {
                    req.setAttribute("error", "Вероятность заболевания мужчины может быть только в пределах от 0 до 1 лет включительно");
                    getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
                }

            } catch (NumberFormatException e1) {
                req.setAttribute("error", "Некорректно введено значение в поле для вероятности заболевания мужчины");
                getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
            }
            try {
                chance_desiase_women = Float.parseFloat(req.getParameter("chance_desiase_women"));
                if (chance_desiase_women > 1.00 || chance_desiase_women < 0.00) {
                    req.setAttribute("error", "Вероятность заболевания женщины может быть только в пределах от 0 до 1 лет включительно");
                    getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e2) {
                req.setAttribute("error", "Некорректно введено значение в поле для вероятности заболевания женщины");
                getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
            }
            try {
                average_age = Integer.parseInt(req.getParameter("average_age"));
                if (average_age < 0 || average_age > 130) {
                    req.setAttribute("error", "Средний возраст больного может быть только в пределах от 0 до 130 лет включительно");
                    getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e3) {
                req.setAttribute("error", "Некорректно введено значение в поле для среднего возраста больного");
                getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
            }
            String[] symptoms_id = req.getParameterValues("symptoms2");
            String[] procedures_id = req.getParameterValues("procedures2");
            try {
                desiaseDao.putDesiase(new Desiase(6, average_age, chance_desiase_man, chance_desiase_women, name));
            } catch (SQLException e) {
                req.setAttribute("error", "Такая болезнь уже существует");
                getServletContext().getRequestDispatcher("/JSP/desiase_add.jsp").forward(req, resp);
                System.out.println(e.getSQLState());
                System.out.println(e.getLocalizedMessage());
            }
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
}
