package models;
public class Symptoms {
    private int id;
    private String name, more_information;

    public Symptoms(String name, String more_information) {
        this.name = name;
        this.more_information = more_information;
    }
    public Symptoms(int id, String name, String more_information) {
        this.id = id;
        this.name = name;
        this.more_information = more_information;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMore_information() {
        return more_information;
    }
}
