package hkrFX;

import hkr.Person;
import hkrDB.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class AddBooking extends Stage {

    private DatabaseManager.Profile profile;
    private DatePicker movein;
    private DatePicker moveout;
    private ComboBox guests;
    private ComboBox rooms;
    protected Pane pane;
    private PersonalAreaCus session;

    public AddBooking(PersonalAreaCus session, DatabaseManager.Profile profile){
        this.profile = profile;
        this.session = session;
        createScene();
    }

    private void createScene(){
        Label label = new Label();
        label.setPrefHeight(17.0);
        label.setPrefWidth(115.0);
        label.setTextFill(Paint.valueOf("#575757"));
        label.setLayoutX(69.0);
        label.setLayoutY(43.0);
        label.setText("Arrival date");

        Label label2 = new Label();
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(115.0);
        label2.setTextFill(Paint.valueOf("#575757"));
        label2.setLayoutX(69.0);
        label2.setLayoutY(102.0);
        label2.setText("Departure date");

        Label label3 = new Label();
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(115.0);
        label3.setTextFill(Paint.valueOf("#575757"));
        label3.setLayoutX(69.0);
        label3.setLayoutY(162.0);
        label3.setText("Number of guest");

        guests = new ComboBox(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        guests.setPrefHeight(27.0);
        guests.setPrefWidth(76.0);
        guests.setLayoutX(69.0);
        guests.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
        guests.setLayoutY(179.0);

        rooms = new ComboBox();
        rooms.setPrefHeight(27.0);
        rooms.setPrefWidth(76.0);
        rooms.setLayoutX(69.0);
        rooms.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
        rooms.setLayoutY(239.0);

        Button button2 = new Button();
        button2.setPrefHeight(27.0);
        button2.setPrefWidth(76.0);
        button2.setLayoutX(150);
        button2.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
        button2.setLayoutY(332);
        button2.setText("Back");
        button2.setMnemonicParsing(false);
        button2.setOnAction(event -> {
            session.borderpane.setRight(session.scrollPane);
        });

        Button button = new Button();
        button.setPrefHeight(27.0);
        button.setPrefWidth(76.0);
        button.setLayoutX(308);
        button.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
        button.setLayoutY(332);
        button.setText("Create");
        button.setMnemonicParsing(false);
        button.setOnAction(event -> {
            closeStage();
        });

        movein = new DatePicker();
        movein.setLayoutX(69.0);
        movein.setLayoutY(60.0);
        movein.setOnAction(event -> {
            rooms.setValue(null);
            System.out.println(moveout.getValue() == null);
            System.out.println(movein.getValue() == null);
            if (moveout.getValue() != null && movein.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
            else
                rooms.setItems(null);
        });

        moveout = new DatePicker();
        moveout.setLayoutX(69.0);
        moveout.setLayoutY(119.0);
        moveout.setOnAction(event -> {
            rooms.setValue(null);
            if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
            else
                rooms.setItems(null);
        });

        pane = new Pane();
        pane.setPrefHeight(400.0);
        pane.setPrefWidth(427.0);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(movein, moveout, button, button2, rooms, guests, label, label2, label3);

        this.setScene(new Scene(pane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT));
    }

    private void emptyData(){
        rooms.setValue(null);
        moveout.setValue(null);
        movein.setValue(null);
    }

    private void closeStage(){
        if(rooms != null){
            MainFX.databaseManager.createBooking(profile.cId, movein.getValue(), moveout.getValue(), (int)guests.getValue(), rooms.getValue().toString());
            session.borderpane.setRight(session.scrollPane);
            emptyData();
        }
    }
}

//.setOnAction(event -> {
//        rooms.setValue(null);
//        if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
//        rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
//        else
//        rooms.setItems(null);
//        });
//        createScene();
//        }
//@ -281,6 +281,16 @@ public class BookingStage extends Stage{
//});
//        }
//
//private void emptyData(){
//        rooms.setValue(null);
//        for(TextField t : fields){
//        t.setText(null);
//        }
//        for(DatePicker d : dates){
//        d.setValue(null);
//        }
//        }

