package dao;

import models.Procedures;

import java.sql.SQLException;
import java.util.List;

public interface ProceduresDao {
//    поиск всех процедур у болезни
    List<Procedures> find(int desiase_id);
//    поиск вспх процедур
    List<Procedures> findAll();
//    поиск процедуры по его ид
    Procedures findProcedure(int procedure_id);
//    поиск всех ид у процедур
    List<Integer> findAllId();
//    поиск всех ид по процедурам
    List<Integer> findIds(List<Procedures> procedures);
//    поиск всех процедур по их ид
    List<Procedures> findProcedures(List<Integer> procedures_id);
//    добавление процедуры
    void putProcedures(Procedures procedures) throws SQLException;
//    удаление процедуры по ид
    void deleteProcedures(int procedures_id);
//    удаление процедур к болезни
    void deleteProceduresOfDesiase(int desiase_id);
//    измение процедуры
    void changeProcedures(int procedures_id, String new_name, String new_recommendation) throws SQLException;
}
