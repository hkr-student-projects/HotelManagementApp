package hkr.eduard;

import hkr.eduard.users.UserLogic;

import java.util.Scanner;

public class HotelApplication implements Runnable {

    private UserLogic userLogic;

    HotelApplication(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public void run() {
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("=====Welcome to our Hotel=====");
            System.out.println("Enter Your Choice");
            System.out.println("Are You :");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            int inp = input.nextInt();
            switch (inp) {
                case 1:
                    this.userLogic.login();
                    break;
                case 2:
                    break;

                default:
                    System.out.println("Unknown id.");
                    break;
            }
        }
    }
}
