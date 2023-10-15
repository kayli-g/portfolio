package employeemanagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class to represent an employee management system.
 *
 * @author Kay
 */
public class ManagementSystem {

    private static final ArrayList<Employee> ROSTER = new ArrayList<Employee>();

    /**
     * Returns the Employee roster ArrayList.
     *
     * @return The Employee roster.
     */
    public ArrayList<Employee> getRoster() {
        return ROSTER;
    }

    /**
     * Adds an employee to the roster.
     *
     * @param name The employee's name.
     * @param pay The employee's hourly pay.
     */
    public void addEmployee(String name, double pay) {
        Employee e = new Employee(name, pay);
        ROSTER.add(e);
    }

    /**
     * Prints information about every employee on the roster.
     */
    public void printRoster() {
        System.out.println("Here is a list of your current employees:");
        System.out.println("");
        for (Employee e : ROSTER) {
            System.out.println(e.toString() + "\n");
        }
    }

    /**
     * Removes an employee from the roster.
     *
     * @param name The name of the employee to remove.
     * @return True if the employee has been found and removed, false otherwise.
     */
    public boolean removeEmployee(String name) {
        for (Employee e : ROSTER) {
            if (e.getName().equals(name)) {
                ROSTER.remove(e);
                return true;
            }
        }
        return false;

    }

    /**
     * Finds an employee in the system.
     *
     * @param name The employee's name to look for.
     * @return The employee with the matching name or null if not found.
     */
    public Employee findEmployee(String name) {
        for (Employee e : ROSTER) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        return null;
    }

    /**
     * Adds together hourly pay for all employees.
     *
     * @return The total pay for all employees.
     */
    public double sumPay() {
        double totalPay = 0;
        for (Employee e : ROSTER) {
            totalPay += e.getPay();
        }

        return totalPay;
    }

    /**
     * Prints menu for the system.
     */
    public void printMenu() {
        System.out.println("Welcome to your employee management system!"
                + "\nHere are your options:"
                + "\n\n1. Add an employee to the system."
                + "\n\n2. Remove an employee from the system."
                + "\n\n3. View all employees."
                + "\n\n4. Change employee information."
                + "\n\n5. View total pay for all employees."
                + "\n\n6. Exit.\n");
    }

    /**
     * Changes an employee's name.
     *
     * @param e The employee that needs their name changed.
     * @param scan The Scanner to use for input.
     */
    public void changeName(Employee e, Scanner scan) {
        System.out.println("\nWhat would you like their new name to be?\n");
        scan.nextLine();
        String newName = scan.nextLine();
        e.setName(newName);
        System.out.println("Name has been changed.");
    }

    /**
     * Changes an employee's pay.
     *
     * @param e The employee who needs their pay changed.
     * @param scan The Scanner to use for input.
     */
    public void changePay(Employee e, Scanner scan) {
        System.out.println("\nWhat would you like their new pay to be?\n");
        boolean validInput = false;
        double newPay = 0;
        while (!validInput) {
            try {
                newPay = scan.nextDouble();
                validInput = true;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
        System.out.println("");
        e.setPay(newPay);
        System.out.println("Pay has been changed.");
    }

}
