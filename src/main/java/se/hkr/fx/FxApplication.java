package se.hkr.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import se.hkr.bookings.BookingDao;
import se.hkr.config.Configuration;
import se.hkr.config.JsonConfiguration;
import se.hkr.customer.CustomerDao;
import se.hkr.data.ConnectionProvider;
import se.hkr.data.MySQLConnectionProvider;
import se.hkr.user.UserDao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FxApplication extends Application {

    public static short SCENE_HEIGHT = 440;
    public static short SCENE_WIDTH = 600;
    private Configuration configuration;
    private LoginCus cusLogin;
    private LoginEmp empLogin;
    private HomeStage homeStage;

    @Override
    public void start(Stage stage) {

        stage.setResizable(false);
        try {
            this.configuration = new JsonConfiguration("config.json");
            ConnectionProvider connectionProvider = new MySQLConnectionProvider(
                    this.configuration.getString("DatabaseHost"),
                    this.configuration.getString("DatabaseUsername"),
                    this.configuration.getString("DatabasePassword"),
                    this.configuration.getString("DatabaseName"),
                    this.configuration.getInt("DatabasePort")
            );
            CustomerDao customerDao = new CustomerDao(connectionProvider);
            UserDao userDao = new UserDao(connectionProvider);
            BookingDao bookingDao = new BookingDao(connectionProvider);

            homeStage = new HomeStage();
            cusLogin = new LoginCus(customerDao, userDao, bookingDao);
            empLogin = new LoginEmp(userDao, customerDao, bookingDao);
            initializeEvents();

//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('101', '1', 'ECONOMY');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('102', '1', 'ECONOMY');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('103', '1', 'ECONOMY');
//        UPDATE `hotel`.`Room` SET `number`='100', `floor`='1' WHERE `number`='301A';
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('201', '2', 'MIDDLE');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('202', '2', 'MIDDLE');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('203', '2', 'MIDDLE');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('200', '2', 'MIDDLE');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('300', '3', 'LUXURY');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('301', '3', 'LUXURY');
//        INSERT INTO `hotel`.`Room` (`number`, `floor`, `class`) VALUES ('302', '3', 'LUXURY');

            stage.setScene(homeStage.getScene());
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeEvents(){

        homeStage.getButton("Customer").setOnAction(event -> cusLogin.show());
        homeStage.getButton("Employee").setOnAction(event -> empLogin.show());
    }

    public static boolean equals(char[] str1, char[] str2){
        if(str1.length != str2.length)
            return false;
        for (short i = 0; i < str1.length; i++)
            if(str1[i] != str2[i])
                return false;


        return true;
    }

}
