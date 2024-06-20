package restaurant;

public class MainDish extends Dish {
    private String cuisine;

    public MainDish(String name, double price, String description, String cuisine) {
        super(name, price, description);
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return super.toString() + " , " + cuisine;
    }
}
