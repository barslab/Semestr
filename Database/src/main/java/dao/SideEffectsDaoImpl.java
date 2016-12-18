package dao;

import factories.ConnectionFactory;
import models.Drug;
import models.SideEffects;
import models.Symptoms;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class SideEffectsDaoImpl implements SideEffectsDao {
    private Connection connection;
    public SideEffectsDaoImpl(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public SideEffects findSideEffects(int sideEffects_id) {
        Statement statement=null;
        String query="SELECT * FROM side_effects WHERE side_effects_id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, sideEffects_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return new SideEffects(rs.getInt("side_effects_id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SideEffects> find(int drug_id) {
        List<SideEffects> sideEffects = new LinkedList<SideEffects>();
        Statement statement=null;
        String query="SELECT side_effects.side_effects_id, side_effects.name FROM side_effects, drug_side WHERE drug_id=? AND side_effects.side_effects_id=drug_side.side_effects_id";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sideEffects.add(new SideEffects(rs.getInt("side_effects_id"), rs.getString("name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sideEffects;
    }

    public List<SideEffects> findAll() {
        List<SideEffects> sideEffects = new LinkedList<SideEffects>();
        Statement statement=null;
        String query="SELECT * FROM side_effects";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sideEffects.add(new SideEffects(rs.getInt("side_effects_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sideEffects;
    }

    public List<SideEffects> findSideEffects(List<Integer> ids) {
        List<SideEffects> sideEffectses = new LinkedList<SideEffects>();
        for(int i=0;i<ids.size(); i++) {
            sideEffectses.add(findSideEffects(ids.get(i)));
        }
        return sideEffectses;
    }

    public List<Integer> findAllId() {
        List<Integer> sideEffectsId = new LinkedList<Integer>();
        Statement statement=null;
        String query="SELECT side_effects_id FROM side_effects";
        PreparedStatement preparedStatement;
        try {
            preparedStatement=connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sideEffectsId.add(rs.getInt("side_effects_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sideEffectsId;
    }

    public List<Integer> findId(List<SideEffects> sideEffectses) {
        List<Integer> ids = new LinkedList<Integer>();
        for (int i=0; i<sideEffectses.size(); i++) {
            ids.add(sideEffectses.get(i).getId());
        }
        return ids;
    }

    public void deleteAllEffects(int drug_id) {
        String query = "DELETE FROM drug_side WHERE drug_id=?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, drug_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении побочных эффектов лекарства");
            e.printStackTrace();
        }
    }
}
