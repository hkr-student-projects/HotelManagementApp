package hkrDB;

import hkrFX.Logger;
import hkrFX.MainFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

enum QueryType{
    UPDATE,//INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE
    READER,//SELECT -> ResultSet
    BOOL//ALL -> SELECT ? true : false
}

public class DatabaseManager {

    private final String rooms = "`hotel`.`Rooms`";
    private final String clients = "`hotel`.`Customers`";
    private final String books = "`hotel`.`Bookings`";
    private final String beds = "`hotel`.`Beds`";
    private final String orders = "`hotel`.`Orders`";
    private final String booked = "`hotel`.`Booked`";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }
        catch(Exception ex){
            //Logger.logError("DB Connection failed");
            Logger.logException(ex);
        }
    }

    public DatabaseManager(){
        checkSchema();
    }

    public int addCustomer(String bookref, String ssn, String name, String midname, String surname, String addr, String phone){
        return (int)executeQuery(QueryType.UPDATE,
                "INSERT INTO " + clients +
                        " (`Booking_refNumber`,`SSN`,`Name`,`MiddleName`,`Surname`,`Address`,`Phone`) " +
                        "VALUES ('" + bookref + "','" + ssn + "','" + name + "','" + midname + "','" + surname + "','" + addr + "','" + phone + "');"
        );
    }

    public void checkSchema()
    {
        try{
            executeQuery(QueryType.BOOL,
                    " CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8;" +
                            "CREATE TABLE IF NOT EXISTS " + books + " (" +
                            "`reference` VARCHAR(6) NOT NULL," +
                            "`movein` DATETIME NOT NULL," +
                            "`moveout` DATETIME NOT NULL," +
                            "PRIMARY KEY (`reference`));" +
                            "CREATE TABLE IF NOT EXISTS " + clients + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`ssn` VARCHAR(45) NOT NULL," +
                            "`name` VARCHAR(45) NOT NULL," +
                            "`middlename` VARCHAR(45) DEFAULT NULL," +
                            "`surname` VARCHAR(45) NOT NULL," +
                            "`phone` VARCHAR(15) NOT NULL," +
                            "`address` VARCHAR(45) NOT NULL," +
                            "PRIMARY KEY (`id`));" +
                            "CREATE TABLE IF NOT EXISTS " + orders + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`Customer_id` INT NOT NULL," +
                            "`Booking_reference` VARCHAR(6) NOT NULL," +
                            "PRIMARY KEY (`id`)," +
                            "INDEX `fk_Orders_Customer1_idx` (`Customer_id` ASC)," +
                            "INDEX `fk_Orders_Booking1_idx` (`Booking_reference` ASC)," +
                            "CONSTRAINT `fk_Orders_Customer1`" +
                            "FOREIGN KEY (`Customer_id`)" +
                            "REFERENCES "+clients+" (`id`)" +
                            "ON DELETE NO ACTION " +
                            "ON UPDATE NO ACTION," +
                            "CONSTRAINT `fk_Orders_Booking1`" +
                            "FOREIGN KEY (`Booking_reference`)" +
                            "REFERENCES "+books+" (`reference`)" +
                            "ON DELETE NO ACTION " +
                            "ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS "+rooms+" (" +
                            "`number` VARCHAR(10) NOT NULL," +
                            "`floor` SMALLINT(3) NOT NULL," +
                            "`class` ENUM('ECONOMY', 'MIDDLE', 'LUXURY') NOT NULL," +
                            "PRIMARY KEY (`number`));" +
                            "CREATE TABLE IF NOT EXISTS "+beds+" (" +
                            "`code` VARCHAR(15) NOT NULL," +
                            "`Room_number` VARCHAR(10) NOT NULL," +
                            "`size` ENUM('SINGLE', 'DOUBLE') NOT NULL," +
                            "PRIMARY KEY (`code`)," +
                            "INDEX `fk_Bed_Room_idx` (`Room_number` ASC)," +
                            "CONSTRAINT `fk_Bed_Room`" +
                            "FOREIGN KEY (`Room_number`)" +
                            "REFERENCES "+rooms+" (`number`)" +
                            "ON DELETE NO ACTION " +
                            "ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS "+booked+" (" +
                            "`id` INT NOT NULL," +
                            "`Booking_reference` VARCHAR(6) NOT NULL," +
                            "`Room_number` VARCHAR(10) NOT NULL," +
                            "INDEX `fk_BookedRoom_Booking1_idx` (`Booking_reference` ASC)," +
                            "INDEX `fk_BookedRoom_Room1_idx` (`Room_number` ASC)," +
                            "CONSTRAINT `fk_BookedRoom_Booking1`" +
                            "FOREIGN KEY (`Booking_reference`)" +
                            "REFERENCES "+books+" (`reference`)" +
                            "ON DELETE NO ACTION " +
                            "ON UPDATE NO ACTION," +
                            "CONSTRAINT `fk_BookedRoom_Room1`" +
                            "FOREIGN KEY (`Room_number`)" +
                            "REFERENCES "+rooms+" (`number`)" +
                            "ON DELETE NO ACTION " +
                            "ON UPDATE NO ACTION);"
            );
        }
        catch (Exception ex){
            Logger.logException(ex);
        }
        //out.println(test);

       //(`steamId` varchar(32) NOT NULL,`balance` decimal(15,2) NOT NULL DEFAULT '25.00',`lastUpdated` timestamp NOT NULL DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,PRIMARY KEY (`steamId`)) ");
    }

    public Object executeQuery(QueryType type, String query)
    {
        // This method is to reduce the amount of copy paste that there was within this class.
        // Initiate result and connection globally instead of within TryCatch context.
        Object result = null;
        Connection connection = createConnection();
        try
        {
            // Initialize command within try context, and execute within it as well.
            Statement command = connection.createStatement();
            result = type == QueryType.UPDATE ? command.executeUpdate(query) : type == QueryType.READER ? command.executeQuery(query) : command.execute(query);
            connection.close();
        }
        catch (Exception ex)
        {
            // Catch and log any errors during execution, like connection or similar.
            Logger.logException(ex);
        }

        return result;
    }

    public static Connection createConnection(){
        Connection conn = null;
        try {
            //System.out.println(MainFX.config.DatabaseAddress);
            conn = DriverManager.getConnection(MainFX.config.DatabaseAddress, MainFX.config.DatabaseUsername, MainFX.config.DatabasePassword);
        } catch (SQLException ex) {
            Logger.logException(ex);
        }

        return conn;
    }
}
