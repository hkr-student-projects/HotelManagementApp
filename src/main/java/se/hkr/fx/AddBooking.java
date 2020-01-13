package se.hkr.fx;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.hkr.bookings.BookingDao;
import se.hkr.rooms.RoomDao;
import se.hkr.user.Profile;

public class AddBooking extends Stage {

    private Profile profile;
    private DatePicker movein;
    private DatePicker moveout;
    private ComboBox guests;
    private ComboBox rooms;
    protected Pane pane;
    private PersonalAreaCus session;

    private final RoomDao roomDao;
    private final BookingDao bookingDao;

    public AddBooking(PersonalAreaCus session, Profile profile, RoomDao roomDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.profile = profile;
        this.session = session;
        this.bookingDao = bookingDao;
        createScene();
    }

    private void createScene() {
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
            session.getBorderpane().setRight(session.getScrollPane());
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
            if (moveout.getValue() != null && movein.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0) {
                this.roomDao.getAvailableRooms(movein.getValue(), moveout.getValue(), roomValues -> {
                    rooms.setItems(roomValues);
                });
            } else {
                rooms.setItems(null);
            }
        });

        moveout = new DatePicker();
        moveout.setLayoutX(103);
        moveout.setLayoutY(178);
        moveout.setOnAction(event -> {
            rooms.setValue(null);
            if(movein.getValue() != null && moveout.getValue() != null && (moveout.getValue().compareTo(movein.getValue())) >= 0) {
                this.roomDao.getAvailableRooms(movein.getValue(), moveout.getValue(), roomValues -> {
                    rooms.setItems(roomValues);
                });
            } else {
                rooms.setItems(null);
            }
        });

        pane = new Pane();
        pane.setPrefHeight(400.0);
        pane.setPrefWidth(427.0);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(movein, moveout, button, button2, rooms, guests, label, label2, label3, label4);

        this.setScene(new Scene(pane, FxApplication.SCENE_WIDTH, FxApplication.SCENE_HEIGHT));
    }

    private void emptyLocalData() {
        rooms.setValue(null);
        moveout.setValue(null);
        movein.setValue(null);
    }

    private void closeStage() {
        if (rooms != null) {
            this.bookingDao.createBooking(profile.getcId(), movein.getValue(), moveout.getValue(), (int)guests.getValue(), rooms.getValue().toString());
            this.bookingDao.getBookings(this.profile.getcId(), bookings -> {
                this.session.setBooks(bookings);
                this.session.loadButtons();
            });
            session.loadButtons();
            new PopUP("Thank you! Your order has been placed!", "ffb053").show();
            session.getBorderpane().setRight(session.getScrollPane());
            emptyLocalData();
        }
    }
}
