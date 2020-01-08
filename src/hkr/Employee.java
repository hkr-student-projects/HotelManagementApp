package hkr;

public class Employee extends Person{
    private String employeePassword;
    private int employeeCounter;
    public enum Role{
        ADMIN,
        CLEANER,
        RECEPTIONIST;
    }
    public Role role;
    public Employee(String firstName, String lastName, String personNumber, String phoneNumber,String email,String userId, String employeePassword,Role role) {
        super(firstName, lastName, personNumber, phoneNumber,email,userId);
        this.employeePassword = employeePassword;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", Employee Password = " + employeePassword +
                ", Role = " + role;
    }
}
