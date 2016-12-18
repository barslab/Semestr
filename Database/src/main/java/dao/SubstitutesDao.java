package dao;
import models.Substitutes;
import java.util.List;

public interface SubstitutesDao {
    List<Substitutes> find(int drug_id);
    void put(int drug_id, int substitutes_drug_id);
    void deleteAllSubstitutes(int drug_id);
}