package restaurant;

public class Restaurant {
    private final String name;

    public Restaurant(String name) {
        this.name = name;
    }

    public void describeRestaurant() {
        System.out.println("Welcome to " + name + "!");
        System.out.println("Our restaurant offers a variety of delicious dishes to satisfy your taste buds.");
    }

    public void openRestaurant() {
        System.out.println(name + " is now open!");
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("When Do We Eat");
        restaurant.describeRestaurant();
        restaurant.openRestaurant();
    }
}