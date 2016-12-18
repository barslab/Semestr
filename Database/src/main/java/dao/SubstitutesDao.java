package dao;
import models.Substitutes;
import java.util.List;

public interface SubstitutesDao {
//    поиск всех заменителей
    List<Substitutes> find(int drug_id);
//    добавление заменителя
    void put(int drug_id, int substitutes_drug_id);
//    удаление заменителя
    void deleteAllSubstitutes(int drug_id);
}