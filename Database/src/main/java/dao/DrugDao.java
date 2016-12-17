package dao;

import models.Drug;

import java.util.List;

public interface DrugDao {
    List<Drug> findAll();
    List<Drug> findDrugs(int desiase_id);
    Drug findDrug(int drug_id);
    List<String> findAllName();
    int findDrugId(String name);
    void putDrug(Drug drug);
    void putSideEffectsDrug(int drug_id, int side_effects_id);
    void deleteDrug(int id);
    void changeQuantityDrug(int id, int new_quantity);
}
