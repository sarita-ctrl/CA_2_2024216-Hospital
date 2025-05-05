/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_2_2024216.hospital;

/**
 *
 * @author sary_
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;

public class EmployeeGenerator {
    
    private static final String[] FIRST_NAMES = {
        "Emma", "Liam", "Olivia", "Noah", "Ava", "James", "Sophia", "Lucas", "Mia", "Ethan"
    };
    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Brown", "Jones", "Garcia", "Martinez", "Davis", "Lopez", "Anderson", "Taylor"
    };
    private static final String[] DEPARTMENTS = {
        "Emergency", "Pediatrics", "Radiology", "Surgery", "Administration"
    };

    private static final Random rand = new Random();

    public static List<Employee> generateRandomEmployees(int count) {
    List<Employee> employees = new ArrayList<>();
    Set<String> usedNames = new HashSet<>();

    while (employees.size() < count) {
        String name = randomName();
        if (usedNames.contains(name)) continue;
        usedNames.add(name);

Department department = Department.values()[rand.nextInt(Department.values().length)];
        Manager manager = Manager.values()[rand.nextInt(Manager.values().length)];
        employees.add(new Employee(name, manager, department));
    }

    return employees;
}


    private static String randomName() {
        return FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)] + " " +
               LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
    }
    
    // Save to file
    public static void saveEmployeesToFile(List<Employee> employees, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Employee emp : employees) {
                writer.write(emp.getName() + "," + emp.getManager() + "," + emp.getDepartment() + "\n");
            }
            System.out.println(employees.size() + " employees saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}
