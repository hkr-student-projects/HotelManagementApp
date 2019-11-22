package hkr;

public class Employee extends Person{
    private String employeePassword;
    private int employeeCounter;

    public Employee(String firstName, String lastName, String personNumber, String phoneNumber, String employeePassword, int employeeCounter) {
        super(firstName, lastName, personNumber, phoneNumber);
        this.employeePassword = employeePassword;
        this.employeeCounter = employeeCounter;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public int getEmployeeCounter() {
        return employeeCounter;
    }

    public void setEmployeeCounter(int employeeCounter) {
        this.employeeCounter = employeeCounter;
    }

    @Override
    public String toString() {
        return ""+super.toString()+"Employee Password='" + employeePassword + '\'' +
                ", employeeCounter=" + employeeCounter;
    }
}
