package hkrDB;

import hkrFX.BookingStage;
import hkrFX.Logger;
import hkrFX.MainFX;

import java.sql.*;

enum QueryType{
    UPDATE,//INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE
    READER,//SELECT -> ResultSet
    BOOL//ALL -> SELECT ? true : false
}

public class DatabaseManager {

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }
        catch(Exception ex){
            Logger.logException("Connection failed..."+ ex.getMessage() +"");
            System.out.println(ex.getStackTrace());
            StackTraceElement[] st = ex.getStackTrace();
            for(StackTraceElement se : st){
                System.out.println(se.getMethodName());
            }
        }
    }

    public DatabaseManager(){
        checkSchema();
    }

    public void checkSchema()
    {
        Object test = ExecuteQuery(QueryType.UPDATE,
                "CREATE TABLE products (Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)"
        );

       //(`steamId` varchar(32) NOT NULL,`balance` decimal(15,2) NOT NULL DEFAULT '25.00',`lastUpdated` timestamp NOT NULL DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,PRIMARY KEY (`steamId`)) ");
    }

    public Object ExecuteQuery(QueryType type, String query)
    {
        // This method is to reduce the amount of copy paste that there was within this class.
        // Initiate result and connection globally instead of within TryCatch context.
        Connection connection = createConnection();
        Object result = null;

        try
        {
            // Initialize command within try context, and execute within it as well.
            Statement command = connection.createStatement();
            result = type == QueryType.UPDATE ? command.executeUpdate(query) : type == QueryType.READER ? command.executeQuery(query) : command.execute(query);
        }
        catch (Exception ex)
        {
            // Catch and log any errors during execution, like connection or similar.
            Logger.logException(ex.getMessage());
            System.out.println(ex.getStackTrace());
            StackTraceElement[] st = ex.getStackTrace();
            for(StackTraceElement se : st){
                System.out.println(se.getMethodName());
            }
        }
//        finally
//        {
//            // No matter what happens, close the connection at the end of execution.
//            connection.close();
//        }

        return result;
    }

    public static Connection createConnection(){
        Connection conn = null;
        try {
            //System.out.println(MainFX.config.DatabaseAddress);
            conn = DriverManager.getConnection(MainFX.config.DatabaseAddress, MainFX.config.DatabaseUsername, MainFX.config.DatabasePassword);
        } catch (SQLException e) {
            Logger.logException(e.getMessage());
            System.out.println(e.getStackTrace());
            StackTraceElement[] st = e.getStackTrace();
            for(StackTraceElement se : st){
                System.out.println(se.getMethodName());
            }
        }

        return conn;
    }
}
