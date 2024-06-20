package application;

import user.*;
import restaurant.*;
import java.util.*;

public class Start {
    private final Restaurant restaurant;
    private final Admin admin;
    private final Manager manager;
    private Customer customer;

    public Start(String name) {
        restaurant = new Restaurant(name);
        admin = new Admin("ManushiOza", "Manushi$2435");
        manager = new Manager();
        // Menu initialization not required here.
    }

    public void menu() {
        restaurant.describeRestaurant();
        restaurant.openRestaurant();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("Enter your role:\n1. Admin\n2. Manager\n3. Customer\n4. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> managerMenu();
                case 3 -> customerMenu();
                case 4 -> System.out.println("Thank you!");
                default -> System.out.println("Incorrect Choice!");
            }
        }
    }

    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 2) {
            System.out.println("1. Login\n2. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter your Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your Password: ");
                String password = scanner.nextLine();
                admin.login(username, password);
            }
        }
    }

    private void managerMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 2) {
            System.out.println("1. Login\n2. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter your Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your Password: ");
                String password = scanner.nextLine();
                manager.login(username, password);
            }
        }
    }

    private void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 2) {
            System.out.println("Welcome!\n1. Let's Order!\n2. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter your Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your Email: ");
                String email = scanner.nextLine();
                customer = new Customer(name, username, email);
                customer.greetCustomer();
                customer.choice();
            }
        }
    }

    public static void main(String[] args) {
        Start restaurantApp = new Start("When Do We Eat");
        restaurantApp.menu();
    }
}