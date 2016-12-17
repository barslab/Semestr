import dao.DesiaseDaoImpl;
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
        SubstitutesDaoImpl desiaseDao = new SubstitutesDaoImpl(connection);
        List<Substitutes> substitutes = desiaseDao.find(1);
        System.out.println(substitutes);
    }
}
