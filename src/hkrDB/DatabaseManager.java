package hkrDB;

import hkrFX.BookingStage;
import hkrFX.Logger;
import hkrFX.MainFX;

import java.sql.*;
import static java.lang.System.out;

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
                    "CREATE TABLE IF NOT EXISTS " + books + " (" +
                            "`refNumber` VARCHAR(6) NOT NULL," +
                            "PRIMARY KEY (`refNumber`));" +
                            "CREATE TABLE IF NOT EXISTS " + clients + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`Booking_refNumber` VARCHAR(6) NOT NULL," +
                            "`SSN` VARCHAR(13) NOT NULL," +
                            "`Name` VARCHAR(45) NOT NULL," +
                            "`MiddleName` VARCHAR(45) NULL," +
                            "`Surname` VARCHAR(45) NOT NULL," +
                            "`Address` VARCHAR(45) NOT NULL," +
                            "`Phone` VARCHAR(15) NOT NULL," +
                            "PRIMARY KEY (`id`)," +
                            //"INDEX `fk_Customer_Booking1_idx` (`Booking_refNumber` ASC)," +
                            //"CONSTRAINT `fk_Customer_Booking1`" +
                            "FOREIGN KEY (`Booking_refNumber`)" +
                            "REFERENCES " + books + " (`refNumber`));" +
                            //"ON DELETE CASCADE " +
                            //"ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS " + rooms + " (" +
                            "`number` VARCHAR(10) NOT NULL," +
                            "`floor` SMALLINT(3) NOT NULL," +
                            "`class` ENUM('ECONOMY', 'MIDDLE', 'LUXURY') NOT NULL," +
                            "`Booking_refNumber` VARCHAR(6) NOT NULL," +
                            "PRIMARY KEY (`number`)," +
                            //"INDEX `fk_Room_Booking1_idx` (`Booking_refNumber` ASC)," +
                            //"CONSTRAINT `fk_Room_Booking1`" +
                            "FOREIGN KEY (`Booking_refNumber`)" +
                            "REFERENCES "+ books +" (`refNumber`));" +
                            //"ON DELETE CASCADE " +
                            //"ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS " + beds + " (" +
                            "`code` VARCHAR(15) NOT NULL," +
                            "`Room_number` VARCHAR(10) NOT NULL," +
                            "`size` ENUM('SINGLE', 'DOUBLE') NULL," +
                            "PRIMARY KEY (`code`));"
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
