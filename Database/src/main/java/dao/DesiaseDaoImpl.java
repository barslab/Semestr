package dao;

import factories.ConnectionFactory;
import models.Desiase;
import models.Drug;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DesiaseDaoImpl implements DesiaseDao {
    private Connection connection;

    public DesiaseDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
    RecomendedDrugsDao recomendedDrugsDao = new RecomendedDrugsDaoImpl(connection);
    ProceduresDaoImpl proceduresDao = new ProceduresDaoImpl(connection);

    public Desiase find(int desiase_id) {
        Statement statement = null;
        String query = "SELECT * FROM desiase WHERE desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new Desiase(rs.getInt("desiase_id"), rs.getInt("average_age"), rs.getFloat("chance_desiase_man"), rs.getFloat("chance_desiase_women"), rs.getString("name"),
                        proceduresDao.find(desiase_id), symptomsDao.find(desiase_id), recomendedDrugsDao.find(desiase_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Desiase> findAll() {
        List<Desiase> desiases = new LinkedList<Desiase>();
        Statement statement = null;
        String query = "SELECT * FROM desiase";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                desiases.add(new Desiase(rs.getInt("desiase_id"), rs.getInt("average_age"), rs.getFloat("chance_desiase_man"), rs.getFloat("chance_desiase_women"), rs.getString("name"),
                        proceduresDao.find(rs.getInt("desiase_id")), symptomsDao.find(rs.getInt("desiase_id")), recomendedDrugsDao.find(rs.getInt("desiase_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desiases;
    }

    public void putDesiase(Desiase desiase) {
        String query = "INSERT INTO desiase (name, chance_desiase_man,chance_desiase_women,average_age) VALUES(?,?,?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, desiase.getName());
            preparedStatement.setFloat(2, desiase.getChance_desiase_man());
            preparedStatement.setFloat(3, desiase.getChance_desiase_women());
            preparedStatement.setInt(4, desiase.getAverage_age());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении болезни");
            e.printStackTrace();
        }
    }

    public void putDesiaseSymptoms(int desiase_id, int symptoms_id) {
        String query = "INSERT INTO symptoms_desiase (desiase_id, symptoms_id) VALUES(?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.setInt(2, symptoms_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении cимптома к блезни");
            e.printStackTrace();
        }
    }

    public void putDesiaseProcedure(int desiase_id, int procedure_id) {
        String query = "INSERT INTO procedures_desiase (desiase_id, procedures_id) VALUES(?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.setInt(2, procedure_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении процедуры к блезни");
            e.printStackTrace();
        }
    }

    public void putDesiaseRecomendedDrug(int desiase_id, int drug_id, int quantity) {
        String query = "INSERT INTO recomended_drug (desiase_id, drug_id,quantity) VALUES(?,?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.setInt(2, drug_id);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении процедуры к блезни");
            e.printStackTrace();
        }
    }
    public int findDesiaseId(String name) {
        Statement statement=null;
        String query="SELECT desiase_id FROM desiase WHERE name=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt("desiase_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteDesiase(int desiase_id) {
        String query = "DELETE FROM desiase where desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении болезни");
            e.printStackTrace();
        }
    }

    public void changeDesiase(Desiase desiase) {
        String query = "UPDATE desiase SET name=?, chance_desiase_man=?, chance_desiase_women=?, average_age=? where desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, desiase.getName());
            preparedStatement.setFloat(2, desiase.getChance_desiase_man());
            preparedStatement.setFloat(3, desiase.getChance_desiase_women());
            preparedStatement.setInt(4, desiase.getAverage_age());
            preparedStatement.setInt(5, desiase.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при изменении болезни");
            e.printStackTrace();
        }
    }
}
