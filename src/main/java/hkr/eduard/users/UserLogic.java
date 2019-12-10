package hkr.eduard.users;

import java.util.Scanner;

public class UserLogic {

    private final Scanner scanner;
    private final UserDao userDao;

    public UserLogic(UserDao userDao) {
        this.userDao = userDao;
        this.scanner = new Scanner(System.in);
    }

    public void login() {
        System.out.println("Please enter your username: ");
        String username = this.scanner.nextLine();

        System.out.println("PLease enter your password: ");
        String password = this.scanner.nextLine();

        this.userDao.login(username, password, person -> {
            if (person instanceof Admin) {
                this.admin((Admin) person);
            } else if (person instanceof Employee) {
                this.employee((Employee) person);
            } else {
                System.out.format("User %s attempted to login with incorrect role. %n", username);
            }
        });
    }

    private void admin(Admin admin) {
        System.out.format("Welcome %s %s.%n", admin.getFirstName(), admin.getLastName());
        while (true) {
            System.out.println("=====Admin=====");
            System.out.println("1. View all available rooms");
            int inp = this.scanner.nextInt();
            switch(inp){
                case 1:
                    //TODO: showRoom();
                    break;
                default:
                    System.out.println("You have entered incorrect choice!");
            }
        }
    }

    private void employee(Employee employee) {
        while (true) {
            System.out.format("Welcome %s %s.%n", employee.getFirstName(), employee.getLastName());
            String input = this.scanner.nextLine();
        }
    }

}
