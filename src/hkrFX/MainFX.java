package hkrFX;

import hkrDB.DatabaseManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

interface IDeserializable<T> {

    public T Deserialize(FileReader reader);
}

interface ISerializable<T> {

    public T Serialize();
}

public class MainFX extends Application {

    public static short SCENE_HEIGHT = 440;
    public static short SCENE_WIDTH = 600;
    public static Config config;
    public static DatabaseManager databaseManager;
    private BookingInfoStage bookingInfoStage;
    private HomeStage homeStage;

    @Override
    public void start(Stage stage) {

        stage.setResizable(false);
        bookingInfoStage = new BookingInfoStage();
        homeStage = new HomeStage();
        config = new Config();
        loadConfig();
        databaseManager = new DatabaseManager();

        initializeEvents();

//        LocalDateTime myDateObj = LocalDateTime.;
//        System.out.println("Before formatting: " + myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);
//        Before Formatting: 2020-01-08T09:28:22.691983
//        After Formatting: 08-01-2020 09:28:22

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

//        System.out.println(Translator.translate("field_empty", "MyField",  this.getClass().getName()));
//        System.out.println(Translator.translate("keyid_not_found", "SomeKey"));
//        Logger.logError(Translator.translate("button_not_found", "SomeButton",  this.getClass().getName()));
//        Logger.log("Logging a message in MainFX class");
//        Logger.logException("Logging an exception in MainFX class");
        stage.setScene(homeStage.getScene());
        stage.show();
        //ObservableList<String> availableRooms = databaseManager.getAvailableRooms(LocalDate.of(2019, 12, 12), LocalDate.of(2019, 12, 19));
        //availableRooms.forEach(room -> out.println(room));
        //! before must be all initializations performed: like rooms
        //databaseManager.addRoom("301A", (short)3, RoomClass.ECONOMY);
        //databaseManager.addCustomer("1999-08-08","Tom","Bob","Cruise","Storagatan 8A","0734956722",
                //new Date(Calendar.getInstance().getTime().getTime()), new Date(Calendar.getInstance().getTime().getTime()), "301A");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    }



    private void initializeEvents(){

        homeStage.getButton("Customer").setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                bookingInfoStage.show();
            }
        });

        homeStage.getButton("Employee").setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //new ReceptionStage();

            }
        });

        homeStage.getButton("Admin").setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new LoginStage();
            }
        });

        //scene.setFill(Color.TRANSPARENT);
        //stage.initStyle(StageStyle.TRANSPARENT);
    }

    public void loadConfig(){
        File f = new File("config.json");
        if(!f.exists()){
            config.loadDefaults();
        }
        else {

            try (FileReader reader = new FileReader(f))
            {
                config.Deserialize(reader);
            }
            catch (IOException ex) {
                Logger.logException(ex);
            }
        }
    }

    private void unloadConfig(){
        config.writeConfig();
    }

//    private void loadDefaults(){
//
//    }

    public static boolean equals(char[] str1, char[] str2){
        if(str1.length != str2.length)
            return false;
        for (short i = 0; i < str1.length; i++)
            if(str1[i] != str2[i])
                return false;


        return true;
    }

}
