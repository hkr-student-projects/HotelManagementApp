package hkrFX;

import com.mysql.cj.protocol.x.XProtocolRowInputStream;
import hkr.Main;
import hkrDB.DatabaseManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private TextField movein;
    private TextField moveout;
    private TextField room;
    private TextField guests;
    private Button button4;

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
        //text.setText("Booking #" + Integer.toHexString(booking.bId?format 0000A) +"");
        text.setText("Booking #"+booking.bId+"");
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

//        Text text6 = new Text();
//        text6.setStrokeWidth(0.0);
//        text6.setStrokeType(StrokeType.OUTSIDE);
//        text6.setLayoutX(163.0);
//        text6.setLayoutY(158.0);
//        text6.setText(booking.movein.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        text6.setWrappingWidth(166.97265625);
//        text6.setFont(new Font("AppleGothic Regular", 18));

        movein = new TextField();
        movein.setPrefHeight(38.0);
        movein.setPrefWidth(167.0);
        movein.setLayoutX(163.0);
        movein.setStyle("-fx-background-color: transparent;");
        movein.setLayoutY(158);
        movein.setText(booking.movein.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        movein.setFont(new Font("AppleGothic Regular", 18));
        movein.setEditable(false);

        moveout = new TextField();
        moveout.setPrefHeight(38.0);
        moveout.setPrefWidth(167.0);
        moveout.setLayoutX(163.0);
        moveout.setStyle("-fx-background-color: transparent;");
        moveout.setLayoutY(199.0);
        moveout.setText(booking.moveout.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        moveout.setFont(new Font("AppleGothic Regular", 18));
        moveout.setEditable(false);

//        Text text7 = new Text();
//        text7.setStrokeWidth(0.0);
//        text7.setStrokeType(StrokeType.OUTSIDE);
//        text7.setLayoutX(163.0);
//        text7.setLayoutY(199.0);
//        text7.setText(booking.moveout.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        text7.setWrappingWidth(166.97265625);
//        text7.setFont(new Font("AppleGothic Regular", 18));

//        Text text8 = new Text();
//        text8.setStrokeWidth(0.0);
//        text8.setStrokeType(StrokeType.OUTSIDE);
//        text8.setLayoutX(163.0);
//        text8.setLayoutY(234.0);
//        text8.setText(booking.room);
//        text8.setFont(new Font("AppleGothic Regular", 18));

        room = new TextField();
        room.setPrefHeight(38.0);
        room.setPrefWidth(167.0);
        room.setLayoutX(163.0);
        room.setStyle("-fx-background-color: transparent;");
        room.setLayoutY(234);
        room.setText(booking.room);
        room.setFont(new Font("AppleGothic Regular", 18));
        room.setEditable(false);

//        Text text9 = new Text();
//        text9.setStrokeWidth(0.0);
//        text9.setStrokeType(StrokeType.OUTSIDE);
//        text9.setTextAlignment(TextAlignment.CENTER);
//        text9.setLayoutX(147.0);
//        text9.setLayoutY(273.0);
//        text9.setText(""+booking.guests+"");
//        text9.setWrappingWidth(42.582000732421875);
//        text9.setFont(new Font("AppleGothic Regular", 18));

        guests = new TextField();
        guests.setPrefHeight(38.0);
        guests.setPrefWidth(167.0);
        guests.setLayoutX(147);
        guests.setStyle("-fx-background-color: transparent;");
        guests.setLayoutY(237);
        guests.setText(""+booking.guests+"");
        guests.setFont(new Font("AppleGothic Regular", 18));
        guests.setEditable(false);

        Button button = new Button();
        button.getStylesheets().add("hkrFX/css/style.css");
        button.setTextFill(Paint.valueOf("#000000e5"));
        button.setLayoutX(330.0);
        button.setStyle("-fx-background-radius: 9; -fx-background-color: transparent;");
        button.setLayoutY(323);
        //button.setText("Delete");
        button.setMnemonicParsing(false);
            //graphic
            Image image = new Image("hkrFX/img/garbage_32px.png");
            ImageView imageview = new ImageView(image);
            imageview.setPickOnBounds(true);
            imageview.setFitWidth(27.0);
            imageview.setFitHeight(23.0);
            imageview.setPreserveRatio(true);
            //graphic
        button.setGraphic(imageview);
        button.setOnAction(event -> {
            closeStage();
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

        button4 = new Button();
        button4.getStylesheets().add("hkrFX/css/style.css");
        button4.getStyleClass().add("backBook");
        button4.setTextFill(Paint.valueOf("GREY"));
        button4.setLayoutX(200);
        button4.setStyle("-fx-background-radius: 9; -fx-background-color: #ededed;");
        button4.setLayoutY(332.0);
        button4.setText("Back");
        button4.setMnemonicParsing(false);
        button4.setVisible(false);
        button4.setOnAction(event -> {
            //save edits
        });

        Button button3 = new Button();
        button3.setPrefHeight(50.0);
        button3.getStylesheets().add("hkrFX/css/style.css");
        button3.setPrefWidth(65.0);
        button3.setTextFill(Paint.valueOf("#000000e5"));
        button3.setLayoutX(330.0);
        button3.setStyle("-fx-background-radius: 9; -fx-background-color: transparent;");
        button3.setMnemonicParsing(false);

        //graphic
            Image image2 = new Image("hkrFX/img/edit_32px.png");
            ImageView imageview2 = new ImageView(image2);
            imageview2.setPickOnBounds(true);
            imageview2.setFitWidth(27.0);
            imageview2.setFitHeight(23.0);
            imageview2.setPreserveRatio(true);
            //graphic
            button.setGraphic(imageview2);
        button3.setOnAction(event -> {
            if(!movein.isEditable()){
                movein.setEditable(true);
                moveout.setEditable(true);
                guests.setEditable(true);
                room.setEditable(true);
            }
            else {
                movein.setEditable(false);
                moveout.setEditable(false);
                guests.setEditable(false);
                room.setEditable(false);
            }
        });

        Pane pane = new Pane();
        pane.setPrefHeight(379.0);
        pane.setPrefWidth(398.0);
        pane.setLayoutX(14.0);
        pane.setStyle("-fx-background-color: white;");
        pane.setLayoutY(14.0);

        pane.getChildren().addAll(button, button2, text, text2, text3, text4, text5, movein, moveout, button4, room, guests);

        paneMain = new Pane();
        paneMain.setPrefHeight(400.0);
        paneMain.setPrefWidth(427.0);
        paneMain.setStyle("-fx-background-color: white;");
        paneMain.getChildren().add(pane);

        Scene scene = new Scene(paneMain, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFX/css/style.css");
        this.setScene(scene);
    }
    private void closeStage(){
        MainFX.databaseManager.deleteBooking(booking.bId);
        session.books.remove(booking);
        session.loadButtons();
        new PopUP("Your order has been deleted!", "ffb053").show();
        session.borderpane.setRight(session.scrollPane);
    }
}
