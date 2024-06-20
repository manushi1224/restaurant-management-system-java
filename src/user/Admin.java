package user;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Admin {
    private final String name;
    private final String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
        adminToFile();
    }

    private void adminToFile() {
        try (var writer = new BufferedWriter(new FileWriter("admin.txt"))) {
            writer.write(name + " : " + password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void login(String name, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("admin.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(name + " : " + password)) {
                    System.out.println("Welcome Back " + name);
                    choice();
                    return;
                }
            }
            System.out.println("Wrong Username / Password");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String nameString = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();
        System.out.print("Password: ");
        String passString = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("manager.txt", true))) {
            writer.write(nameString + " " + username + " " + email + " " + position + " " + passString + "\n");
            System.out.println("Details have been successfully added.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewManagerDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("manager.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void favoriteDish() {
        Map<String, Integer> dishCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("custreview.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dishes = line.split(" : ")[1].split(" , ");
                for (String dish : dishes) {
                    dishCount.put(dish, dishCount.getOrDefault(dish, 0) + 1);
                }
            }

            JFrame frame = new JFrame("Favorite Dishes");
            String[] columnNames = { "Dish", "Count" };
            Object[][] data = new Object[dishCount.size()][2];
            int index = 0;
            for (Map.Entry<String, Integer> entry : dishCount.entrySet()) {
                data[index][0] = entry.getKey();
                data[index][1] = entry.getValue();
                index++;
            }

            JTable table = new JTable(data, columnNames);
            JOptionPane.showMessageDialog(frame, new JScrollPane(table));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void choice() {
        Scanner scanner = new Scanner(System.in);
        int adminChoice = 0;

        while (adminChoice != 4) {
            System.out.println("1. Add manager details\n2. Show Details of Manager\n3. Show Favorite Dish\n4. Quit");
            adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1 -> addManager();
                case 2 -> viewManagerDetails();
                case 3 -> favoriteDish();
                case 4 -> System.out.println("Exiting admin menu...");
                default -> System.out.println("Incorrect Choice!");
            }
        }
    }
}