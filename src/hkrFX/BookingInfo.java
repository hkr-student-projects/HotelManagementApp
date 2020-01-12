package hkrFX;

import hkrDB.DatabaseManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class BookingInfo extends Stage {

    private PersonalAreaCus session;
    private DatabaseManager.Booking booking;
    public Pane paneMain;

    public BookingInfo(DatabaseManager.Booking booking, PersonalAreaCus session){
        this.setResizable(false);
        this.booking = booking;
        this.session = session;
        createScene();
    }

    private void createScene(){

        Text text = new Text();
        text.setStrokeWidth(0.0);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setLayoutX(67.0);
        text.setLayoutY(84.0);
        text.setText("Booking #" + Integer.toHexString(booking.bId) +"");
        text.setWrappingWidth(263.21875);
        text.setFont(new Font("Futura Medium", 24));

        Text text2 = new Text();
        text2.setStrokeWidth(0.0);
        text2.setStrokeType(StrokeType.OUTSIDE);
        text2.setLayoutX(68.0);
        text2.setLayoutY(156.0);
        text2.setText("Move in");
        text2.setFont(new Font("AppleGothic Regular", 18));

        Text text3 = new Text();
        text3.setStrokeWidth(0.0);
        text3.setStrokeType(StrokeType.OUTSIDE);
        text3.setLayoutX(68.0);
        text3.setLayoutY(197.0);
        text3.setText("Move out");
        text3.setFont(new Font("AppleGothic Regular", 18));

        Text text4 = new Text();
        text4.setStrokeWidth(0.0);
        text4.setStrokeType(StrokeType.OUTSIDE);
        text4.setLayoutX(68.0);
        text4.setLayoutY(235.0);
        text4.setText("Room");
        text4.setFont(new Font("AppleGothic Regular", 18));

        Text text5 = new Text();
        text5.setStrokeWidth(0.0);
        text5.setStrokeType(StrokeType.OUTSIDE);
        text5.setLayoutX(68.0);
        text5.setLayoutY(274.0);
        text5.setText("Guests");
        text5.setFont(new Font("AppleGothic Regular", 18));

        Text text6 = new Text();
        text6.setStrokeWidth(0.0);
        text6.setStrokeType(StrokeType.OUTSIDE);
        text6.setLayoutX(163.0);
        text6.setLayoutY(158.0);
        text6.setText(booking.movein.format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        text6.setWrappingWidth(166.97265625);
        text6.setFont(new Font("AppleGothic Regular", 18));

        Text text7 = new Text();
        text7.setStrokeWidth(0.0);
        text7.setStrokeType(StrokeType.OUTSIDE);
        text7.setLayoutX(163.0);
        text7.setLayoutY(199.0);
        text7.setText(booking.moveout.format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        text7.setWrappingWidth(166.97265625);
        text7.setFont(new Font("AppleGothic Regular", 18));

        Text text8 = new Text();
        text8.setStrokeWidth(0.0);
        text8.setStrokeType(StrokeType.OUTSIDE);
        text8.setLayoutX(163.0);
        text8.setLayoutY(234.0);
        text8.setText(booking.room);
        text8.setFont(new Font("AppleGothic Regular", 18));

        Text text9 = new Text();
        text9.setStrokeWidth(0.0);
        text9.setStrokeType(StrokeType.OUTSIDE);
        text9.setTextAlignment(TextAlignment.CENTER);
        text9.setLayoutX(147.0);
        text9.setLayoutY(273.0);
        text9.setText(""+booking.guests+"");
        text9.setWrappingWidth(42.582000732421875);
        text9.setFont(new Font("AppleGothic Regular", 18));

        Button button = new Button();
        button.getStylesheets().add("hkrFX/css/style.css");
        button.getStyleClass().add("deleteBook");
        button.setTextFill(Paint.valueOf("RED"));
        button.setLayoutX(308.0);
        button.setStyle("-fx-background-radius: 9; -fx-background-color: #ededed;");
        button.setLayoutY(332.0);
        button.setText("Delete");
        button.setMnemonicParsing(false);
        button.setOnAction(event -> {
            MainFX.databaseManager.deleteBooking(booking.bId);
            session.books.remove(this.booking);
            //rebuild book list in personal area
            this.close();
        });

        Button button2 = new Button();
        button2.getStylesheets().add("hkrFX/css/style.css");
        button2.getStyleClass().add("backBook");
        button2.setTextFill(Paint.valueOf("GREY"));
        button2.setLayoutX(150);
        button2.setStyle("-fx-background-radius: 9; -fx-background-color: #ededed;");
        button2.setLayoutY(332.0);
        button2.setText("Back");
        button2.setMnemonicParsing(false);
        button2.setOnAction(event -> {
            session.borderpane.setRight(session.scrollPane);
        });

        Pane pane = new Pane();
        pane.setPrefHeight(379.0);
        pane.setPrefWidth(398.0);
        pane.setLayoutX(14.0);
        pane.setStyle("-fx-background-color: white;");
        pane.setLayoutY(14.0);

        pane.getChildren().addAll(button, button2, text, text2, text3, text4, text5, text6, text7, text8, text9);

        paneMain = new Pane();
        paneMain.setPrefHeight(400.0);
        paneMain.setPrefWidth(427.0);
        paneMain.setStyle("-fx-background-color: white;");
        paneMain.getChildren().add(pane);

        Scene scene = new Scene(paneMain, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFX/css/style.css");
        this.setScene(scene);
    }
}
