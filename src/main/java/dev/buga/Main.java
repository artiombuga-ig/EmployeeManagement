package dev.buga;

import dev.buga.config.AppConfig;
import dev.buga.utility.UserInputHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserInputHandler userInputHandler = context.getBean("userInputHandler", UserInputHandler.class);

        System.out.println("Welcome to Buga Employee Management System");
        String userInput;
        do {
            System.out.println("\nSelect an action:");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Department");
            System.out.println("3. Add Project");
            System.out.println("4. Link Employee to Department");
            System.out.println("5. Set Employee as Department Manager");
            System.out.println("6. Link Employee to Project");
            System.out.println("7. Show Employees");
            System.out.println("8. Show Departments");
            System.out.println("9. Show Projects");
            System.out.println("10. Remove Employee");
            System.out.println("11. Remove Department");
            System.out.println("12. Remove Project");
            System.out.println("0. Exit");

            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    userInputHandler.addEmployee();
                    break;
                case "2":
                    userInputHandler.addDepartment();
                    break;
                case "3":
                    userInputHandler.addProject();
                    break;
                case "4":
                    userInputHandler.linkEmployeeToDepartment();
                    break;
                case "5":
                    userInputHandler.setEmployeeAsManager();
                    break;
                case "6":
                    userInputHandler.linkEmployeeToProject();
                    break;
                case "7":
                    userInputHandler.showEmployees();
                    break;
                case "8":
                    userInputHandler.showDepartments();
                    break;
                case "9":
                    userInputHandler.showProjects();
                    break;
                case "10":
                    userInputHandler.removeEmployee();
                    break;
                case "11":
                    userInputHandler.removeDepartment();
                    break;
                case "12":
                    userInputHandler.removeProject();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Chose an option from the list.");
            }

        } while (!userInput.equals("0"));
    }
}