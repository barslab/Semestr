package models;
public class SideEffects {
    private int id;
    private String name;

    public SideEffects(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
