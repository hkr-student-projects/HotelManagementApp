package hkrFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static short SCENE_HEIGHT = 437;
    public static short SCENE_WIDTH = 600;
    private BookingStage bookingStage;
    private HomeStage homeStage;


    @Override
    public void start(Stage stage) throws IOException {

        bookingStage = new BookingStage();
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
                bookingStage.show();
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
}
