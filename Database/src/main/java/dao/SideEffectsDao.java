package dao;

import models.Drug;
import models.SideEffects;

import java.util.List;

public interface SideEffectsDao {
//    поиск поб.эффекта по ид
    SideEffects findSideEffects(int sideEffects_id);
//    поиск всех побочных эффектов у лекарства
    List<SideEffects> find(int drug_id);
//    поиск всех поб.эффектов
    List<SideEffects> findAll();
//   поиск всех поб.эффектов по их ид
    List<SideEffects> findSideEffects(List<Integer> ids);
//  поиск всех ид у поб.эффектов
    List<Integer> findAllId();
//  поиск всех ид у поб.эффектов
    List<Integer> findId(List<SideEffects> sideEffectses);
//  удаление поб.эффектов у лекарства
    void deleteAllEffects(int drug_id);
}
