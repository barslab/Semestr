package dao;

import models.Desiase;

import java.sql.SQLException;
import java.util.List;

public interface DesiaseDao {
//    поиск болезни по его id
    Desiase find(int desiase_id);
//    поиск всех болезней
    List<Desiase> findAll();
//    поиск всех id у болезней
    List<Integer> findAllId();
//    поиск всех id у болезни по названию
    int findDesiaseId(String name);
//    добавление болезни
    void putDesiase(Desiase desiase) throws SQLException;
//    добавление симптома к болезни по id
    void putDesiaseSymptoms(int desiase_id, int symptoms_id);
//    добавление процедуры к болезни по id
    void putDesiaseProcedure(int desiase_id, int procedure_id);
//    перенести в интерфейс рекомендованных лекарств
//    добавление рекомендованных лекарств к болезни по id
    void putDesiaseRecomendedDrug(int desiase_id, int drug_id, int quantity);
//    удаление болезни по ид
    void deleteDesiase(int desiase_id);
//    изменение болезни по ид
    void changeDesiase(Desiase desiase) throws SQLException;
}
