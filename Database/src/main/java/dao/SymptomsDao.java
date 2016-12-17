package dao;

import models.Symptoms;

import java.util.List;

public interface SymptomsDao {
    List<Symptoms> find(int desiase_id);
    List<Symptoms> findAll();
    Symptoms findSymptom(int symptom_id);
    void putSymptoms(Symptoms symptoms);
    void deleteSymptoms(int symptoms_id);
    void changeSymptoms(int symptoms_id, String new_name, String new_informations);
}
