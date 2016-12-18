import Util.OtherMethods;
import dao.DesiaseDaoImpl;
import dao.DrugDao;
import dao.DrugDaoImpl;
import dao.SubstitutesDaoImpl;
import factories.ConnectionFactory;
import models.Desiase;
import models.Drug;
import models.Substitutes;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        OtherMethods others = new OtherMethods();
        DrugDaoImpl drugDao = new DrugDaoImpl(connection);
        List<Drug> drugs = drugDao.findDrugs(12);
//        List<Integer> all = drugDao.findAllDrugsId();
//        List<Integer> other = drugDao.findDrugsId(1);
//        List<Drug> result = drugDao.findDrugs(others.deleter(all, other));
        System.out.println(drugs);
    }
}
