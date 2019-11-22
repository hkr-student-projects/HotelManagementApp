package hkr;

public class Person {
    private String firstName;
    private String lastName;
    private String personNumber;
    private String phoneNumber;

    public Person(String firstName, String lastName, String personNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personNumber='" + personNumber + '\'' +
                ", phoneNumber='" + phoneNumber;
    }
}
