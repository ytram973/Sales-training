package model;

public class Formation {
    private Long id;
    private String name;
    private String description;
    private int durationDays;
    private Mode mode;
    private double price;
    private String category;

    public Formation(Long id, String name, String description, int durationDays, Mode mode, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationDays = durationDays;
        this.mode = mode;
        this.price = price;
        this.category = category;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getDurationDays() {
        return durationDays;
    }
    public Mode getMode() {
        return mode;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
     @Override
    public String toString() {
    return "Formation{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", durationDays=" + durationDays +
            ", mode=" + mode +
            ", price=" + price +
            ", category='" + category + '\'' +
            '}';
}

    }

