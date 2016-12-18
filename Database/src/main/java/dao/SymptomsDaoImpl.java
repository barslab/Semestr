package dao;

import factories.ConnectionFactory;
import models.Drug;
import models.SideEffects;
import models.Symptoms;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SymptomsDaoImpl implements SymptomsDao {
    private Connection connection;

    public SymptomsDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public List<Symptoms> find(int desiase_id) {
        List<Symptoms> symptoms = new LinkedList<Symptoms>();
        String query = "SELECT * FROM desiase_symptoms_view WHERE desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                symptoms.add(new Symptoms(rs.getInt("symptoms_id"), rs.getString("name"), rs.getString("more_information")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return symptoms;
    }

    public HashSet<Integer> findIdOfDesiase(int desiase_id) {
        HashSet<Integer> ids = new HashSet<Integer>();
        String query = "SELECT symptoms_id FROM symptoms_desiase WHERE desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("symptoms_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public List<Symptoms> findAll() {
        List<Symptoms> symptoms = new LinkedList<Symptoms>();
        String query = "SELECT * FROM symptoms";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                symptoms.add(new Symptoms(rs.getInt("symptoms_id"), rs.getString("name"), rs.getString("more_information")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return symptoms;
    }

    public Symptoms findSymptom(int symptom_id) {
        String query = "SELECT * FROM symptoms WHERE symptoms_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, symptom_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new Symptoms(rs.getInt("symptoms_id"), rs.getString("name"), rs.getString("more_information"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> findAllId() {
        List<Integer> ids = new LinkedList<Integer>();
        String query = "SELECT symptoms_id FROM symptoms";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("symptoms_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public List<Integer> findIds(List<Symptoms> symptoms) {
        List<Integer> ids = new LinkedList<Integer>();
        for (int i = 0; i < symptoms.size(); i++) {
            ids.add(symptoms.get(i).getId());
        }
        return ids;
    }

    public List<Symptoms> findSymptoms(List<Integer> symptoms_id) {
        List<Symptoms> symptoms = new LinkedList<Symptoms>();
        for (int i = 0; i < symptoms_id.size(); i++) {
            symptoms.add(findSymptom(symptoms_id.get(i)));
        }
        return symptoms;
    }

    public void putSymptoms(Symptoms symptoms) throws SQLException {
        String query = "SELECT symptoms_insert(?,?)";
        PreparedStatement preparedStatement = ConnectionFactory.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, symptoms.getName());
        preparedStatement.setString(2, symptoms.getMore_information());
        ResultSet resultSet = preparedStatement.executeQuery();
    }
    public void deleteSymptoms(int symptoms_id) throws SQLException {
        String query = "SELECT symptoms_delete(?)";
        PreparedStatement preparedStatement = ConnectionFactory.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setInt(1, symptoms_id);
        ResultSet resultSet = preparedStatement.executeQuery();
    }

    public void deleteSymptomsOfDesiase(int desiase_id) {
        String query = "DELETE FROM symptoms_desiase where desiase_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении симптома у болезни");
            e.printStackTrace();
        }
    }

    public void changeSymptoms(int symptoms_id, String new_name, String new_informations) throws SQLException {
        String query = "UPDATE symptoms SET more_information=?, name=? where symptoms_id=?";
        PreparedStatement preparedStatement;
//        try {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, new_informations);
        preparedStatement.setString(2, new_name);
        preparedStatement.setInt(3, symptoms_id);
        preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Ошибка при изменении информации симптома");
//            e.printStackTrace();
//        }
    }
}
