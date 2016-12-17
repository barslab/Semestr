package dao;

import models.Procedures;

import java.util.List;

public interface ProceduresDao {
    List<Procedures> find(int desiase_id);
    List<Procedures> findAll();
    Procedures findProcedure(int procedure_id);
    void putProcedures(Procedures procedures);
    void deleteProcedures(int procedures_id);
    void changeProcedures(int procedures_id, String new_name, String new_recommendation);
}
