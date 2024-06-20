package restaurant;

import java.io.*;
import java.util.*;

public class Menu {
    protected List<Dish> items;
    protected List<String> data;

    public Menu() {
        items = new ArrayList<>();
        data = new ArrayList<>();
        loadDataFromFile();
    }

    protected void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void addDish(Dish dish) {
        items.add(dish);
    }

    protected void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt", true))) {
            for (Dish dish : items) {
                writer.write(dish.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void searchDish() {
        try (BufferedReader reader = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a dish to be searched: ");
            String item = scanner.nextLine();
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(item)) {
                    System.out.println(line);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Sorry! Item is not available!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
