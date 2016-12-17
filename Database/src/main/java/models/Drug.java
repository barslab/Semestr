package models;

import java.util.List;

public class Drug {
    private int id, quantity;
    private String name, form, contraindications, overdose;
    private List<SideEffects> side_effects;
    private List<Substitutes> substitutes;

    public Drug(int id, int quantity, String name, String form, String contraindications, String overdose, List<SideEffects> side_effects) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.form = form;
        this.contraindications = contraindications;
        this.overdose = overdose;
        this.side_effects = side_effects;
    }

    public Drug(int quantity, String name, String form, String contraindications, String overdose) {
        this.quantity = quantity;
        this.name = name;
        this.form = form;
        this.contraindications = contraindications;
        this.overdose = overdose;
    }

    public Drug(int id, int quantity, String name, String form, String contraindications, String overdose) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.form = form;
        this.contraindications = contraindications;
        this.overdose = overdose;
    }

    public Drug(int id, int quantity, String name, String form, String contraindications, String overdose, List<SideEffects> side_effects, List<Substitutes> substitutes) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.form = form;
        this.contraindications = contraindications;
        this.overdose = overdose;
        this.side_effects = side_effects;
        this.substitutes = substitutes;
    }
    public List<Substitutes> getSubstitutes() {
        return substitutes;
    }
    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public String getContraindications() {
        return contraindications;
    }

    public String getOverdose() {
        return overdose;
    }

    public List<SideEffects> getSide_effects() {
        return side_effects;
    }
}
