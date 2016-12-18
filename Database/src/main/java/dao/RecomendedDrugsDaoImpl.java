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

    public int find(int desiase_id, String drug_name) {
        String query="SELECT recomended_drug.quantity FROM recomended_drug, drug WHERE desiase_id=? AND drug.name=? AND drug.drug_id=recomended_drug.drug_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.setString(2, drug_name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Integer> findIds(List<Drug> drugs) {
        List<Integer> ids = new LinkedList<Integer>();
        for(int i=0; i<drugs.size(); i++) {
            ids.add(drugs.get(i).getId());
        }
        return ids;
    }

    public void putRecomendedDrugs(RecomendedDrugs recomendedDrugs) {

    }

    public void deleteRecomendedDrugs(int desiase_id) {
        String query = "DELETE FROM recomended_drug WHERE desiase_id=?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении лекарств к болезни");
            e.printStackTrace();
        }
    }

    public void deleteRecomendedDrugs(int desiase_id, String drug_name) {
        String query = "DELETE FROM recomended_drug WHERE desiase_id=? AND drug_id=(SELECT drug_id FROM drug WHERE name=?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.setString(2, drug_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении лекарств к болезни");
            e.printStackTrace();
        }
    }
}
