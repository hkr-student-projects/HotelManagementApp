package hkr;
import java.text.DateFormat;
import java.text.ParseException;
import java.io.*;
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
    private ArrayList<CleanLogic>  cleanedRooms = new ArrayList<>();
    long current = System.currentTimeMillis() - 1000*60*60*24;
    Date curr = new Date(current);
    SimpleDateFormat formatter =new SimpleDateFormat("dd-mm-yy");
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.preDefinedRooms();
        myApp.outputStreamRooms();
        myApp.preDefinedCustomer();
        myApp.preDefinedEmployee();
        myApp.showMenu();
    }
    private void customerVerify(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your choice:");
        out.println("1. Sign In");
        out.println("2. Sign Up");
        out.println("3. Forgot Password");
        int a = input.nextInt();
        input.nextLine();
        if(a == 1){
            out.println("Enter your user id:");
            String usId = input.nextLine();
            for(int i = 0;i<customers.size();i++){
                if(usId.equals(customers.get(i).getUserId())){
                    out.println("Enter the password:");
                    String pass = input.nextLine();
                    if(pass.equals(customers.get(i).getCustomerPassword())){
                        out.println("Welcome "+customers.get(i).getFirstName());
                        customerLogInMenu(customers.get(i).getFirstName());
                        break;
                    }else{
                        out.println("Incorrect Password! Try again");
                        customerVerify();
                    }
                }
            }
            out.println("You are not a customer registered! Please register first");
            customerSignUp();
        }else if(a == 2){
            customerSignUp();
        }
        else if(a == 3){
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
            CleanLogic clg = new CleanLogic(i,false);
            rooms.add(room1);
            cleanedRooms.add(clg);
        }
        for(int i = 0;i<rooms.size();i=i+3){
            rooms.get(i).setDoubleBed(true);
            rooms.get(i).setNumberOfBeds(2);
            rooms.get(i).setPrice(1500);
            rooms.get(i).setSize(30);
            rooms.get(i).setBalcony(true);
            cleanedRooms.get(i).setCleaned(true);
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
                    customerVerify();
                    break;
                case 3:
                    System.exit(0);
                default:
                    showMenu();
            }
        }
    }
    private void adminVerify(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your user id:");
        String uId = input.nextLine();
        for(int i = 0;i<employees.size();i++){
            if(uId.equals(employees.get(i).getUserId())){
                if(employees.get(i).getRole() == Employee.Role.ADMIN){
                    out.println("Enter the password:");
                    String pass = input.nextLine();
                    if(pass.equals(employees.get(i).getEmployeePassword())){
                        out.println("Welcome "+employees.get(i).getFirstName());
                        adminMenu();
                        break;
                    }else{
                        out.println("Incorrect Password! Try again");
                        employeeVerify();
                        break;
                    }
                }else{
                    out.println("You are not authorized person!");
                    employeeVerify();
                    break;
                }
            }
        }
        out.println("You are not an employee");
        showMenu();
    }
    private void cleanerMenu(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your choice");
        out.println("1. Show rooms to be cleaned");
        out.println("2. Mark a room Cleaned");
        out.println("3. Back");
        int ch = input.nextInt();
        switch(ch){
            case 1:
                roomCleanedCheck();
                break;
            case 2:
                markCleaned();
                break;
            case 3:
                employeeVerify();
                break;
            default:
                out.println("Incorrect choice");
                cleanerMenu();
        }

    }
    private void cleanerVerify(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your user id:");
        String usId = input.nextLine();
        for(int i = 0;i<employees.size();i++){
            if(usId.equals(employees.get(i).getUserId())){
                if(employees.get(i).getRole() == Employee.Role.CLEANER){
                    out.println("Enter the password:");
                    String pass = input.nextLine();
                    if(pass.equals(employees.get(i).getEmployeePassword())){
                        out.println("Welcome "+employees.get(i).getFirstName());
                        cleanerMenu();
                        break;
                    }else{
                        out.println("Incorrect Password! Try again");
                        employeeVerify();
                        break;
                    }
                }else{
                    out.println("You are not authorized person!");
                    employeeVerify();
                    break;
                }
            }
        }
        out.println("You are not an employee");
        showMenu();
    }
    private void receptionistVerify(){
        Scanner input = new Scanner(System.in);
        out.println("Enter your user id:");
        String usrId = input.nextLine();
        for(int i = 0;i<employees.size();i++){
            if(usrId.equals(employees.get(i).getUserId())){
                if(employees.get(i).getRole() == Employee.Role.RECEPTIONIST){
                    out.println("Enter the password:");
                    String pass = input.nextLine();
                    if(pass.equals(employees.get(i).getEmployeePassword())){
                        out.println("Welcome "+employees.get(i).getFirstName());
                        employeeMenu();
                        break;
                    }else{
                        out.println("Incorrect Password! Try again");
                        employeeVerify();
                        break;
                    }
                }else{
                    out.println("You are not authorized person!");
                    employeeVerify();
                    break;
                }
            }
        }
        out.println("You are not an employee");
        showMenu();
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
                adminVerify();
                break;
            case 2:
                cleanerVerify();
                break;
            case 3:
                receptionistVerify();
                break;
            case 4:
                showMenu();
                break;
            default:
                out.println("Incorrect Choice");
                showMenu();
        }
    }
    private void adminMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("=====Admin=====");
        System.out.println("1.  View all available rooms");
        System.out.println("2.  View all Employees");
        System.out.println("3.  View all Bookings");
        System.out.println("4.  Add A Booking");
        System.out.println("5.  Create a new room");
        System.out.println("6.  Create a new Employee");
        System.out.println("7.  Modify A Room");
        System.out.println("8.  Remove A Room");
        System.out.println("9.  Remove An Employee");
        System.out.println("10. Modify A Booking");
        System.out.println("11. Remove A Booking");
        System.out.println("12. Show All Customers");
        System.out.println("13. Find Customers");
        System.out.println("14. Exit");
        int inp = input.nextInt();
        switch(inp){
            case 1:
                //inputStreamRooms();
                showRoom();
                adminMenu();
                break;
            case 2:
                showEmployee();
                adminMenu();
                break;
            case 3:
                showBookings();
                adminMenu();
                break;
            case 4:
                input.nextLine();
                out.println("What's the name of customer:");
                String name = input.nextLine();
                addBookings(name);
                break;
            case 5:
                createRoom();
                adminMenu();
                break;
            case 6:
                createEmployee();
                adminMenu();
                break;
            case 7:
                modifyRoom();
                adminMenu();
                break;
            case 8:
                removeRoom();
                adminMenu();
                break;
            case 9:
                removeEmployee();
                adminMenu();
                break;
            case 10:
                modifyBooking();
                adminMenu();
                break;
            case 11:
                removeBooking();
                adminMenu();
                break;
            case 12:
                showCustomer();
                adminMenu();
                break;
            case 13:
                findCustomer();
                adminMenu();
                break;
            case 14:
                System.exit(0);
            default:
                out.println("You have entered incorrect choice!");
        }
    }
    private void showEmployee(){
        for(Employee employee:employees){
            out.println(employee);
        }
    }
    private void removeRoom(){
        Scanner input = new Scanner(System.in);
        out.println("Enter the room you want to remove:");
        int roomNumber = input.nextInt();
        for(int i = 0;i<rooms.size();i++){
            if(roomNumber == rooms.get(i).getAvailableRooms()){
                rooms.remove(i);
                adminMenu();
                break;
            }
        }
        out.println("This room does not exist");
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
                    if(checkInDate.before(curr)){
                        out.println("You cannot select previous day");
                        addBookings(cname);
                    }
                    else {
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
                        Booking booking = new Booking(rooms.get(j).getAvailableRooms(), rooms.get(j).isDoubleBed(), rooms.get(j).getNumberOfBeds(), rooms.get(j).getPrice(), rooms.get(j).getSize(), rooms.get(j).isBalcony(), randomAlphaNumeric(8), checkInDate, checkOutDate,cname);
                        bookings.add(booking);
                        for (int k = 0; k < bookings.size(); k++) {
                            if (rooms.get(j).getAvailableRooms() == bookings.get(k).getAvailableRooms()) {
                                makeBooking(bookings.get(k).getAvailableRooms(), bookings.get(k).getBookingReference(), cname, bookings.get(k).getCheckIn(), bookings.get(k).getCheckOut());
                                break;
                            }
                        }
                        break;
                    }
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
    private void createEmployee(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first name of Employee");
        String fname = input.nextLine();
        System.out.println("Enter the last name of Employee");
        String lname = input.nextLine();
        System.out.println("Enter the Person Number of Employee");
        String pNumber = input.nextLine();
        System.out.println("Enter the Phone Number of Employee");
        String phNumber = input.nextLine();
        System.out.println("Enter the email of Employee");
        String email = input.nextLine();
        System.out.println("Enter the userId of Employee");
        String userId = input.nextLine();
        for(int i=0;i<employees.size();i++){
            if(userId.equals(employees.get(i).getUserId())){
                out.println("This user id exists");
                createEmployee();
                break;
            }
        }
        System.out.println("Enter the Password of Employee");
        String pass = input.nextLine();
        System.out.println("Enter the role(recep,cleaner,admin)");
        String role = input.nextLine();
        if(role.equalsIgnoreCase("recep")){
            Employee employee2  = new Employee(fname,lname,pNumber,phNumber,email,userId,pass,Employee.Role.RECEPTIONIST);
            employees.add(employee2);
        }else if(role.equalsIgnoreCase("cleaner")){
            Employee employee2  = new Employee(fname,lname,pNumber,phNumber,email,userId,pass,Employee.Role.CLEANER);
            employees.add(employee2);
        }else if(role.equalsIgnoreCase("admin")){
            Employee employee2  = new Employee(fname,lname,pNumber,phNumber,email,userId,pass,Employee.Role.ADMIN);
            employees.add(employee2);
        }else{
            out.println("Incorrect choice");
            createEmployee();
        }
    }
    private void showBookings() {
        for(Booking booking:bookings){
            out.println(booking);
        }
    }
    private void removeEmployee() {
        Scanner input = new Scanner(System.in);
        out.println("Which employee you want to remove, Write his/her user id:");
        String usId = input.nextLine();
        for(int i = 0;i<employees.size();i++){
            if(usId.equals(employees.get(i).getUserId())){
                out.println("Is this is the user: "+employees.get(i).getUserId()+" Press Enter to confirm");
                input.nextLine();
                employees.remove(i);
                adminMenu();
                break;
            }
        }
        out.println("This user does not exist");
    }
    private void removeBooking() {
        Scanner input = new Scanner(System.in);
        out.println("Enter the room you want to remove the booking for:");
        int roomNumber = input.nextInt();
        for(int i = 0;i<bookings.size();i++){
            if(roomNumber == bookings.get(i).getAvailableRooms()){
                bookings.remove(i);
                adminMenu();
                break;
            }
        }
        out.println("This booking does not exist");
        adminMenu();
    }
    private void employeeMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Choose the option");
        System.out.println("1. View Bookings");
        System.out.println("2. View Rooms to be Cleaned");
        System.out.println("3. View All Rooms");
        System.out.println("4. Remove A booking");
        System.out.println("5. Modify A Booking");
        System.out.println("6. Show All Customers");
        System.out.println("7. Back");
        int ch = input.nextInt();
        switch(ch){
            case 1:
                showBookings();
                employeeMenu();
                break;
            case 2:
                roomCleanedCheck();
                employeeMenu();
                break;
            case 3:
                showRoom();

                break;
            case 4:
                adminVerify();
                removeBooking();
                employeeMenu();
                break;
            case 5:
                modifyBooking();
                employeeMenu();
                break;
            case 6:
                showCustomer();
                employeeMenu();
                break;
            case 7:
                employeeVerify();
                break;
            default:
                out.println("Incorrect choice");
                employeeMenu();
        }
    }
    private void markCleaned() {
        Scanner input = new Scanner(System.in);
        out.println("Enter the room you have cleaned");
        int rn = input.nextInt();
        for(int i = 0;i<cleanedRooms.size();i++){
            if(rn == cleanedRooms.get(i).getRoomNumber()){
                if(!cleanedRooms.get(i).isCleaned()){
                    cleanedRooms.get(i).setCleaned(true);
                    break;
                }else{
                    out.println("The room is already cleaned");
                    break;
                }
            }
        }
        out.println("The room does not exist");
    }
    private void customerSignUp(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first name of Customer");
        String fname = input.nextLine();
        System.out.println("Enter the last name of Customer");
        String lname = input.nextLine();
        System.out.println("Enter the Person Number of Customer");
        String pNumber = input.nextLine();
        System.out.println("Enter the Phone Number of Customer");
        String phNumber = input.nextLine();
        System.out.println("Enter the email of Customer");
        String email = input.nextLine();
        System.out.println("Enter the userId of Customer");
        String userId = input.nextLine();
        for(int i=0;i<employees.size();i++){
            if(userId.equals(employees.get(i).getUserId())){
                out.println("This user id exists");
                createEmployee();
                break;
            }
        }
        System.out.println("Enter the Password of Customer");
        String pass = input.nextLine();
            Customer customer  = new Customer(fname,lname,pNumber,phNumber,email,userId,pass);
            customers.add(customer);
            customerLogInMenu(fname);
    }
    private void customerLogInMenu(String name){
        Scanner input = new Scanner(System.in);
        System.out.println("Select Your Choice");
        System.out.println("1. Make A Booking");
        System.out.println("2. Cancel Booking");
        System.out.println("3. Change the Booking");
        System.out.println("4. Change your details");
        System.out.println("5. See Your Booking(s)");
        System.out.println("6. See Your Booking(s) History");
        System.out.println("7. Back");
        int  cho = input.nextInt();
        switch(cho){
            case 1:
                addBookings(name);
                customerVerify();
                break;
            case 2:
                out.println("Your request has been made");
                employeeVerify();
                removeBooking();
                customerVerify();
                break;
            case 3:
                modifyBooking();
                customerVerify();
                break;
            case 4:
                break;
            case 5:
                showBookingsForCustomer(name);
                customerLogInMenu(name);
                break;
            case 6:
                bookingHistory(name);
                customerLogInMenu(name);
                break;
            case 7:
                customerVerify();
                break;
            default:
                out.println("Incorrect Choice");
                cleanerVerify();
        }
    }
    private void showCustomer(){
        for(Customer customer:customers){
            out.println(customer);
        }
    }
    private void findCustomer(){
            Scanner input = new Scanner(System.in);
            out.println("You want to search by:");
            out.println("1. First Name");
            out.println("2. Last Name");
            out.println("3. Press any key to back");
            int a = input.nextInt();
            input.nextLine();
            switch (a) {
                case 1:
                    out.println("Enter your First Name");
                    String fname = input.nextLine();
                    for (int i = 0; i < customers.size(); i++) {
                        if (fname.equalsIgnoreCase(customers.get(i).getFirstName())) {
                            out.println("Is this your email: " + customers.get(i).getEmail());
                            out.println("Press Enter to continue or any other key to exit");
                            String b = input.nextLine();
                            if (b.equals("")) {
                                out.println("An email has been sent to your mail");
                                customerVerify();
                                break;
                            } else {
                                customerVerify();
                                break;
                            }
                        }
                    }
                    out.println("This user does not exist");
                    break;
                case 2:
                    out.println("Enter your Last Name");
                    String lname = input.nextLine();
                    for (int i = 0; i < customers.size(); i++) {
                        if (lname.equalsIgnoreCase(customers.get(i).getLastName())) {
                            out.println("Is this your email: " + customers.get(i).getEmail());
                            out.println("Press Enter to continue or any other key to exit");
                            String b = input.nextLine();
                            if (b.equals("")) {
                                out.println("An email has been sent to your mail");
                                break;
                            } else {
                                findCustomer();
                                break;
                            }
                        }
                    }
                    out.println("This user does not exist");
                    break;
                default:
                    customerVerify();
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
            pw.format(" Booking Reference = %s",bookingRf );
            pw.format(" CheckIn date = "+chin );
            pw.format(" CheckOut date = "+chout+"\n" );
            pw.close();
        } catch (IOException e) {
            out.println("Error!");
        }
    }
    private void roomCleanedCheck(){
      for(CleanLogic clg:cleanedRooms){
          out.println(clg);
      }
    }
    private void modifyRoom(){
        Scanner input = new Scanner(System.in);
        out.println("Enter the room number you want to modify");
        int rn = input.nextInt();
        for(int i = 0;i<rooms.size();i++){
            if(rn == rooms.get(i).getAvailableRooms()){
                out.println("What do you want to edit");
                out.println("1. Status of double bed");
                out.println("2. Number of bed");
                out.println("3. Price");
                out.println("4. Size");
                out.println("5. Status of balcony");
                out.println("6. Back");
                int ch = input.nextInt();
                switch (ch){
                    case 1:
                        out.println("Is the room contains double bed");
                        boolean dbed = input.nextBoolean();
                        rooms.get(i).setDoubleBed(dbed);
                        break;
                    case 2:
                        out.println("Enter the number of the bed(s)");
                        int numberBed = input.nextInt();
                        rooms.get(i).setNumberOfBeds(numberBed);
                        break;
                    case 3:
                        out.println("Enter the price of the room per night");
                        double price = input.nextDouble();
                        rooms.get(i).setPrice(price);
                        break;
                    case 4:
                        out.println("Enter the size of the room");
                        double size = input.nextDouble();
                        rooms.get(i).setSize(size);
                        break;
                    case 5:
                        out.println("Does the room has balcony");
                        boolean balcony = input.nextBoolean();
                        rooms.get(i).setBalcony(balcony);
                        break;
                    case 6:
                        adminMenu();
                        break;
                    default:
                        out.println("Incorrect Choice");
                        modifyRoom();
                }
            }
        }
    }
    private void modifyBooking(){
        Scanner input = new Scanner(System.in);
        out.println("Enter the room number you want to change the booking");
        int rn = input.nextInt();
        input.nextLine();
        for(int i = 0;i<bookings.size();i++) {
            if (rn == bookings.get(i).getAvailableRooms()) {
                out.println("What do you want to change");
                out.println("1. Checkin Date");
                out.println("2. Checkout Date");
                int ch = input.nextInt();
                if (ch == 1) {
                    try {
                        System.out.print("Enter checkIn Date(dd-mm-yy): ");
                        String checkin = input.nextLine();
                        input.nextLine();
                        Date checkInDate = null;
                        checkInDate = formatter.parse(checkin);
                        DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
                        String s3 = df3.format(checkInDate);
                        System.out.println("The entered date is: " + s3);
                    } catch (ParseException e) {
                        out.println("Unable to parse");
                    }
                } else if (ch == 2) {
                    try {
                        System.out.print("Enter checkout Date(dd-mm-yy): ");
                        String checkOut = input.nextLine();
                        Date checkOutDate = null;
                        checkOutDate = formatter.parse(checkOut);
                        DateFormat df4 = DateFormat.getDateInstance(DateFormat.LONG);
                        String s4 = df4.format(checkOutDate);
                        System.out.println("The entered date is: " + s4);
                    } catch (ParseException e) {
                        out.println("Unable to parse");
                    }
                }
            }
        }
        out.println("This booking does not exist");
    }
    private void outputStreamRooms(){
        try{
            FileOutputStream f = new FileOutputStream(new File("AllRooms.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            for(Room room:rooms){
                o.writeObject(room);
            }
            o.close();
            f.close();
        }catch (IOException e){
            out.println("File Not Found");
        }
    }
    private void inputStreamRooms(){
        try{
                FileInputStream fi = new FileInputStream(new File("AllRooms.txt"));
                ObjectInputStream oi = new ObjectInputStream(fi);
                for(Room room:rooms){
                    room = (Room) oi.readObject();
                    out.println(room.toString());
                }
                oi.close();
                fi.close();
        }
        catch(Exception e){
            System.out.println("File Not Found");}
    }
    private void showBookingsForCustomer(String name){
        for (Booking booking : bookings) {
            if (name.equals(booking.getNameOfCustomer())) {
                out.println(booking);
            }
        }
    }
    private void bookingHistory(String name) {
        try {
            String str = " Customer Name = " + name;
            Scanner scanner = new Scanner(new File("Bookings.txt"));
                while(scanner.hasNextLine()){
                    String s = scanner.nextLine();
                    if(s.contains(str)){
                        out.println(""+s);
                }
                scanner.close();
            }
        } catch (IOException e) {
            out.println("File Not Found");
        }
    }
}