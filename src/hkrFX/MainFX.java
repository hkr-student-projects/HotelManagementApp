package hkrFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

interface ISerializable<T> {

    public T Serialize();
}

public class MainFX extends Application {

    public static short SCENE_HEIGHT = 440;
    public static short SCENE_WIDTH = 600;
    private BookingInfoStage bookingInfoStage;
    private HomeStage homeStage;


    @Override
    public void start(Stage stage) throws IOException {

        bookingInfoStage = new BookingInfoStage();
        homeStage = new HomeStage();
        stage.setResizable(false);
        initializeEvents();
        stage.setScene(homeStage.getScene());
        stage.show();

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

    public static boolean equals(char[] str1, char[] str2){
        if(str1.length != str2.length)
            return false;
        for (short i = 0; i < str1.length; i++)
            if(str1[i] != str2[i])
                return false;


        return true;
    }
}
