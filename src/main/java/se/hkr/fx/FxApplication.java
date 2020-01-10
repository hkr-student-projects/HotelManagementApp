package se.hkr.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import se.hkr.bookings.BookingDao;
import se.hkr.config.Configuration;
import se.hkr.config.JsonConfiguration;
import se.hkr.data.ConnectionProvider;
import se.hkr.data.MySQLConnectionProvider;
import se.hkr.rooms.RoomDao;

public class FxApplication extends Application {
    public static short SCENE_HEIGHT = 440;
    public static short SCENE_WIDTH = 600;
    private BookingInfoStage bookingInfoStage;
    private HomeStage homeStage;
    private Configuration configuration;

    @Override
    public void start(Stage stage) {
        try {
            this.configuration = new JsonConfiguration("config.json");
            ConnectionProvider connectionProvider = new MySQLConnectionProvider(
                    this.configuration.getString("DatabaseHost"),
                    this.configuration.getString("DatabaseUsername"),
                    this.configuration.getString("DatabasePassword"),
                    this.configuration.getString("DatabaseName"),
                    this.configuration.getInt("DatabasePort")
            );
            RoomDao roomDao = new RoomDao(connectionProvider);
            BookingDao bookingDao = new BookingDao(connectionProvider);

            stage.setResizable(false);

            this.bookingInfoStage = new BookingInfoStage(roomDao, bookingDao);
            this.homeStage = new HomeStage();

            initializeEvents();

            stage.setScene(homeStage.getScene());
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeEvents(){
        homeStage.getButton("Customer").setOnAction(event -> bookingInfoStage.show());

        /*
        TODO: Employees.
        homeStage.getButton("Employee").setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //new ReceptionStage();

            }
        });*/

        homeStage.getButton("Admin").setOnAction(event -> new LoginStage());

        //scene.setFill(Color.TRANSPARENT);
        //stage.initStyle(StageStyle.TRANSPARENT);
    }

    private void unloadConfig() throws Exception{
        this.configuration.close();
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
