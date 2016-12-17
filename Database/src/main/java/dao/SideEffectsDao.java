package dao;

import models.SideEffects;

import java.util.List;

public interface SideEffectsDao {
    List<SideEffects> find(int drug_id);
    List<SideEffects> findAll();

}
