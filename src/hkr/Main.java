package hkr;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;

public class Main {
    private ArrayList<String> rooms = new ArrayList<>();
    private int empCounter = 0;
    private int admCounter = 0;
    private int roomCounter = 0;
    private int bookingCounter = 0;
    private int customerCounter = 0;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.showMenu();
    }
    private String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private void showMenu(){
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
                    customerMenu();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
    private void adminVerify() {
        try {
            Scanner input = new Scanner(System.in);
            String st = "";
            try {
                out.println("Enter the Admin Id");
                st = input.nextLine();
                int number = Integer.parseInt(st);
            }catch(NumberFormatException e){
                out.println("You have entered String!Please try again!");
                adminVerify();
            }
            String str = "Admin Id ="+st;
            Scanner scanner = new Scanner(new File("Admin.txt"));
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        out.println("Enter the password");
                        String pass = input.nextLine();
                        String sa = "Admin Password ="+pass;
                        if(!s.equals("")) {
                            if (s.contains(sa)) {
                                out.println("Welcome!");
                                adminMenu();
                                break;
                            } else {
                                out.println("Access Denied!");
                                showMenu();
                                break;
                            }
                        }else{
                            out.println("Access Denied!");
                            adminVerify();
                        }
                    }
                }
                scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        out.println("Done");
    }
    private void adminMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("=====Admin=====");
        System.out.println("1.  View all available rooms");
        System.out.println("2.  View all Employees");
        System.out.println("3.  View all Bookings");
        System.out.println("4.  View all Admins");
        System.out.println("5.  Create a new room");
        System.out.println("6.  Create a new Employee");
        System.out.println("7.  Create a new Admin");
        System.out.println("8.  Remove a room");
        System.out.println("9.  Remove a employee");
        System.out.println("10. Remove a admin");
        System.out.println("11. Remove a Booking");
        System.out.println("12. Show All Customers");
        System.out.println("13. Find Customers");
        System.out.println("14. Exit");
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
            case 9:
                removeEmployee();
                break;
            case 10:
                removeAdmin();
                break;
            case 11:
                removeBooking();
                break;
            case 14:
                showMenu();
                break;
            case 12:
                showCustomer();
                break;
            case 13:
                findCustomer();
                break;
            default:
                out.println("You have entered incorrect choice!");
        }
    }
    private void createRoom(){
        try {
            Scanner input = new Scanner(System.in);
            out.println("Enter the room number you want to add");
            String room = input.nextLine();
            out.println("Enter the price per night");
            double price = input.nextDouble();
            out.println("Enter the number of the beds");
            int numberOfBeds = input.nextInt();
            out.println("Enter the size of the room");
            double size = input.nextDouble();
            out.println("Does the room have double bed");
            boolean doubleBed = input.nextBoolean();
            out.println("Are you Sure! Press Enter");
            input.nextLine();
            Room hotel1 = new Room(room,doubleBed,numberOfBeds,price,size);
            FileWriter fw = new FileWriter("AllRooms.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.format("Room No =%s ",hotel1.getAvailableRooms());
            pw.format("Price =%.2f ",hotel1.getPrice());
            pw.format("Size =%.2f meter square ",hotel1.getSize());
            pw.format("Number of bed(s) =%d ",hotel1.getNumberOfBeds());
            if(hotel1.isDoubleBed()) {
                pw.format("Double Bed =Yes%n");
            }else{
                pw.format("Double Bed =No%n");
            }
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
        adminMenu();
    }
    private void showRoom(){
        try {
            FileReader fr = new FileReader("AllRooms.txt");
            BufferedReader br = new BufferedReader(fr);
            String room;
            rooms.clear();
            while ((room = br.readLine()) != null) {
                rooms.add(room);
            }
            for (int i = 0; i < rooms.size(); i++){
                out.println(rooms.get(i));
        }
        }catch(IOException e){
            out.println("File Not Found!");
        }
    }
    private void createEmployee(){
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
        System.out.println("Enter the role(receptionist,cleaner,waiter)");
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
    private void showEmployee(){
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
    private void createAdmin(){
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
    private void showAdmins(){
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
    private void showBookings() {
        try{
            FileReader fr = new FileReader("Bookings.txt");
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
    private void removeAdmin() {
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
    private void removeEmployee(){
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
    private void removeBooking() {
        try {
            String str = "Booking =Yes";
            Scanner scanner = new Scanner(new File("AllRooms.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    out.println("" + s);
                }
            }
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        out.println("Done");
        try {
            out.println("Enter the Room Number you want to remove the booking");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            File oldFile = new File("AllRooms.txt");
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
                File tempFile = new File("AllRooms.txt");
                newFile.renameTo(tempFile);
            } catch(IOException e){
                out.println("File Not Found!");
            }
            out.println("Done");
        }
        private void employeeverify(){
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
                            employeeMenu();
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
    private void employeeMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Choose the option");
        System.out.println("1. View Bookings");
        System.out.println("2. View Rooms to be Cleaned");
        System.out.println("3. View All Rooms");
        System.out.println("4. Remove A booking");
        System.out.println("5. Mark a room As cleaned");
        int ch = input.nextInt();
        switch(ch){
            case 1:
                showBookings();
                break;
            case 2:
                showRoomsToBeCleaned();
                break;
            case 3:
                showRoom();
                break;
            case 4:
                adminVerify();
                removeBooking();
                break;
            case 5:
                roomCleanedCheck();
                markCleaned();
                break;
        }
    }
    private void showRoomsToBeCleaned(){
        try {
            String str = "Cleaned =No";
            Scanner scanner = new Scanner(new File("AllRooms.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    out.println("" + s);
                }
            }
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
    }
    private void markCleaned() {
        try {
            Scanner input = new Scanner(System.in);
            out.println("Enter the room number you cleaned");
            String room = input.nextLine();
            String str = "Room No =" + room;
            Scanner scanner = new Scanner(new File("AllRooms.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    modifyFile("AllRooms.txt", " Cleaned =No", " Cleaned =Yes");
                }
            }
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
    }
    private void customerMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Select Your Choice");
        System.out.println("1. LogIn");
        System.out.println("2. SignUp");
        int ch = input.nextInt();
        switch(ch){
            case 1:
                customerLogIn();
                break;
            case 2:
                customerSignUp();
                break;
        }
    }
    private void customerLogIn(){
        try {
            Scanner input = new Scanner(System.in);
            out.println("Enter the Customer Id");
            int st = input.nextInt();
            input.nextLine();
            String str = "Customer Id ="+st;
            Scanner scanner = new Scanner(new File("Customer.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    out.println("Enter the password");
                    String pass = input.nextLine();
                    String sa = "Customer Password ="+pass;
                    if(s.contains(sa)){
                        out.println("Welcome!");
                        customerLogInMenu();
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
    private void customerSignUp(){
        try {
            String str = "Customer Id =";
            int line = 0;
            Scanner scanner = new Scanner(new File("Customer.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    line++;
                }
            }
            out.println(line);
            customerCounter = line;
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name");
        String fname = input.nextLine();
        System.out.println("Enter your last name");
        String lname = input.nextLine();
        System.out.println("Enter your person number");
        String pnumb = input.nextLine();
        System.out.println("Enter your phone number");
        String phnumb = input.nextLine();
        System.out.println("Enter your password");
        String pass = input.nextLine();
        customerCounter++;
        Customer customer = new Customer(fname,lname,pnumb,phnumb,pass,customerCounter);
        try {
            FileWriter fw = new FileWriter("Customer.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.format("Customer Id =%d ",customer.getCustomerCounter());
            pw.format("Customer First Name =%s ",customer.getFirstName());
            pw.format("Customer Last Name =%s ",customer.getLastName());
            pw.format("Customer Personal Number =%s ",customer.getPersonNumber());
            pw.format("Customer Phone Number =%s ",customer.getPhoneNumber());
            pw.format("Customer Password =%s%n",customer.getCustomerPassword());
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
    }
    private void customerLogInMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Select Your Choice");
        System.out.println("1. Make A Booking");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Change the Booking");
        int  cho = input.nextInt();
        switch(cho){
            case 1:
                roomCounterMethod();
                makeBooking();
                break;
        }
    }
    private void showCustomer(){
        try {
            String str = "Customer Id =";
            int line = 0;
            Scanner scanner = new Scanner(new File("Customer.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    line++;
                }
            }
            out.println(line);
            customerCounter = line;
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        System.out.println("The available Customers are:"+customerCounter);
        try{
            FileReader fr = new FileReader("Customer.txt");
            BufferedReader br = new BufferedReader(fr);

            String customer;
            while((customer = br.readLine()) != null){
                out.println(customer+"\n");
            }
        }catch(IOException e){
            out.println("File Not Found!");
        }
    }
    private void findCustomer(){
        try {
            Scanner input = new Scanner(System.in);
            out.println("Enter the Customer Name");
            String name = input.nextLine();
            String str = "Customer First Name ="+name;
            Scanner scanner = new Scanner(new File("Customer.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    out.println("" + s);
                }
            }
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
    }
    private void makeBooking() {
        try {
            if(roomCounter!=0){
                out.println("Enter the Room Number you want to book");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                File oldFile = new File("AllRooms.txt");
                File newFile = new File("Bookings.txt");
                Scanner scanner = new Scanner(oldFile);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if (s.contains(str)) {
                        FileWriter fw = new FileWriter(newFile, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        pw.println("" + s+"Booking Reference No ="+randomAlphaNumeric(8));
                        pw.close();
                    }
                    modifyFile("Bookings.txt","Room No ="+str+" Booking =No","Room No ="+str+" Booking =Yes");
                    modifyFile("AllRooms.txt","Room No ="+str+" Booking =No","Room No ="+str+" Booking =Yes");
                }
            }else{
                out.println("We do not have any rooms");
            }
        }catch (IOException e) {
            out.println("File Not Found!");
        }
    }
    private  void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources

                reader.close();

                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void roomCounterMethod(){
        try {
            String str = "Booking =No";
            int line = 0;
            Scanner scanner = new Scanner(new File("AllRooms.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    line++;
                    out.println("" + s);
                }
            }

            roomCounter = line;
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
        System.out.println("The available rooms are:"+roomCounter);
    }
    private void roomCleanedCheck(){
        try {
            String str = "Cleaned =No";
            Scanner scanner = new Scanner(new File("AllRooms.txt"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.contains(str)) {
                    out.println("" + s);
                }
            }
            scanner.close();
        } catch (IOException e) {
            out.println("File Not Found!");
        }
    }
}