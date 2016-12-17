package dao;

import factories.ConnectionFactory;
import models.Drug;
import models.SideEffects;
import models.Substitutes;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SubstitutesDaoImpl implements SubstitutesDao {
    private Connection connection;

    public SubstitutesDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }
    SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
    public List<Substitutes> find(int drug_id) {
        List<Substitutes> substitutes = new LinkedList<Substitutes>();
        String query="SELECT substitutes_id, distinction, substitutes_drug_id, drug_id FROM substitutes WHERE drug_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                substitutes.add(new Substitutes(rs.getInt("substitutes_id"), rs.getString("distinction"), findSubstitutesDrug(rs.getInt("substitutes_drug_id"))));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при поиске заменителей");
            e.printStackTrace();
        }
        return substitutes;
    }
    public void put(int drug_id, int substitutes_drug_id) {
        String query = "INSERT INTO substitutes (drug_id, substitutes_drug_id) VALUES(?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            preparedStatement.setInt(2, substitutes_drug_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении заменителя к лекарству");
            e.printStackTrace();
        }
    }

    public Drug findSubstitutesDrug(int substitutes_id) {
        String query="SELECT * FROM drug WHERE drug_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, substitutes_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new Drug(rs.getInt("drug_id"), rs.getInt("quantity"), rs.getString("name"),
                        rs.getString("form"), rs.getString("contraindications"), rs.getString("overdose"),
                        sideEffectsDao.find(rs.getInt("drug_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
