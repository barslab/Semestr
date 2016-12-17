package models;

import java.util.List;

public class RecomendedDrugs {
    private int id, quantity;
    private List<Drug> drugs;
    private Drug drug;

    public RecomendedDrugs(int id, int quantity, List<Drug> drugs) {
        this.id = id;
        this.quantity = quantity;
        this.drugs = drugs;
    }

    public RecomendedDrugs(int id, int quantity, Drug drug) {
        this.id = id;
        this.quantity = quantity;
        this.drug = drug;
    }

    public Drug getDrug() {
        return drug;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
    public List<Drug> getDrugs() {
        return drugs;
    }
}
