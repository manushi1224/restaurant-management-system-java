package restaurant;

import java.util.*;

public class Order extends Menu {
    private final List<Dish> orderItems;
    private final List<Double> prices;
    private final List<Integer> quantities;
    private double total;

    public Order() {
        orderItems = new ArrayList<>();
        prices = new ArrayList<>();
        quantities = new ArrayList<>();
        total = 0;
    }

    public void orderItem() {
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the dishes you want to order (comma separated): ");
        String[] items = scanner.nextLine().split(",");

        for (String item : items) {
            boolean itemFound = false;
            for (String line : data) {
                if (line.contains(item.trim())) {
                    System.out.println(line);
                    String[] parts = line.split(" : ");
                    String name = parts[0];
                    String[] subParts = parts[1].split(",");
                    double price = Double.parseDouble(subParts[1].trim());
                    String description = subParts[0].trim();
                    orderItems.add(new Dish(name, price, description));
                    System.out.print("Enter quantity for " + name + ": ");
                    int quantity = scanner.nextInt();
                    quantities.add(quantity);
                    prices.add(price);
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                System.out.println("Item " + item.trim() + " not found in the menu.");
            }
        }

        calculateTotal();
        scanner.nextLine(); // Consume the remaining newline
        scanner.close();
    }

    private void calculateTotal() {
        total = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            total += prices.get(i) * quantities.get(i);
        }
    }

    public void showBill() {
        System.out.println("--------------------------------------------------");
        System.out.println("Your Bill:");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < orderItems.size(); i++) {
            Dish dish = orderItems.get(i);
            int quantity = quantities.get(i);
            double price = prices.get(i);
            System.out.println(dish.getName() + " x " + quantity + " @ " + price + " = " + (price * quantity));
        }
        System.out.println("Subtotal: " + total);
        double cgst = total * 0.09;
        double sgst = total * 0.09;
        System.out.println("CGST (9%): " + cgst);
        System.out.println("SGST (9%): " + sgst);
        total += cgst + sgst;
        System.out.println("Total (incl. taxes): " + total);
    }
}