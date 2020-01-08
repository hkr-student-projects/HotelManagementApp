package hkr;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import static java.lang.System.*;
import java.text.SimpleDateFormat;

public class Main {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private int empCounter = 0;
    LocalDate currentDate = LocalDate.now();
    SimpleDateFormat formatter =new SimpleDateFormat("dd-mm-yy");
    private int admCounter = 0;
    private int roomCounter = 0;
    private int bookingCounter = 0;
    private int customerCounter = 0;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static void main(String[] args) {
        Main myApp = new Main();
        //myApp.createAdmins();
        myApp.preDefinedRooms();
        //myApp.readEmployee();
        //myApp.preDefinedBookings();
        myApp.preDefinedCustomer();
        myApp.preDefinedEmployee();
        myApp.showMenu();
    }
    /*private void preDefinedBookings(){
        for(int i = 0;i<rooms.size();i=i+5){
            Booking booking = new Booking(rooms.get(i).getAvailableRooms(),rooms.get(i).isDoubleBed(),rooms.get(i).getNumberOfBeds(),rooms.get(i).getPrice(),rooms.get(i).getSize(),randomAlphaNumeric(8));
            bookings.add(booking);
        }
        for(Booking bookng:bookings){
            out.println(bookng);
        }
    }*/
    private void customerVerify(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your choice:");
        out.println("1. Sign In");
        out.println("2. Sign Up");
        out.println("3. Forgot Password");
        int a = input.nextInt();
        if(a == 1){
            out.println("Enter your email:");
            String cid = input.nextLine();
            input.nextLine();
            for(int i = 0;i<customers.size();i++){
                if(cid.equals(customers.get(i).getEmail())){
                    out.println("Enter the password:");
                    String pass = input.nextLine();
                    if(pass.equals(customers.get(i).getCustomerPassword())){
                        out.println("Welcome "+customers.get(i).getFirstName());
                        customerLogIn();
                        break;
                    }
                    else{
                        out.println("Wrong Password! Try Again");
                        customerVerify();
                        break;
                    }
                }else{
                    out.println("You are not a registered member Try Sign Up first");
                    customerVerify();
                }
            }
        }else if(a == 3){
            findCustomer();
        }
    }
    private void preDefinedCustomer(){
        Customer customer1 = new Customer("Katia","Rahi","19970614-8978","0756894712","katia123@mail.com","katia123","katia123");
        Customer customer2 = new Customer("Anna","John","19860225-5647","0714569878","anna123@mail.com","anna123","anna123");
        Customer customer3 = new Customer("Kalle","Bell","19821119-2369","074568697","kalle123@mail.com","kalle123","kalle123");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
    }
    private void preDefinedRooms(){
        for(int i = 1;i<=25;i++){
            Room room1 = new Room(i,false,1,1100,19,false);
            rooms.add(room1);
        }
        for(int i = 0;i<rooms.size();i=i+3){
            rooms.get(i).setDoubleBed(true);
            rooms.get(i).setNumberOfBeds(2);
            rooms.get(i).setPrice(1500);
            rooms.get(i).setSize(30);
            rooms.get(i).setBalcony(true);
        }
        for(Room room:rooms) {
            out.println(room);
        }
    }
    private String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private void preDefinedEmployee(){
        Employee employee1  = new Employee("Muhammad","Hamza","19980515-3377","0737410229","hamza123@gmail.com","hamza123","hamza123",Employee.Role.ADMIN);
        Employee employee2  = new Employee("Deniel","Alekseev","19970615-4896","0787850156","deniel123@gmail.com","deniel23","deniel123",Employee.Role.RECEPTIONIST);
        Employee employee3  = new Employee("Katia","Rahi","19960818-6545","0787415728","katia123@gmail.com","katia123","katia123",Employee.Role.CLEANER);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
    }
    private void showMenu(){
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("=====Welcome to our Hotel=====");
            System.out.println("Enter Your Choice");
            System.out.println("Are You :");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            int inp = input.nextInt();
            switch (inp) {
                case 1:
                    employeeVerify();
                    break;
                case 2:
                    //employeeverify();
                    break;
                case 3:
                    customerVerify();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
    private void employeeVerify() {
       Scanner input = new Scanner(System.in);
        out.println("Enter your choice:");
        out.println("1. Admin");
        out.println("2. Cleaner");
        out.println("3. Receptionist");
        out.println("4. Back");
        int ch = input.nextInt();
        input.nextLine();
        switch(ch) {
            case 1:
                out.println("Enter your user id:");
                String uId = input.nextLine();
                for(int i = 0;i<employees.size();i++){
                    if(uId.equals(employees.get(i).getUserId())){
                        if(employees.get(i).getRole() == Employee.Role.ADMIN){
                            out.println("Enter the password:");
                            String pass = input.nextLine();

                        }
                    }
                }
        }
    }
        /*try {
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
    }*/
    private void bookingHistory(){
        try {
            FileWriter fw = new FileWriter("history.log",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.format("============History===============");
            for(int i = 0;i<bookings.size();i++) {
               /* pw.format("Room No = "+bookings.get(i).getAvailableRooms()+);
                pw.format("Employee First Name =%s ", employee1.getFirstName());
                pw.format("Employee Last Name =%s ", employee1.getLastName());
                pw.format("Employee Role =%s ", employee1.getRole());
                pw.format("Employee Personal Number =%s ", employee1.getPersonNumber());
                pw.format("Employee Phone Number =%s ", employee1.getPhoneNumber());
                pw.format("Employee Password =%s%n", employee1.getEmployeePassword());*/
            }
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
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
        System.out.println("14. Add A Booking") ;
        System.out.println("15. Exit");
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
                //createEmployee();
                break;
            case 7:
                //createAdmin();
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
            case 15:
                showMenu();
                break;
            case 12:
                showCustomer();
                break;
            case 13:
                findCustomer();
                break;
            case 14:
                input.nextLine();
                out.println("What's the name of customer:");
                String name = input.nextLine();
                addBookings(name);
                break;
            default:
                out.println("You have entered incorrect choice!");
        }
    }
    private void createRoom(){
        Scanner input = new Scanner(System.in);
        out.println("Enter the room number you want to add:");
        int roomNumber = input.nextInt();
        out.println("Is the room contains double bed");
        boolean dbed = input.nextBoolean();
        out.println("Enter the number of the bed(s)");
        int numberBed = input.nextInt();
        out.println("Enter the price of the room per night");
        double price = input.nextDouble();
        out.println("Enter the size of the room");
        double size = input.nextDouble();
        out.println("Does the room has balcony");
        boolean balcony = input.nextBoolean();
        Room room1 = new Room(roomNumber,dbed,numberBed,price,size,balcony);
        rooms.add(room1);
        adminMenu();
    }
    private void addBookings(String cname){
        Scanner input = new Scanner(System.in);
        out.println("Enter the room you want to book(1-25)");
        int a = input.nextInt();
        input.nextLine();
        for(int i = 0;i<bookings.size();i++) {
            if (a == bookings.get(i).getAvailableRooms()) {
                out.println("The room is already booked");
                showMenu();
                break;
            }
        }
        for (int j = 0;j<rooms.size();j++) {
            if (a == rooms.get(j).getAvailableRooms()) {
                try {
                    System.out.print("Enter checkIn Date: ");
                    String checkin = input.nextLine();
                    input.nextLine();
                    Date checkInDate = null;
                    checkInDate = formatter.parse(checkin);
                    DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
                    String s3 = df3.format(checkInDate);
                    System.out.println("The entered date is: " + s3);
                    System.out.print("Enter checkout Date: ");
                    String checkOut = input.nextLine();
                    Date checkOutDate = null;
                    checkOutDate = formatter.parse(checkOut);
                    DateFormat df4 = DateFormat.getDateInstance(DateFormat.LONG);
                    String s4 = df4.format(checkOutDate);
                    System.out.println("The entered date is: " + s4);
                    Booking booking = new Booking(rooms.get(j).getAvailableRooms(), rooms.get(j).isDoubleBed(), rooms.get(j).getNumberOfBeds(), rooms.get(j).getPrice(), rooms.get(j).getSize(),rooms.get(j).isBalcony(), randomAlphaNumeric(8), checkInDate, checkOutDate);
                    bookings.add(booking);
                    for(int k = 0;k<bookings.size();k++) {
                        if(rooms.get(j).getAvailableRooms() == bookings.get(k).getAvailableRooms()) {
                            makeBooking(bookings.get(k).getAvailableRooms(),bookings.get(k).getBookingReference(),cname,bookings.get(k).getCheckIn(),bookings.get(k).getCheckOut());
                            break;
                        }
                    }
                    break;
                }catch(ParseException e) {
                    System.out.println("Unable to parse");
                }
                }
            }
        }
    private void showRoom(){
        for(Room room: rooms){
            out.println(room);
        }
    }
    /*private void createEmployee(){
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
    }*/
    private void showEmployee(){
        for(Employee employee:employees){
            out.println(employee);
        }
        adminMenu();
    }
    /*private void createAdmin(){
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
        Admin admin2 = new Admin(fname,lname,pNumber,phNumber,"ss",pass,admCounter);
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
        //adminMenu();
    }*/
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
        for(Booking booking:bookings){
            out.println(booking);
        }
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
        Scanner input = new Scanner(System.in);
        showBookings();
        out.println("Enter the booking reference number for the booking you want to remove");
        String br = input.nextLine();
        for(int i = 0;i<bookings.size();i++){
            if(br.equalsIgnoreCase(bookings.get(i).getBookingReference())){
                int a = bookings.get(i).getAvailableRooms();
                out.println("Room No is "+a);
                out.println("Do you want to remove the booking for the room(Press 'Y' for YES and 'N' for NO)");
                String choice = input.nextLine();
                if(choice.equalsIgnoreCase("y")){
                    bookings.remove(i);
                    showBookings();
                }
                else if(choice.equalsIgnoreCase("n")){
                    showBookings();
                }
                else{
                    showMenu();
                }
            }
        }
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
                //adminVerify();
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
    /*private void customerSignUp(){
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
    }*/
    private void customerLogInMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Select Your Choice");
        System.out.println("1. Make A Booking");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Change the Booking");
        int  cho = input.nextInt();
        switch(cho){
            case 1:
                //addBookings();
                //makeBooking();
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
            out.println("You want to search by:");
            out.println("1. First Name");
            out.println("2. Last Name");
            int a = input.nextInt();
            input.nextLine();
            switch (a){
                case 1:
                    out.println("Enter your First Name");
                    String fname = input.nextLine();
                    for(int i = 0;i<customers.size();i++){
                        if(fname.equalsIgnoreCase(customers.get(i).getFirstName())){
                            out.println("Is this your email: "+customers.get(i).getEmail());
                            out.println("Press Enter to continue or any other key to exit");
                            String b = input.nextLine();
                            if(b.equals("")){
                                out.println("An email has been sent to your mail");
                                break;
                            }else{
                                customerLogInMenu();
                            }
                        }
                    }
                    break;
                case 2:
                    out.println("Enter your Last Name");
                    String lname = input.nextLine();
                    for(int i = 0;i<customers.size();i++){
                        if(lname.equalsIgnoreCase(customers.get(i).getLastName())){
                            out.println("Is this your email: "+customers.get(i).getEmail());
                            out.println("Press Enter to continue or any other key to exit");
                            String b = input.nextLine();
                            if(b.equals("")){
                                out.println("An email has been sent to your mail");
                                break;
                            }else{
                                findCustomer();
                            }
                        }
                    }
                    break;
            }
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
    private void makeBooking(int roomNumber,String bookingRf,String name,Date chin,Date chout) {
        try {
            FileWriter fw = new FileWriter("Bookings.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.format("========History=========\n");
            pw.format("Room No = %d",roomNumber);
            pw.format(" Customer Name = %s",name );
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
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