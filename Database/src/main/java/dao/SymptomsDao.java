package dao;

import models.Symptoms;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public interface SymptomsDao {
//    поиск всех симптомов у болезни
    List<Symptoms> find(int desiase_id);
//    поиск всех ид у симптомов у болезни
    HashSet<Integer> findIdOfDesiase(int desiase_id);
//    поиск всех симптомов
    List<Symptoms> findAll();
//    поиск симптома по ид
    Symptoms findSymptom(int symptom_id);
//    поиск всех ид у симптомов
    List<Integer> findAllId();
//    поиск всех ид у симптомов
    List<Integer> findIds(List<Symptoms> symptoms);
//    поиск всех симптово по ид
    List<Symptoms> findSymptoms(List<Integer> symptoms_id);
//    добавление симптопа
    void putSymptoms(Symptoms symptoms) throws SQLException;
//    удаление симптома
    void deleteSymptoms(int symptoms_id) throws SQLException;
//    удалиение симптомов у болезни
    void deleteSymptomsOfDesiase(int desiase_id);
//    изменение симптмома
    void changeSymptoms(int symptoms_id, String new_name, String new_informations) throws SQLException;
}
