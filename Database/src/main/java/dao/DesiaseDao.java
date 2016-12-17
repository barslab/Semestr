package dao;

import models.Desiase;

import java.util.List;

public interface DesiaseDao {
    Desiase find(int desiase_id);
    List<Desiase> findAll();
    int findDesiaseId(String name);
    void putDesiase(Desiase desiase);
    void putDesiaseSymptoms(int desiase_id, int symptoms_id);
    void putDesiaseProcedure(int desiase_id, int procedure_id);
//    перенести в интерфейс рекомендованных лекарств
    void putDesiaseRecomendedDrug(int desiase_id, int drug_id, int quantity);
    void deleteDesiase(int desiase_id);
}
