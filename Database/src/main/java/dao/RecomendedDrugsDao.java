package dao;

import models.Drug;
import models.RecomendedDrugs;

import java.util.List;

public interface RecomendedDrugsDao {
    List<RecomendedDrugs> find(int desiase_id);
    void putRecomendedDrugs(RecomendedDrugs recomendedDrugs);
    void deleteRecomendedDrugs(int recomended_drugs_id);
//    убрать??
    void changeRecomendedDrugs(int desiase_id, int new_quantity);
}
