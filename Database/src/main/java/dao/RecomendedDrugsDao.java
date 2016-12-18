package dao;

import models.Drug;
import models.RecomendedDrugs;

import java.util.List;

public interface RecomendedDrugsDao {
    List<RecomendedDrugs> find(int desiase_id);
    List<Integer> findIds(List<Drug> drugs);
    void putRecomendedDrugs(RecomendedDrugs recomendedDrugs);
    void deleteRecomendedDrugs(int desiase_id);
//    убрать??
    void changeRecomendedDrugs(int desiase_id, int new_quantity);
}
