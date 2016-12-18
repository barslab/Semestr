package Util;

import dao.SymptomsDaoImpl;
import factories.ConnectionFactory;
import models.Drug;

import java.sql.Connection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class OtherMethods {
    private Connection connection;
    public OtherMethods(Connection connection) {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public List<Integer> deleter(List<Integer> allId, List<Integer> deletingId, int own_id) {
        deletingId.add(own_id);
        for (int i=0; i<deletingId.size(); i++) {
            allId.remove(deletingId.get(i));
        }
        return allId;
    }
    public List<Integer> deleter(List<Integer> allId, List<Integer> deletingId) {
        for (int i=0; i<deletingId.size(); i++) {
            allId.remove(deletingId.get(i));
        }
        return allId;
    }
    public boolean check(int desiase_id, String[] symptoms_id) {
        SymptomsDaoImpl symptomsDao = new SymptomsDaoImpl(connection);
        HashSet<Integer> symptoms_ids = new HashSet<Integer>();
        for(int i=0;i<symptoms_id.length;i++) {
            symptoms_ids.add(Integer.parseInt(symptoms_id[i]));
        }
        if (symptomsDao.findIdOfDesiase(desiase_id).containsAll(symptoms_ids)) {
            return true;
        }
        else {
            return false;
        }
    }
}
