package dao;

import models.Symptoms;

import java.util.List;

public interface SymptomsDao {
    List<Symptoms> find(int desiase_id);
    List<Symptoms> findAll();
    Symptoms findSymptom(int symptom_id);
    List<Integer> findAllId();
    List<Integer> findIds(List<Symptoms> symptoms);
    List<Symptoms> findSymptoms(List<Integer> symptoms_id);
    void putSymptoms(Symptoms symptoms);
    void deleteSymptoms(int symptoms_id);
    void deleteSymptomsOfDesiase(int desiase_id);
    void changeSymptoms(int symptoms_id, String new_name, String new_informations);
}
