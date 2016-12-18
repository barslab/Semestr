package dao;

import models.Drug;
import models.RecomendedDrugs;

import java.util.List;

public interface RecomendedDrugsDao {
//   поиск всех рекомендованных лекарств к болезни
    List<RecomendedDrugs> find(int desiase_id);
//   поиск количества рекомендого лекарства у болезни
    int find(int desiase_id, String drug_name);
//   поиск всех ид у болезней
    List<Integer> findIds(List<Drug> drugs);
//  добавление рекомендованныз лекарств
    void putRecomendedDrugs(RecomendedDrugs recomendedDrugs);
//    удаление рек.лекарств у болезни
    void deleteRecomendedDrugs(int desiase_id);
//   удаление рек.лекарства у болезни по названию
    void deleteRecomendedDrugs(int desiase_id, String drug_name);
}
