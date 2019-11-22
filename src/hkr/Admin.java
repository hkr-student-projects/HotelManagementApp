package hkr;

public class Admin extends Person{
    private String adminPassword;
    private int adminCounter;

    public Admin(String firstName, String lastName, String personNumber, String phoneNumber, String adminPassword, int adminCounter) {
        super(firstName, lastName, personNumber, phoneNumber);
        this.adminPassword = adminPassword;
        this.adminCounter = adminCounter;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public int getAdminCounter() {
        return adminCounter;
    }

    public void setAdminCounter(int adminCounter) {
        this.adminCounter = adminCounter;
    }

    @Override
    public String toString() {
        return ""+super.toString()+"Admin Password='" + adminPassword + '\'' +
                ", adminCounter=" + adminCounter;
    }
}
