package hkr.eduard.users;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String password;
    private String personNumber;
    private String phoneNumber;
    private EmployeeRole role;

    public Person(String firstName, String lastName, String password,String personNumber, String phoneNumber, EmployeeRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

}