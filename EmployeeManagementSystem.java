import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private String designation;
    private double salary;

    // Constructor
    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    // Method to display employee details
    public void displayEmployee() {
        System.out.println("Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }

    // Method to save employee details to file
    public void saveEmployeeToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(id + "," + name + "," + designation + "," + salary);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }
}

public class EmployeeManagementSystem {
    private static final String FILENAME = "employees.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        do {
            // Menu
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (option) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (option != 3);
    }

    // Method to add an employee
    public static void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            Employee employee = new Employee(id, name, designation, salary);
            employee.saveEmployeeToFile(FILENAME);
            System.out.println("Employee added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to display all employees
    public static void displayEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            System.out.println("Employee Details:");
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                int id = Integer.parseInt(employeeData[0]);
                String name = employeeData[1];
                String designation = employeeData[2];
                double salary = Double.parseDouble(employeeData[3]);

                Employee employee = new Employee(id, name, designation, salary);
                employee.displayEmployee();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No employees found. Please add some employees first.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
