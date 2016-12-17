package dao;

import com.sun.org.apache.regexp.internal.RE;
import factories.ConnectionFactory;
import models.Drug;
import models.RecomendedDrugs;
import models.Symptoms;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class RecomendedDrugsDaoImpl implements RecomendedDrugsDao {
    private Connection connection;
    public RecomendedDrugsDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    DrugDaoImpl drugDao = new DrugDaoImpl(connection);
    public List<RecomendedDrugs> find(int desiase_id) {
        List<RecomendedDrugs> recomendedDrugses = new LinkedList<RecomendedDrugs>();
        Statement statement=null;
        String query="SELECT recomended_drugs_id, quantity, drug_id FROM recomended_drug WHERE desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                recomendedDrugses.add(new RecomendedDrugs(rs.getInt("recomended_drugs_id"), rs.getInt("quantity"), drugDao.findDrug(rs.getInt("drug_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recomendedDrugses;
    }

    public void putRecomendedDrugs(RecomendedDrugs recomendedDrugs) {

    }
    public void deleteRecomendedDrugs(int recomended_drugs_id) {

    }

    public void changeRecomendedDrugs(int desiase_id, int new_quantity) {

    }
}
