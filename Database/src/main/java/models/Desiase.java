package models;

import java.util.List;

public class Desiase {
    private int id, average_age;
    private float chance_desiase_man, chance_desiase_women;
    private String name;
    private List<Procedures> procedures;
    private List<Symptoms> symptoms;
    private List<RecomendedDrugs> recomendedDrugs;

    public Desiase(int id, int average_age, float chance_desiase_man, float chance_desiase_women, String name) {
        this.id = id;
        this.average_age = average_age;
        this.chance_desiase_man = chance_desiase_man;
        this.chance_desiase_women = chance_desiase_women;
        this.name = name;
    }

    public Desiase(int id, int average_age, float chance_desiase_man, float chance_desiase_women, String name, List<Procedures> procedures, List<Symptoms> symptoms) {
        this.id = id;
        this.average_age = average_age;
        this.chance_desiase_man = chance_desiase_man;
        this.chance_desiase_women = chance_desiase_women;
        this.name = name;
        this.procedures = procedures;
        this.symptoms = symptoms;
    }

    public Desiase(int id, int average_age, float chance_desiase_man, float chance_desiase_women, String name, List<Procedures> procedures, List<Symptoms> symptoms, List<RecomendedDrugs> recomendedDrugs) {
        this.id = id;
        this.average_age = average_age;
        this.chance_desiase_man = chance_desiase_man;
        this.chance_desiase_women = chance_desiase_women;
        this.name = name;
        this.procedures = procedures;
        this.symptoms = symptoms;
        this.recomendedDrugs = recomendedDrugs;
    }

    public List<Procedures> getProcedures() {
        return procedures;
    }

    public List<RecomendedDrugs> getRecomendedDrugs() {
        return recomendedDrugs;
    }

    public List<Symptoms> getSymptoms() {
        return symptoms;
    }

    public int getId() {
        return id;
    }

    public int getAverage_age() {
        return average_age;
    }

    public float getChance_desiase_man() {
        return chance_desiase_man;
    }

    public float getChance_desiase_women() {
        return chance_desiase_women;
    }

    public String getName() {
        return name;
    }
}
