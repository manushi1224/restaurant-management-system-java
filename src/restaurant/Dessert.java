package restaurant;

public class Dessert extends Dish {
    private final String texture;

    public Dessert(String name, double price, String description, String texture) {
        super(name, price, description);
        this.texture = texture;
    }

    @Override
    public String toString() {
        return super.toString() + " , " + texture;
    }
}
