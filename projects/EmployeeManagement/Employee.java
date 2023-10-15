package employeemanagement;

/**
 * A class to represent an employee in a system.
 *
 * @author Kayli
 */
public class Employee {

    private String name;
    private double pay;

    /**
     * Employee constructor.
     *
     * @param name The employee's first and last name.
     * @param pay The employee's hourly rate.
     */
    public Employee(String name, double pay) {
        this.name = name;
        this.pay = pay;
    }

    /**
     * Returns the Employee's name.
     *
     * @return The employee's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Employee's name to the specified String.
     *
     * @param name The employee's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Employee's hourly pay.
     *
     * @return the employee's hourly pay.
     */
    public double getPay() {
        return pay;
    }

    /**
     * Sets the Employee's pay to the specified double.
     *
     * @param pay The employee's new hourly pay.
     */
    public void setPay(double pay) {
        this.pay = pay;
    }

    /**
     * A toString method.
     *
     * @return A printout of employee information.
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Pay: $" + pay;
    }
}
