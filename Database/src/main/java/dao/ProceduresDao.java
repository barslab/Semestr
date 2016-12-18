package dao;

import models.Procedures;

import java.sql.SQLException;
import java.util.List;

public interface ProceduresDao {
    List<Procedures> find(int desiase_id);
    List<Procedures> findAll();
    Procedures findProcedure(int procedure_id);
    List<Integer> findAllId();
    List<Integer> findIds(List<Procedures> procedures);
    List<Procedures> findProcedures(List<Integer> procedures_id);
    void putProcedures(Procedures procedures) throws SQLException;
    void deleteProcedures(int procedures_id);
    void deleteProceduresOfDesiase(int desiase_id);
    void changeProcedures(int procedures_id, String new_name, String new_recommendation);
}
