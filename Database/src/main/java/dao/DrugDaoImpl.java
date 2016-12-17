package dao;

import factories.ConnectionFactory;
import models.Drug;
import models.Symptoms;
import services.SubstitutesService;
import services.SubstitutesServiceImpl;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DrugDaoImpl implements DrugDao {
    private Connection connection;
    public DrugDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }
    private SideEffectsDaoImpl sideEffectsDao = new SideEffectsDaoImpl(connection);
    private SubstitutesService substitutesService = new SubstitutesServiceImpl();
    public List<Drug> findAll() {
        Drug drug;
        List<Drug> drugs = new LinkedList<Drug>();
        Statement statement=null;
        String query="SELECT * FROM drug;";
        try {
            statement= connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                drugs.add(new Drug(rs.getInt("drug_id"), rs.getInt("quantity"), rs.getString("name"), rs.getString("form"), rs.getString("contraindications"),
                        rs.getString("overdose"), sideEffectsDao.find(rs.getInt("drug_id")), substitutesService.find(rs.getInt("drug_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    public List<Drug> findDrugs(int desiase_id) {
        List<Drug> drugs = new LinkedList<Drug>();
        Statement statement=null;
        String query="SELECT * FROM recomended_drug, drug WHERE desiase_id=? AND recomended_drug.desiase_id=drug.drug_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, desiase_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                drugs.add(new Drug(rs.getInt("drug_id"), rs.getInt("quantity"), rs.getString("name"), rs.getString("form"), rs.getString("contraindications"),
                        rs.getString("overdose"), sideEffectsDao.find(rs.getInt("drug_id")), substitutesService.find(rs.getInt("drug_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }
    public Drug findDrug(int drug_id) {
        Statement statement=null;
        String query="SELECT * FROM drug WHERE drug_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new Drug(rs.getInt("drug_id"), rs.getInt("quantity"), rs.getString("name"),
                        rs.getString("form"), rs.getString("contraindications"), rs.getString("overdose"),
                        sideEffectsDao.find(rs.getInt("drug_id")), substitutesService.find(rs.getInt("drug_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> findAllName() {
        List<String> names = new LinkedList<String>();
        Statement statement=null;
        String query="SELECT name FROM drug;";
        try {
            statement= connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public int findDrugId(String name) {
        Statement statement=null;
        String query="SELECT drug_id FROM drug WHERE name=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt("drug_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void putDrug(Drug drug) {
        String query = "INSERT INTO drug (name, quantity,form, contraindications, overdose) VALUES(?,?,?,?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,drug.getName());
            preparedStatement.setInt(2, drug.getQuantity());
            preparedStatement.setString(3, drug.getForm());
            preparedStatement.setString(4, drug.getContraindications());
            preparedStatement.setString(5, drug.getOverdose());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении лекарства");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void putSideEffectsDrug(int drug_id, int side_effects_id) {
        String query = "INSERT INTO drug_side (drug_id, side_effects_id) VALUES(?,?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            preparedStatement.setInt(2, side_effects_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении cимптома к блезни");
            e.printStackTrace();
        }
    }

    public void deleteDrug(int id) {
        String query = "DELETE FROM drug where drug_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении лекарства");
            e.printStackTrace();
        }
    }
    public void changeQuantityDrug(int id, int new_quantity) {
        String query = "UPDATE drug SET quantity=? where drug_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,new_quantity);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при изменении количества лекарства на складе");
            e.printStackTrace();
        }
    }
}
