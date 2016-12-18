package dao;

import models.Drug;

import java.sql.SQLException;
import java.util.List;

public interface DrugDao {
//    поиск всех лекарсвт
    List<Drug> findAll();
//    поиск всех лекарств, рекомендованых к болезни
    List<Drug> findDrugs(int desiase_id);
//    поиск всех лекарств по списку их ид
    List<Drug> findDrugs(List<Integer> ids);
//    поиск списка лекарств по его заменителю
    List<Drug> findDrugsOfDrug(int drug_id);
//    поиск всех ид у заменителя
    List<Integer> findDrugsId(int drug_id);
//    поиск всех ид у лекарств
    List<Integer> findAllDrugsId();
//   поиск лекарства по ид
    Drug findDrug(int drug_id);
//    поиск всех имен лекарств
    List<String> findAllName();
//    поиск ид лекарства по имени
    int findDrugId(String name);
//    добавление лекарства
    void putDrug(Drug drug) throws SQLException;
//   добавление побочных эффектов к лекарству
    void putSideEffectsDrug(int drug_id, int side_effects_id);
//    удаление лекарства по ид
    void deleteDrug(int id);
//    изменение лекарства
    void changeDrug(Drug drug) throws SQLException;
//    изменение количества лекарства на складе
    void changeDrugQuantity(int drug_id, int new_quantity);
//    изменение количества лекарства(сумма старого и нового)
    void changeDrugQuantity(int desiase_id);
}
