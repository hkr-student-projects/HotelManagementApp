package hkrFX;

import hkr.Person;
import hkrDB.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.time.LocalDate;

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
        label.setLayoutX(103);
        label.setLayoutY(102);
        label.setText("Arrival date");

        Label label2 = new Label();
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(115.0);
        label2.setTextFill(Paint.valueOf("#575757"));
        label2.setLayoutX(103);
        label2.setLayoutY(161);
        label2.setText("Departure date");

        Label label3 = new Label();
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(115.0);
        label3.setTextFill(Paint.valueOf("#575757"));
        label3.setLayoutX(103);
        label3.setLayoutY(221);
        label3.setText("Number of guest");

        Label label4 = new Label();
        label4.setPrefHeight(17.0);
        label4.setPrefWidth(115.0);
        label4.setTextFill(Paint.valueOf("#575757"));
        label4.setLayoutX(103);
        label4.setLayoutY(281);
        label4.setText("Available rooms");

        guests = new ComboBox(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        guests.setPrefHeight(27.0);
        guests.setPrefWidth(76.0);
        guests.setLayoutX(103);
        guests.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
        guests.setLayoutY(238);

        rooms = new ComboBox();
        rooms.setPrefHeight(27.0);
        rooms.setPrefWidth(76.0);
        rooms.setLayoutX(103);
        rooms.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
        rooms.setLayoutY(298);

        Button button2 = new Button();
        button2.setPrefHeight(27.0);
        button2.setPrefWidth(76.0);
        button2.setLayoutX(33);
        button2.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
        button2.setLayoutY(378);
        button2.setText("Back");
        button2.setMnemonicParsing(false);
        button2.setOnAction(event -> {
            session.borderpane.setRight(session.scrollPane);
        });

        Button button = new Button();
        button.setPrefHeight(27.0);
        button.setPrefWidth(76.0);
        button.setLayoutX(300);
        button.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
        button.setLayoutY(378);
        button.setText("Create");
        button.setMnemonicParsing(false);
        button.setOnAction(event -> {
            closeStage();
        });

        movein = new DatePicker();
        movein.setLayoutX(103);
        movein.setLayoutY(119);
        movein.setOnAction(event -> {
            rooms.setValue(null);
//            System.out.println(moveout.getValue() == null);
//            System.out.println(movein.getValue() == null);
            if (moveout.getValue() != null && movein.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
            else
                rooms.setItems(null);
        });
        movein.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        moveout = new DatePicker();
        moveout.setLayoutX(103);
        moveout.setLayoutY(178);
        moveout.setOnAction(event -> {
            rooms.setValue(null);
            if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
            else
                rooms.setItems(null);
        });
        moveout.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        pane = new Pane();
        pane.setPrefHeight(400.0);
        pane.setPrefWidth(427.0);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(movein, moveout, button, button2, rooms, guests, label, label2, label3, label4);

        this.setScene(new Scene(pane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT));
    }

    private void emptyLocalData(){
        rooms.setValue(null);
        moveout.setValue(null);
        movein.setValue(null);
    }

    private void closeStage(){
        if(rooms != null){
            MainFX.databaseManager.createBooking(profile.cId, movein.getValue(), moveout.getValue(), (int)guests.getValue(), rooms.getValue().toString());
            session.books = MainFX.databaseManager.getBookings(profile.cId);
            session.loadButtons();
            String color = "ffb053";
            if(session.injection.injected)
                color = PersonalAreaEmp.colorCode;
            new PopUP("Thank you! Your order has been placed!", ""+color+"").show();
            session.borderpane.setRight(session.scrollPane);
            emptyLocalData();
        }
    }
}

//        });
//
//        movein = new DatePicker();
//        movein.setLayoutX(69.0);
//        movein.setLayoutY(60.0);
//        movein.setOnAction(event -> {
//            rooms.setValue(null);
//            System.out.println(moveout.getValue() == null);
//            System.out.println(movein.getValue() == null);
//            if (moveout.getValue() != null && movein.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
//                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
//            else
//                rooms.setItems(null);
//        });
//
//        moveout = new DatePicker();
//        moveout.setLayoutX(69.0);
//        moveout.setLayoutY(119.0);
//        moveout.setOnAction(event -> {
//            rooms.setValue(null);
//            if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
//                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
//            else
//                rooms.setItems(null);
//        });
//    }
//
//    private void emptyLocalData(){
//        rooms.setValue(null);
//        moveout.setValue(null);
//        movein.setValue(null);
//    }
//
//    private void closeStage(){
//        if(rooms != null){
//            //add in DB
//            this.close();
//            emptyLocalData();
//        }
//    }
//}
//
////.setOnAction(event -> {
////        rooms.setValue(null);
////        if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
////        rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
////        else
////        rooms.setItems(null);
////        });
//>>>>>>> parent of 3b8aeea... Add
////        createScene();
////    }
////
////    private void createScene(){
////        Label label = new Label();
////        label.setPrefHeight(17.0);
////        label.setPrefWidth(115.0);
////        label.setTextFill(Paint.valueOf("#575757"));
////        label.setLayoutX(100);
////        label.setLayoutY(43.0);
////        label.setText("Arrival date");
////
////        Label label2 = new Label();
////        label2.setPrefHeight(17.0);
////        label2.setPrefWidth(115.0);
////        label2.setTextFill(Paint.valueOf("#575757"));
////        label2.setLayoutX(100);
////        label2.setLayoutY(102.0);
////        label2.setText("Departure date");
////
////        Label label3 = new Label();
////        label3.setPrefHeight(17.0);
////        label3.setPrefWidth(115.0);
////        label3.setTextFill(Paint.valueOf("#575757"));
////        label3.setLayoutX(100);
////        label3.setLayoutY(162.0);
////        label3.setText("Number of guest");
////
////        guests = new ComboBox(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
////        guests.setPrefHeight(27.0);
////        guests.setPrefWidth(76.0);
////        guests.setLayoutX(100);
////        guests.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
////        guests.setLayoutY(179.0);
////
////        rooms = new ComboBox();
////        rooms.setPrefHeight(27.0);
////        rooms.setPrefWidth(76.0);
////        rooms.setLayoutX(100);
////        rooms.setStyle("-fx-background-radius: 111; -fx-background-color: #ededed;");
////        rooms.setLayoutY(239.0);
////
////        Button button2 = new Button();
////        button2.setPrefHeight(27.0);
////        button2.setPrefWidth(76.0);
////        button2.setLayoutX(70);
////        button2.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
////        button2.setLayoutY(332);
////        button2.setText("Back");
////        button2.setMnemonicParsing(false);
////        button2.setOnAction(event -> {
////            session.borderpane.setRight(session.scrollPane);
////        });
////
////        Button button = new Button();
////        button.setPrefHeight(27.0);
////        button.setPrefWidth(76.0);
////        button.setLayoutX(308);
////        button.setStyle("-fx-background-color: #ededed; -fx-background-radius: 36;");
////        button.setLayoutY(332);
////        button.setText("Create");
////        button.setMnemonicParsing(false);
////        button.setOnAction(event -> {
////            closeStage();
////        });
////
////        movein = new DatePicker();
////        movein.setLayoutX(100);
////        movein.setLayoutY(60.0);
////        movein.setOnAction(event -> {
////            rooms.setValue(null);
////            System.out.println(moveout.getValue() == null);
////            System.out.println(movein.getValue() == null);
////            if (moveout.getValue() != null && movein.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
////                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
////            else
////                rooms.setItems(null);
////        });
////
////        moveout = new DatePicker();
////        moveout.setLayoutX(100);
////        moveout.setLayoutY(119.0);
////        moveout.setOnAction(event -> {
////            rooms.setValue(null);
////            if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
////                rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
////            else
////                rooms.setItems(null);
////        });
////<<<<<<< HEAD
////
////        pane = new Pane();
////        pane.setPrefHeight(400.0);
////        pane.setPrefWidth(427.0);
////        pane.setStyle("-fx-background-color: white;");
////        pane.getChildren().addAll(movein, moveout, button, button2, rooms, guests, label, label2, label3);
////
////        this.setScene(new Scene(pane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT));
////=======
////>>>>>>> parent of 3b8aeea... Add
////    }
////
////    private void emptyLocalData(){
////        rooms.setValue(null);
////        moveout.setValue(null);
////        movein.setValue(null);
////    }
////
////    private void closeStage(){
////        if(rooms != null){
////<<<<<<< HEAD
////            MainFX.databaseManager.createBooking(profile.cId, movein.getValue(), moveout.getValue(), (int)guests.getValue(), rooms.getValue().toString());
////            session.borderpane.setRight(session.scrollPane);
////            session.showUpdateBookings();
////=======
////            //add in DB
////            this.close();
////>>>>>>> parent of 3b8aeea... Add
////            emptyLocalData();
////        }
////    }
////}
////
//////.setOnAction(event -> {
//////        rooms.setValue(null);
//////        if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0)
//////        rooms.setItems(MainFX.databaseManager.getAvailableRooms(movein.getValue(), moveout.getValue()));
//////        else
//////        rooms.setItems(null);
//////        });
//////        createScene();
//////        }
//////@ -281,6 +281,16 @@ public class BookingStage extends Stage{
//////});
//////        }
//////
//////private void emptyLocalData(){
//////        rooms.setValue(null);
//////        for(TextField t : fields){
//////        t.setText(null);
//////        }
//////        for(DatePicker d : dates){
//////        d.setValue(null);
//////        }
//////        }
////
