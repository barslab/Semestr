package services;

import models.Substitutes;

import java.util.List;

public interface SubstitutesService {
    List<Substitutes> find(int drug_id);
}
