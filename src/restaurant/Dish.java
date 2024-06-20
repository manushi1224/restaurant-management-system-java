package restaurant;

public class Dish {
    private final String name;
    private final double price;
    private final String description;

    public Dish(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " : " + description + " , " + price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}