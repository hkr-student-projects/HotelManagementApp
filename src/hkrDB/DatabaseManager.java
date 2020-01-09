package hkrDB;

import hkrFX.Logger;
import hkrFX.MainFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

enum QueryType{
    UPDATE,//INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE
    READER,//SELECT -> ResultSet
    BOOL//ALL -> SELECT ? true : false
}

public class DatabaseManager {

    private final String rooms = "`hotel`.`Room`";
    private final String clients = "`hotel`.`Customer`";
    private final String books = "`hotel`.`Booking`";
    private final String beds = "`hotel`.`Bed`";
    private final String orders = "`hotel`.`CustomerOrder`";
    private final String booked = "`hotel`.`BookedRoom`";

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

    public ObservableList<String> getAvailableRooms(LocalDate movein, LocalDate moveout){
        ArrayList<String> arooms = new ArrayList<>();
        try{
            try (Connection conn = createConnection()){

                PreparedStatement pst = conn.prepareStatement("SELECT `Room_number` As Room\n" +
                        "FROM hotel.BookedRoom, hotel.Booking \n" +
                        "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND `Room_number` NOT IN\n" +
                        "(SELECT `Room_number`\n" +
                        "FROM hotel.BookedRoom, hotel.Booking \n" +
                        "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND\n" +
                        "(\t\n" +
                        "\t(('"+ movein +"' >= `movein` AND '"+ movein +"' < `moveout`) OR ('"+ moveout +"' >= `movein` AND '"+ moveout +"' <= `moveout`)) \n" +
                        "    OR \n" +
                        "\t(`movein` >= '"+ movein +"' AND `moveout` <= '"+ moveout +"')\n" +
                        "))\n" +
                        "UNION SELECT `number`\n" +
                        "FROM hotel.Room \n" +
                        "WHERE `number` \n" +
                        "NOT IN (SELECT `Room_number` \n" +
                        "FROM hotel.BookedRoom)\n" +
                        "ORDER BY Room;");

                boolean isResult = pst.execute();
                do {
                    try (ResultSet rs = pst.getResultSet()) {

                        while (rs.next()) {

                            arooms.add(rs.getString(1));
                        }

                        isResult = pst.getMoreResults();
                    }

                } while (isResult);
            }
        }
        catch(Exception ex){
            Logger.logException(ex);
        }
        //arooms.forEach(e -> System.out.println(e));
        return FXCollections.observableArrayList(arooms);
    }

    public int addEntry(String ssn, String name, String surname, String addr, String phone, LocalDate movein, LocalDate moveout, String roomnum){
        return (int)executeQuery(QueryType.UPDATE,
                "INSERT INTO "+books+" " +
                        "(`movein`,`moveout`) " +
                        "VALUES ('"+movein+"','"+moveout+"');" +
                        "SELECT @bid:=LAST_INSERT_ID();" +
                        "INSERT INTO " + clients + " " +
                        "(`ssn`,`name`,`surname`,`address`,`phone`) " +
                        "VALUES ('" + ssn + "','" + name + "','" + surname + "','" + addr + "','" + phone + "');" +
                        "SELECT @cid:=LAST_INSERT_ID();" +
                        "INSERT INTO "+ orders +" " +
                        "(`Customer_id`,`Booking_reference`) " +
                        "VALUES (@cid,@bid);" +
                        "INSERT INTO "+ booked +" " +
                        "(`Booking_reference`,`Room_number`) " +
                        "VALUES (@bid,'"+ roomnum +"');"
        );
    }

    public int addRoom(String number, short floor, RoomClass rclass){
        return (int)executeQuery(QueryType.UPDATE,
                "INSERT INTO "+rooms+" " +
                        "(`number`,`floor`,`class`) " +
                        "VALUES ('"+ number +"','"+ floor +"','"+ rclass +"');"
        );
    }

//    public String[] getRoomsDate(LocalDate movein, LocalDate moveout){
//        ResultSet result = (ResultSet)executeQuery(QueryType.READER,
//                "SELECT "
//        );
//    }

//    private String getAvailableRoom(){
//        String room = null;
//        ResultSet res = (ResultSet)executeQuery(QueryType.READER,
//                ""
//        );
//    }

//    "UPDATE "+books+" SET =concat(" +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@lid)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
//            "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed)*36+1, 1)" +
//            ") " +
//            "WHERE id=@lid;

    public void checkSchema()
    {
        try {
            executeQuery(QueryType.BOOL,
                    " CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8;" +
                            "CREATE TABLE IF NOT EXISTS " + books + " (" +
                            "`reference` INT NOT NULL AUTO_INCREMENT," +
                            "`movein` DATE NOT NULL," +
                            "`moveout` DATE NOT NULL," +
                            "PRIMARY KEY (`reference`));" +
                            "CREATE TABLE IF NOT EXISTS " + clients + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`ssn` VARCHAR(45) NOT NULL," +
                            "`name` VARCHAR(45) NOT NULL," +
//                            "`middlename` VARCHAR(45) DEFAULT NULL," +
                            "`surname` VARCHAR(45) NOT NULL," +
                            "`phone` VARCHAR(15) NOT NULL," +
                            "`address` VARCHAR(45) NOT NULL," +
                            "PRIMARY KEY (`id`));" +
                            "CREATE TABLE IF NOT EXISTS " + orders + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`Customer_id` INT NOT NULL," +
                            "`Booking_reference` INT NOT NULL," +
                            "PRIMARY KEY (`id`)," +
                            "INDEX `fk_Order_Customer1_idx` (`Customer_id` ASC)," +
                            "INDEX `fk_Order_Booking1_idx` (`Booking_reference` ASC)," +
                            "CONSTRAINT `fk_Order_Customer1`" +
                            "FOREIGN KEY (`Customer_id`)" +
                            "REFERENCES " + clients + " (`id`)" +
                            "ON DELETE CASCADE " +
                            "ON UPDATE NO ACTION," +
                            "CONSTRAINT `fk_Order_Booking1`" +
                            "FOREIGN KEY (`Booking_reference`)" +
                            "REFERENCES " + books + " (`reference`)" +
                            "ON DELETE CASCADE " +
                            "ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS " + rooms + " (" +
                            "`number` VARCHAR(10) NOT NULL," +
                            "`floor` SMALLINT(3) NOT NULL," +
                            "`class` ENUM('ECONOMY', 'MIDDLE', 'LUXURY') NOT NULL," +
                            "PRIMARY KEY (`number`));" +
                            "CREATE TABLE IF NOT EXISTS " + beds + " (" +
                            "`code` VARCHAR(15) NOT NULL," +
                            "`Room_number` VARCHAR(10) NOT NULL," +
                            "`size` ENUM('SINGLE', 'DOUBLE') NOT NULL," +
                            "PRIMARY KEY (`code`)," +
                            "INDEX `fk_Bed_Room_idx` (`Room_number` ASC)," +
                            "CONSTRAINT `fk_Bed_Room`" +
                            "FOREIGN KEY (`Room_number`)" +
                            "REFERENCES " + rooms + " (`number`)" +
                            "ON DELETE CASCADE " +
                            "ON UPDATE NO ACTION);" +
                            "CREATE TABLE IF NOT EXISTS " + booked + " (" +
                            "`id` INT NOT NULL AUTO_INCREMENT," +
                            "`Booking_reference` INT NOT NULL," +
                            "`Room_number` VARCHAR(10) NOT NULL," +
                            "PRIMARY KEY (`id`)," +
                            "INDEX `fk_BookedRoom_Booking1_idx` (`Booking_reference` ASC)," +
                            "INDEX `fk_BookedRoom_Room1_idx` (`Room_number` ASC)," +
                            "CONSTRAINT `fk_BookedRoom_Booking1`" +
                            "FOREIGN KEY (`Booking_reference`)" +
                            "REFERENCES " + books + " (`reference`)" +
                            "ON DELETE CASCADE " +
                            "ON UPDATE NO ACTION," +
                            "CONSTRAINT `fk_BookedRoom_Room1`" +
                            "FOREIGN KEY (`Room_number`)" +
                            "REFERENCES " + rooms + " (`number`)" +
                            "ON DELETE CASCADE " +
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
        Object result = null;
        Connection connection = createConnection();
        try
        {
            //Statement command = connection.createStatement();
            PreparedStatement command = connection.prepareStatement(query);
            boolean isResult = command.execute(query);
            result = type == QueryType.READER ? command : command.getUpdateCount();
            //connection.close();

            //A ResultSet object is automatically closed when the Statement object that generated it is closed,
            //re-executed, or used to retrieve the next result from a sequence of multiple results.
        }
        catch (Exception ex)
        {
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


//        try{
//                String url = "jdbc:mysql://localhost/hotel?&allowMultiQueries=true&serverTimezone=Europe/Stockholm&useSSL=false";
//                String username = "root";
//                String password = "91rasuho";
//                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//
//                try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//                PreparedStatement pst = conn.prepareStatement("SELECT @rin:='2019-12-12';SELECT @rout:='2019-12-19';SELECT `Room_number` As Room\n" +
//                "FROM hotel.BookedRoom, hotel.Booking \n" +
//                "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND `Room_number` NOT IN\n" +
//                "(SELECT `Room_number`\n" +
//                "FROM hotel.BookedRoom, hotel.Booking \n" +
//                "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND\n" +
//                "(\t\n" +
//                "\t((@rin >= `movein` AND @rin < `moveout`) OR (@rout >= `movein` AND @rout <= `moveout`)) \n" +
//                "    OR \n" +
//                "\t(`movein` >= @rin AND `moveout` <= @rout)\n" +
//                "))\n" +
//                "UNION SELECT `number`\n" +
//                "FROM hotel.Room \n" +
//                "WHERE `number` \n" +
//                "NOT IN (SELECT `Room_number` \n" +
//                "FROM hotel.BookedRoom)\n" +
//                "ORDER BY Room;");
//                boolean isResult = pst.execute();
//
//                do {
//                try (ResultSet rs = pst.getResultSet()) {
//
//                while (rs.next()) {
//
//                System.out.println(rs.getString(1));
//                }
//
//                isResult = pst.getMoreResults();
//                }
//
//                } while (isResult);
//                }
//                }
//                catch(Exception ex){
//                System.out.println("Connection failed...");
//
//                System.out.println(ex);
//                }
