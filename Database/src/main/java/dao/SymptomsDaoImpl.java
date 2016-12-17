package dao;

import factories.ConnectionFactory;
import models.Drug;
import models.SideEffects;
import models.Symptoms;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SymptomsDaoImpl implements SymptomsDao {
    private Connection connection;

    public SymptomsDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }
    public List<Symptoms> find(int desiase_id) {
        List<Symptoms> symptoms = new LinkedList<Symptoms>();
        Statement statement=null;
        String query="SELECT symptoms.symptoms_id,symptoms.name,symptoms.more_information FROM symptoms, symptoms_desiase WHERE desiase_id=? AND symptoms_desiase.symptoms_id=symptoms.symptoms_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
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

    public List<Symptoms> findAll() {
        List<Symptoms> symptoms = new LinkedList<Symptoms>();
        Statement statement=null;
        String query="SELECT * FROM symptoms";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
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
        Statement statement=null;
        String query="SELECT * FROM symptoms WHERE symptoms_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
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

    public void putSymptoms(Symptoms symptoms) {
        String query = "INSERT INTO symptoms (name, more_information) VALUES(?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, symptoms.getName());
            preparedStatement.setString(2, symptoms.getMore_information());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении симптома");
            e.printStackTrace();
        }
    }

    public void deleteSymptoms(int symptoms_id) {
        String query = "DELETE FROM symptoms where symptoms_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, symptoms_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении симптома");
            e.printStackTrace();
        }
    }
    public void changeSymptoms(int symptoms_id, String new_name, String new_informations) {
        String query = "UPDATE symptoms SET more_information=?, name=? where symptoms_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, new_informations);
            preparedStatement.setString(2, new_name);
            preparedStatement.setInt(3, symptoms_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при изменении информации симптома");
            e.printStackTrace();
        }
    }
}
