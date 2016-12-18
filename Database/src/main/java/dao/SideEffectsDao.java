package dao;

import models.Drug;
import models.SideEffects;

import java.util.List;

public interface SideEffectsDao {
    SideEffects findSideEffects(int sideEffects_id);
    List<SideEffects> find(int drug_id);
    List<SideEffects> findAll();
    List<SideEffects> findSideEffects(List<Integer> ids);
    List<Integer> findAllId();
    List<Integer> findId(List<SideEffects> sideEffectses);
    void deleteAllEffects(int drug_id);
}
