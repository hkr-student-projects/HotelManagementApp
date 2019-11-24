package hkr;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import static java.lang.System.*;

public class Main {
    int empCounter = 0;
    int admCounter = 0;
    int roomCounter = 0;
    int bookingCounter = 0;
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.showMenu();
    }
    public void showMenu(){
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("=====Welcome to our Hotel=====");
            System.out.println("Enter Your Choice");
            System.out.println("Are You :");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Customer");
            System.out.println("4. Exit");
            int inp = input.nextInt();
            switch (inp) {
                case 1:
                    adminVerify();
                    break;
                case 2:
                    employeeverify();
                    break;
                case 3:
                    //customerveify();
                    break;
            }
        }
    }
    public void adminVerify() {
        try {
            Scanner input = new Scanner(System.in);
            out.println("Enter the Admin Id");
            int st = input.nextInt();
            input.nextLine();
            String str = "Admin Id ="+st;
            Scanner scanner = new Scanner(new File("Admin.txt"));
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        out.println("Enter the password");
                        String pass = input.nextLine();
                        String sa = "Admin Password ="+pass;
                        if(s.contains(sa)){
                            out.println("Welcome!");
                            adminMenu();
                            break;
                        }
                        else{
                            out.println("Access Denied!");
                            showMenu();
                            break;
                        }
                    }
                }
                scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    public void adminMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("=====Admin=====");
        System.out.println("1. View all available rooms");
        System.out.println("2. View all Employees");
        System.out.println("3. View all Bookings");
        System.out.println("4. View all Admins");
        System.out.println("5. Create a new room");
        System.out.println("6. Create a new Employee");
        System.out.println("7. Create a new Admin");
        System.out.println("8. Remove a room");
        System.out.println("9. Remove a employee");
        System.out.println("10. Remove a admin");
        System.out.println("11. Remove a Booking");
        System.out.println("12. Exit");
        int inp = input.nextInt();
        switch(inp){
            case 1:
                showRoom();
                break;
            case 2:
                showEmployee();
                break;
            case 3:
                showBookings();
                break;
            case 4:
                showAdmins();
                break;
            case 5:
                createRoom();
                break;
            case 6:
                createEmployee();
                break;
            case 7:
                createAdmin();
                break;
            case 8:
                removeRoom();
                break;
            case 9:
                removeEmployee();
                break;
            case 10:
                removeAdmin();
                break;
            case 11:
                removeBooking();
                break;
            case 12:
                showMenu();
                break;
            default:
                out.println("You have entered incorrect choice!");
        }
    }
    public void createRoom(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of room");
        String room = input.nextLine();
        System.out.println("Does it have a booking(Y or N)");
        String booking = input.nextLine();
        roomCounter++;
        if(booking.equalsIgnoreCase("y")){
            bookingCounter++;
            booking = "Yes";
        }else if(booking.equalsIgnoreCase("n")){
            booking = "No";
        }
        Hotel hotel = new Hotel(room,booking,bookingCounter);
        try {
            FileWriter fw = new FileWriter("Rooms.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
                pw.format("Room No =%s ",hotel.getAvailableRooms());
                pw.format("Booking =%s%n",hotel.getBooking());
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
        adminMenu();
    }
    public void showRoom(){
        System.out.println("The available rooms are:"+roomCounter);
        try{
            FileReader fr = new FileReader("Rooms.txt");
            BufferedReader br = new BufferedReader(fr);

            String room;
            while((room = br.readLine()) != null){
                out.println(room+"\n");
            }
        }catch(IOException e){
            out.println("File Not Found!");
        }
    }
    public void createEmployee(){
        try {
            String str = "Employee Id =";
            int line = 0;
            Scanner scanner = new Scanner(new File("Employee.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    line++;

                }
            }
            out.println(line);
            empCounter = line;
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first name of Employee");
        String fname = input.nextLine();
        System.out.println("Enter the last name of Employee");
        String lname = input.nextLine();
        System.out.println("Enter the Person Number of Employee");
        String pNumber = input.nextLine();
        System.out.println("Enter the Phone Number of Employee");
        String phNumber = input.nextLine();
        System.out.println("Enter the Password of Employee");
        String pass = input.nextLine();
        System.out.println("Enter the role(recepionist,cleaner,waiter)");
        String role = input.nextLine();
        empCounter++;
        Employee employee1 = new Employee(fname,lname,pNumber,phNumber,pass,empCounter,role);
        try {
            FileWriter fw = new FileWriter("Employee.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.format("Employee Id =%d ",employee1.getEmployeeCounter());
            pw.format("Employee First Name =%s ",employee1.getFirstName());
            pw.format("Employee Last Name =%s ",employee1.getLastName());
            pw.format("Employee Role =%s ",employee1.getRole());
            pw.format("Employee Personal Number =%s ",employee1.getPersonNumber());
            pw.format("Employee Phone Number =%s ",employee1.getPhoneNumber());
            pw.format("Employee Password =%s%n",employee1.getEmployeePassword());
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
        showMenu();
    }
    public void showEmployee(){
        try{
            FileReader fr = new FileReader("Employee.txt");
            BufferedReader br = new BufferedReader(fr);

            String emp;
            while((emp = br.readLine()) != null){
                out.println(emp+"\n");
            }
        }catch(IOException e){
            out.println("File Not Found!");
        }
        adminMenu();
    }
    public void createAdmin(){
        try {
            String str = "Admin Id =";
            int line = 0;
            Scanner scanner = new Scanner(new File("Admin.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    line++;
                }
            }
            out.println(line);
            admCounter = line;
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first name of Admin");
        String fname = input.nextLine();
        System.out.println("Enter the last name of Admin");
        String lname = input.nextLine();
        System.out.println("Enter the Person Number of Admin");
        String pNumber = input.nextLine();
        System.out.println("Enter the Phone Number of Admin");
        String phNumber = input.nextLine();
        System.out.println("Enter the Password of Admin");
        String pass = input.nextLine();
        admCounter++;
        Admin admin2 = new Admin(fname,lname,pNumber,phNumber,pass,admCounter);
        try {
            FileWriter fw = new FileWriter("Admin.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
                pw.format("Admin Id =%d ",admin2.getAdminCounter());
                pw.format("Admin First Name =%s ",admin2.getFirstName());
                pw.format("Admin Last Name =%s ",admin2.getLastName());
                pw.format("Admin Personal Number =%s ",admin2.getPersonNumber());
                pw.format("Admin Phone Number =%s ",admin2.getPhoneNumber());
                pw.format("Admin Password =%s%n",admin2.getAdminPassword());
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
        adminMenu();
    }
    public void showAdmins(){
        try{
            FileReader fr = new FileReader("Admin.txt");
            BufferedReader br = new BufferedReader(fr);

            String adm;
            while((adm = br.readLine()) != null){
                out.println(adm+"\n");
            }
        }catch(IOException e){
            out.println("File Not Found!");
        }
        adminMenu();
    }
    public void showBookings() {
        try {
            String str = "Booking =Yes";
            Scanner scanner = new Scanner(new File("Rooms.txt"));
            if (!scanner.equals(str)) {
                out.println("There is no booking");
            } else {
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        out.println("" + s);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    public void removeRoom() {
        try {
            out.println("Enter the room No you want to remove");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            File oldF = new File("Rooms.txt");
            File newF = new File("tp.txt");
            Scanner scanner = new Scanner(oldF);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.contains(str)) {
                    out.println(""+s);
                    FileWriter fw = new FileWriter(newF, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println("" + s);
                    pw.close();
                }
            }scanner.close();
            oldF.delete();
            File tempF = new File("Rooms.txt");
            newF.renameTo(tempF);
        }catch(IOException e){
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    public void removeAdmin() {
        try {
            out.println("Enter the Admin name you want to remove");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            File oldFile = new File("Admin.txt");
            File newFile = new File("DeletedAdmins.txt");
            Scanner scanner = new Scanner(oldFile);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.contains(str)) {
                    out.println(""+s);
                    FileWriter fw = new FileWriter(newFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println("" + s);
                    pw.close();
                }
            }scanner.close();
            File tempFile = new File("Admin.txt");
            newFile.renameTo(tempFile);
        }catch(IOException e){
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    public void removeEmployee(){
        try {
            out.println("Enter the Employee name you want to remove");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            File oldFile = new File("Employee.txt");
            File newFile = new File("DeletedEmployee.txt");
            Scanner scanner = new Scanner(oldFile);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.contains(str)) {
                    out.println(""+s);
                    FileWriter fw = new FileWriter(newFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println("" + s);
                    pw.close();
                }
            }scanner.close();
            File tempFile = new File("Employee.txt");
            newFile.renameTo(tempFile);
        }catch(IOException e){
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    public void removeBooking() {
        try {
            String str = "Booking =Yes";
            Scanner scanner = new Scanner(new File("Rooms.txt"));
            if (!scanner.equals(str)) {
                out.println("There is no booking");
                adminMenu();
            } else {
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        out.println("" + s);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        out.println("Done");
        try {
            out.println("Enter the Room Number you want to remove the booking");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            File oldFile = new File("Rooms.txt");
            File newFile = new File("DeletedRooms.txt");
                Scanner scanner = new Scanner(oldFile);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (!s.contains(str)) {
                        out.println("" + s);
                        FileWriter fw = new FileWriter(newFile, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println("" + s);
                        pw.close();
                    }
                }
                scanner.close();
                File tempFile = new File("Rooms.txt");
                newFile.renameTo(tempFile);
            } catch(IOException e){
                out.println("File Not Found!");
            }
            out.println("Done");
        }
        public void employeeverify(){
            try {
                Scanner input = new Scanner(System.in);
                out.println("Enter the Employee Id");
                int st = input.nextInt();
                input.nextLine();
                String str = "Employee Id ="+st;
                Scanner scanner = new Scanner(new File("Employee.txt"));
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        out.println("Enter the password");
                        String pass = input.nextLine();
                        String sa = "Employee Password ="+pass;
                        if(s.contains(sa)){
                            out.println("Welcome!");
                            adminMenu();
                            break;
                        }
                        else{
                            out.println("Access Denied!");
                            showMenu();
                            break;
                        }
                    }
                }
                scanner.close();
            } catch (IOException e) {
                out.println("File Not Found!");
            }
            out.println("Done");
            employeeMenu();
        }
    public void employeeMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Choose the option");
        System.out.println("1. View Bookings");
        System.out.println("2. View Rooms to be Cleaned");
        System.out.println("3. View All Rooms");
        System.out.println("4. Remove A booking");
        System.out.println("5. View all Booking");
        System.out.println("6. Mark a room As cleaned");
        int ch = input.nextInt();
        switch(ch){
            case 1:
                showBookings();
                break;
            case 2:
                //showRoomsToBeCleaned();
                break;
        }
    }
}