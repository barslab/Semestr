package dao;

import factories.ConnectionFactory;
import models.Procedures;
import models.SideEffects;
import models.Symptoms;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProceduresDaoImpl implements ProceduresDao {
    private Connection connection;
    public ProceduresDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }
    public List<Procedures> find(int desiase_id) {
        List<Procedures> procedures = new LinkedList<Procedures>();
        Statement statement=null;
        String query="SELECT procedures.procedures_id,procedures.name,procedures.recommendations FROM procedures, procedures_desiase WHERE  procedures_desiase.procedures_id=procedures.procedures_id AND desiase_id=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                procedures.add(new Procedures(rs.getInt("procedures_id"), rs.getString("name"), rs.getString("recommendations")));

            }
        } catch (SQLException e) {
            System.out.println("Ошибка при поиске процедуры");
            e.printStackTrace();
        }
        return procedures;
    }

    public List<Procedures> findAll() {
        List<Procedures> procedures = new LinkedList<Procedures>();
        String query="SELECT * FROM procedures";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                procedures.add(new Procedures(rs.getInt("procedures_id"), rs.getString("name"), rs.getString("recommendations")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return procedures;
    }

    public Procedures findProcedure(int procedure_id) {
        Statement statement=null;
        String query="SELECT * FROM procedures WHERE procedures_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, procedure_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new Procedures(rs.getInt("procedures_id"), rs.getString("name"), rs.getString("recommendations"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> findAllId() {
        List<Integer> ids = new LinkedList<Integer>();
        String query="SELECT procedures_id FROM procedures";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("procedures_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public List<Integer> findIds(List<Procedures> procedures) {
        List<Integer> ids = new LinkedList<Integer>();
        for(int i=0;i<procedures.size();i++) {
            ids.add(procedures.get(i).getId());
        }
        return ids;
    }

    public List<Procedures> findProcedures(List<Integer> procedures_id) {
        List<Procedures> procedures = new LinkedList<Procedures>();
        for(int i=0;i<procedures_id.size();i++) {
            procedures.add(findProcedure(procedures_id.get(i)));
        }
        return procedures;
    }

    public void putProcedures(Procedures procedures) throws SQLException {
        String query = "INSERT INTO procedures (name, recommendations) VALUES(?,?);";
        PreparedStatement preparedStatement;
//        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, procedures.getName());
            preparedStatement.setString(2, procedures.getRecommendations());
            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Ошибка при добавлении процедуры");
//            e.printStackTrace();
//        }
    }

    public void deleteProcedures(int procedures_id) {
        String query = "DELETE FROM procedures where procedures_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, procedures_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении процедуры");
            e.printStackTrace();
        }
    }

    public void deleteProceduresOfDesiase(int desiase_id) {
        String query = "DELETE FROM procedures_desiase where desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении процедуры у болезни");
            e.printStackTrace();
        }
    }

    public void changeProcedures(int procedures_id, String new_name, String new_recommendation) throws SQLException {
        String query = "UPDATE procedures SET recommendations=?, name=? where procedures_id=?";
        PreparedStatement preparedStatement;
//        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, new_recommendation);
            preparedStatement.setString(2, new_name);
            preparedStatement.setInt(3, procedures_id);
            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Ошибка при изменении рекомендации процедуры");
//            e.printStackTrace();
//        }
    }
}
