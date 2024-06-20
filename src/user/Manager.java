package user;

import restaurant.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Manager extends Menu {
    private String username;
    private String password;

    public Manager() {
        super();
    }

    public void login(String username, String password) {
        this.username = username;
        this.password = password;

        try (BufferedReader reader = new BufferedReader(new FileReader("manager.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] managerData = line.split(" ");
                if (managerData[1].equals(username) && managerData[4].equals(password)) {
                    System.out.println("Login Successful!");
                    greetUser();
                    choice();
                    return;
                }
            }
            System.out.println("Incorrect username or password.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void greetUser() {
        System.out.println("\nWelcome back, " + username + "!");
    }

    public void addToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your dish name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your dish price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter description of your dish: ");
        String description = scanner.nextLine();
        System.out.print("Is it cuisine or dessert? (c/d): ");
        char choice = scanner.next().charAt(0);
        scanner.nextLine(); // Consume newline

        if (choice == 'c') {
            System.out.print("Enter cuisine of your dish: ");
            String cuisine = scanner.nextLine();
            addDish(new MainDish(name, price, description, cuisine));
        } else if (choice == 'd') {
            System.out.print("Enter texture of your dessert: ");
            String texture = scanner.nextLine();
            addDish(new Dessert(name, price, description, texture));
        }
        saveToFile();
    }

    public void removeFromMenu() {
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("menu.txt")));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the dish name to be removed: ");
            String name = scanner.nextLine();

            lines.removeIf(line -> line.contains(name));
            Files.write(Paths.get("menu.txt"), lines);

            System.out.println(name + " has been successfully removed!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMenu() {
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("menu.txt")));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the dish name to be updated: ");
            String oldName = scanner.nextLine();
            System.out.print("Enter new dish name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            System.out.print("Is it cuisine or dessert? (c/d): ");
            char choice = scanner.next().charAt(0);
            scanner.nextLine(); // Consume newline

            String updatedDish;
            if (choice == 'c') {
                System.out.print("Enter new cuisine: ");
                String cuisine = scanner.nextLine();
                updatedDish = new MainDish(newName, price, description, cuisine).toString();
            } else {
                System.out.print("Enter new texture: ");
                String texture = scanner.nextLine();
                updatedDish = new Dessert(newName, price, description, texture).toString();
            }

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(oldName)) {
                    lines.set(i, updatedDish);
                }
            }
            Files.write(Paths.get("menu.txt"), lines);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void choice() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println(
                    "1. Add a dish to menu\n2. Remove dish from menu\n3. Update Menu\n4. Search Dish\n5. Display Menu\n6. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addToMenu();
                case 2 -> removeFromMenu();
                case 3 -> updateMenu();
                case 4 -> searchDish();
                case 5 -> displayMenu();
                case 6 -> System.out.println("Exiting manager menu...");
                default -> System.out.println("Incorrect Choice!");
            }
        }
    }
}
