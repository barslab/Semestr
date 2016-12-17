package models;
public class Procedures {
    private int id;
    private String name, recommendations;

    public Procedures(String name, String recommendations) {
        this.name = name;
        this.recommendations = recommendations;
    }

    public Procedures(int id, String name, String recommendations) {
        this.id = id;
        this.name = name;
        this.recommendations = recommendations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRecommendations() {
        return recommendations;
    }
}
