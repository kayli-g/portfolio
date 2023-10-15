package employeemanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A main method class for running an employee management system.
 *
 * @author Kay
 */
public class Main {

    private static final Scanner SCAN = new Scanner(System.in);
    private static int userInput = 0;

    public static void main(String[] args) throws InputMismatchException {

        ManagementSystem system = new ManagementSystem();

        while (userInput != 6) {

            /*
            While user has not entered the exit case, print menu and get
            user input.
             */
            system.printMenu();
            
            try {
            userInput = SCAN.nextInt();
            }
            
            catch (InputMismatchException ex) {
            }
            SCAN.nextLine();
            System.out.println("");

            switch (userInput) {
                case 1:
                    //Add an employee by prompting name and pay
                    double payChoice;
                    System.out.println("What is the employee's name?\n");
                    String nameChoice = SCAN.nextLine();
                    System.out.println("\nPlease enter the employee's hourly pay.\n");
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            payChoice = SCAN.nextDouble();
                            System.out.println("");
                            system.addEmployee(nameChoice, payChoice);
                            System.out.println(nameChoice + " has been added to the roster."
                                    + "\nThis employee is paid $" + payChoice + ".\n");
                            validInput = true;
                        } catch (InputMismatchException ex) {
                            System.out.println("\nPlease enter a valid number.\n");
                            SCAN.nextLine();
                        }

                    }

                    break;

                case 2:
                    //View a list of current employees and choose one to remove
                    if (system.getRoster().isEmpty()) {
                        System.out.println("You have no employees in the system.\n");
                        break;
                    }
                    system.printRoster();
                    System.out.println("\nPlease enter the name of the person that "
                            + "\nyou would like to remove.");
                    SCAN.nextLine();
                    System.out.println("");
                    String removeThem = SCAN.nextLine();
                    System.out.println("");
                    //If the employee name is valid and therefore successfully
                    //removed from the list, say so
                    if (system.removeEmployee(removeThem)) {
                        System.out.println("Employee has been removed.\n");

                    } else {
                        //Otherwise, try again
                        System.out.println("Employee not found. Please try again.");
                    }
                    break;

                case 3:
                    //View list of current employees
                    system.printRoster();
                    break;

                case 4:
                    //View a list of current employees, enter the name of
                    //the employee you would like to edit
                    if (system.getRoster().isEmpty()) {
                        System.out.println("You have no employees in the system.\n");
                        break;
                    }
                    system.printRoster();
                    System.out.println("Whose file would you like to change?\n");
                    String name = SCAN.nextLine();
                    System.out.println("");
                    //Attempt to locate the employee
                    Employee e = system.findEmployee(name);
                    if (e != null) {
                        //Employee found, user decides what they would like to change
                        System.out.println("What would you like to change?"
                                + "\n1. Their name"
                                + "\n2. Their pay");
                        int choice = SCAN.nextInt();
                        switch (choice) {
                            case 1:
                                //Change name
                                system.changeName(e, SCAN);
                                break;
                            case 2:
                                //Change pay
                                system.changePay(e, SCAN);
                                break;
                            default:
                                //Invalid input
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    } else {
                        System.out.println("\nEmployee not found. Please try again.\n");
                    }
                    break;

                case 5:
                    //View total pay of all employees
                    System.out.println("The total pay for all employees is $" + system.sumPay());
                    break;
                    
                case 6:
                    //End program
                    break;

                default:
                    System.out.println("Command not recognized. Please enter a"
                            + "\nnumber between 1 and 6.\n");
            }

        }

        System.out.println("Thank you for using our system!");

    }

}
