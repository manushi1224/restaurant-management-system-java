package user;

import restaurant.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Customer extends User {
    private Order order;

    public Customer(String name, String username, String email) {
        super(name, username, email);
        order = new Order();
    }

    public void greetCustomer() {
        System.out.println("\nWelcome, " + getName() + "!");
    }

    public void choice() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("1. View Menu\n2. Order\n3. View Bill\n4. Feedback\n5. Quit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> order.displayMenu();
                case 2 -> order.orderItem();
                case 3 -> order.showBill();
                case 4 -> feedback();
                case 5 -> System.out.println("Exiting customer menu...");
                default -> System.out.println("Incorrect Choice!");
            }
        }
    }

    private void feedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your favourite dishes (comma separated): ");
        String dishes = scanner.nextLine();
        String feedback = getName() + " : " + dishes;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("custreview.txt", true))) {
            writer.write(feedback + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
