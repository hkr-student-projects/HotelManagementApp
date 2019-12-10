package hkr.eduard.customer;

public class CustomerLogic {

    private final CustomerDao customerDao;

    public CustomerLogic(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
