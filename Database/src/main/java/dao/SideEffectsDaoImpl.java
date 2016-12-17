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
}
