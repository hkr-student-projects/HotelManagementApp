package hkr.eduard.users;

public class Admin extends Person {
    private int adminCounter;

    public Admin(String firstName, String lastName, String personNumber, String phoneNumber, String adminPassword, int adminCounter, EmployeeRole role) {
        super(firstName, lastName, adminPassword, personNumber, phoneNumber, role);
        this.adminCounter = adminCounter;
    }

    public int getAdminCounter() {
        return adminCounter;
    }

    public void setAdminCounter(int adminCounter) {
        this.adminCounter = adminCounter;
    }

}
