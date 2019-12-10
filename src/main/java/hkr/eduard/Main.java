package hkr.eduard;

import hkr.eduard.customer.CustomerDao;
import hkr.eduard.database.ConnectionProvider;
import hkr.eduard.database.MySQLConnectionProvider;
import hkr.eduard.users.UserDao;
import hkr.eduard.users.UserLogic;

public class Main {

    public static void main(String[] args) {
        try {
            ConnectionProvider connectionProvider = new MySQLConnectionProvider("localhost", "root", "", "hotel", 3306);

            UserLogic userLogic = new UserLogic(new UserDao(connectionProvider));

            HotelApplication hotelApplication = new HotelApplication(userLogic);
            hotelApplication.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
