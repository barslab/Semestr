package models;

import java.util.List;

public class Substitutes {
    private int id;
    private String name, distinction;
//    private List<Drug> drugs;
    private  Drug drug;

    public Substitutes(int id, Drug drug) {
        this.id = id;
        this.drug = drug;
    }
    //    public Substitutes(int id, String name, String distinction) {
//        this.id = id;
//        this.name = name;
//        this.distinction = distinction;
//    }
//
//    public Substitutes(int id, String name, String distinction, List<Drug> drugs) {
//        this.id = id;
//        this.name = name;
//        this.distinction = distinction;
//        this.drugs = drugs;
//    }

    public Substitutes(int id, String distinction, Drug drug) {
        this.id = id;
        this.distinction = distinction;
        this.drug = drug;
    }

    public Drug getDrug() {
        return drug;
    }

//    public List<Drug> getDrugs() {
//        return drugs;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistinction() {
        return distinction;
    }
}
