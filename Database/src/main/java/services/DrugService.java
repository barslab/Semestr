package services;

import models.Drug;

import java.util.List;

public interface DrugService {
    List<Drug> findAll();
    List<Drug> findDrugs(int desiase_id);
    Drug findDrug(int drug_id);
}
