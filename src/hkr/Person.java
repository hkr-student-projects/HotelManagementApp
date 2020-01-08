package hkr;

public class Person {
    private String firstName;
    private String lastName;
    private String personNumber;
    private String phoneNumber;
    private String email;
    private String userId;

    public Person(String firstName, String lastName, String personNumber, String phoneNumber,String email,String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "First Name = " + firstName  +
                ", Last Name = " + lastName +
                ", Person Number = " + personNumber +
                ", Phone Number = " + phoneNumber+
                " Email = "+email+
                ", User Id = "+userId;
    }
}
