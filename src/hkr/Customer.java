package hkr;

public class Customer extends Person{
    private String customerPassword;

    public Customer(String firstName, String lastName, String personNumber, String phoneNumber,String email,String userId, String customerPassword) {
        super(firstName, lastName, personNumber, phoneNumber,email,userId);
        this.customerPassword = customerPassword;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                " customerPassword='" + customerPassword + '\'' +
                '}';
    }
}
