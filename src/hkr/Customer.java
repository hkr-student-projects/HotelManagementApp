package hkr;

public class Customer extends Person{
    private String customerPassword;
    private int customerCounter;

    public Customer(String firstName, String lastName, String personNumber, String phoneNumber, String customerPassword, int customerCounter) {
        super(firstName, lastName, personNumber, phoneNumber);
        this.customerPassword = customerPassword;
        this.customerCounter = customerCounter;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public int getCustomerCounter() {
        return customerCounter;
    }

    public void setCustomerCounter(int customerCounter) {
        this.customerCounter = customerCounter;
    }
}
