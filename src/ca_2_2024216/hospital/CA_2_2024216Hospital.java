/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_2_2024216.hospital;

/**
 *
 * @author sary_
 */

import java.util.*;

public class CA_2_2024216Hospital {

    /**
     * @param args the command line arguments
     */
         private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial generation of 10 random employees
        employees.addAll(EmployeeGenerator.generateRandomEmployees(10));

        boolean running = true;

        while (running) {
            System.out.println("\n---- Hospital Menu ----");
            for (int i = 0; i < Menu.values().length; i++) {
                System.out.println((i + 1) + ". " + Menu.values()[i]);
            }

            System.out.print("Choose an option (1-" + Menu.values().length + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Menu option = Menu.values()[choice - 1];

            switch (option) {
                case SORT:
                    Collections.sort(employees, Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
                    System.out.println("Employees sorted by name:");
                    employees.forEach(System.out::println);
                    break;

                case SEARCH:
                    System.out.print("Enter name to search: ");
                    String nameToSearch = scanner.nextLine();
                    int index = binarySearch(employees, nameToSearch);
                    if (index != -1) {
                        System.out.println("Found: " + employees.get(index));
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case ADD_RECORD:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.println("Choose Department:");
                    Department[] depts = Department.values();
                    for (int i = 0; i < depts.length; i++) {
                        System.out.println((i + 1) + ". " + depts[i]);
                    }
                    int deptIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    Department dept = depts[deptIndex];

                    System.out.println("Choose Manager Type:");
                    Manager[] managers = Manager.values();
                    for (int i = 0; i < managers.length; i++) {
                        System.out.println((i + 1) + ". " + managers[i]);
                    }
                    int managerIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    Manager manager = managers[managerIndex];

                    employees.add(new Employee(name, manager, dept));
                    System.out.println("Employee added.");
                    break;

                case GENERATE_RANDOM:
                    System.out.print("How many random employees to generate? ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    employees.addAll(EmployeeGenerator.generateRandomEmployees(count));
                    System.out.println(count + " employees generated.");
                    break;

                case EXIT:
                    running = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    public static int binarySearch(List<Employee> list, String name) {
        Collections.sort(list, Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int result = list.get(mid).getName().compareToIgnoreCase(name);
            if (result == 0) return mid;
            if (result < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}