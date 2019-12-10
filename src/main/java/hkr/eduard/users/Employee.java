package hkr.eduard.users;


public class Employee extends Person {
    private int employeeCounter;

    public Employee(String firstName, String lastName, String personNumber, String phoneNumber, String employeePassword, int employeeCounter, EmployeeRole role) {
        super(firstName, lastName, employeePassword, personNumber, phoneNumber, role);
        this.employeeCounter = employeeCounter;
    }

    public int getEmployeeCounter() {
        return employeeCounter;
    }

    public void setEmployeeCounter(int employeeCounter) {
        this.employeeCounter = employeeCounter;
    }

}
