package se.hkr.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import se.hkr.bookings.Booking;
import se.hkr.bookings.BookingDao;
import se.hkr.customer.Profile;

import java.util.List;

public class PersonalAreaCus extends Stage {

    private Profile user;
    private List<Booking> books;
    private BorderPane borderpane;
    private VBox menu;
    private Text initials;
    private Label fullName;
    private Button bookings;
    private Button profile;
    private Button signout;

    private final BookingDao bookingDao;

    public PersonalAreaCus(Profile profile, BookingDao bookingDao){
        this.user = profile;
        this.bookingDao = bookingDao;
        this.bookingDao.getBookings(profile.getcId(), bookings1 -> {
            this.books = bookings1;
            createScene();
        });
    }

    private void createScene(){

        borderpane = new BorderPane();
        borderpane.setPrefHeight(400.0);
        borderpane.setPrefWidth(600.0);

        menu = new VBox();
        menu.setPrefHeight(400.0);
        menu.setPrefWidth(173.0);
        menu.setStyle("-fx-background-color: #ffb053;");
        menu.setAlignment(Pos.TOP_CENTER);

        //menu childs
        Pane pane = new Pane();
        pane.setPrefHeight(107.0);
        pane.setPrefWidth(242.0);

        //pane childs
        Circle circle = new Circle();
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setLayoutX(87.0);
        circle.setLayoutY(54.0);
        circle.setFill(Paint.valueOf("#ffffff"));
        circle.setRadius(39.0);
        circle.setStroke(Paint.valueOf("transparent"));

        initials = new Text();
        initials.setStrokeWidth(0.0);
        initials.setStrokeType(StrokeType.OUTSIDE);
        initials.setTextAlignment(TextAlignment.CENTER);
        initials.setLayoutX(58.0);
        initials.setLayoutY(68.0);
        initials.setText(user.getName().substring(0, 1) + user.getSurname().substring(0, 1));
        initials.setFill(Paint.valueOf("#ffb053"));
        initials.setWrappingWidth(57.21875);
        initials.setFont(new Font(38.0));
        //pane childs
        pane.getChildren().addAll(circle, initials);

        fullName = new Label();
        fullName.setTextFill(Paint.valueOf("#ffffff"));
        fullName.setText(user.getName() + " " + user.getSurname());
        fullName.setFont(new Font("System Bold", 15));

        bookings = new Button();
        bookings.setPrefHeight(42.0);
        bookings.getStylesheets().add("hkrFX/css/style.css");
        bookings.setGraphicTextGap(22.0);
        bookings.setPrefWidth(259.0);
        bookings.setText("Bookings");
        bookings.setAlignment(Pos.BASELINE_LEFT);
        bookings.getStyleClass().add("sideButton");
        bookings.setMnemonicParsing(false);
        //graphic
        Image image = new Image("hkrFX/img/icons8_Xbox_Menu_32px.png");
        ImageView imageview = new ImageView(image);
        imageview.setPickOnBounds(true);
        imageview.setFitWidth(27.0);
        imageview.setFitHeight(23.0);
        imageview.setPreserveRatio(true);
        //graphic
        bookings.setGraphic(imageview);
        bookings.setPadding(new Insets(0, 0, 0, 20));

        profile = new Button();
        profile.setPrefHeight(42.0);
        profile.getStylesheets().add("hkrFX/css/style.css");
        profile.setGraphicTextGap(22.0);
        profile.setPrefWidth(259.0);
        profile.setText("Profile");
        profile.setAlignment(Pos.BASELINE_LEFT);
        profile.getStyleClass().add("sideButton");
        profile.setMnemonicParsing(false);
        //graphic
        Image image2 = new Image("hkrFX/img/icons8_Person_32px.png");
        ImageView imageview2 = new ImageView(image2);
        imageview2.setPickOnBounds(true);
        imageview2.setFitWidth(27.0);
        imageview2.setFitHeight(23.0);
        imageview2.setPreserveRatio(true);
        //graphic
        profile.setGraphic(imageview2);
        profile.setPadding(new Insets(0, 0, 0, 20));

        signout = new Button();
        signout.setPrefHeight(42.0);
        signout.getStylesheets().add("hkrFX/css/style.css");
        signout.setGraphicTextGap(22.0);
        signout.setPrefWidth(259.0);
        signout.setText("Profile");
        signout.setAlignment(Pos.BASELINE_LEFT);
        signout.getStyleClass().add("sideButton");
        signout.setMnemonicParsing(false);
        //graphic
        Image image3 = new Image("hkrFX/img/icons8_Sign_Out_32px.png");
        ImageView imageview3 = new ImageView(image3);
        imageview3.setPickOnBounds(true);
        imageview3.setFitWidth(27.0);
        imageview3.setFitHeight(23.0);
        imageview3.setPreserveRatio(true);
        //graphic
        signout.setGraphic(imageview3);
        signout.setPadding(new Insets(0, 0, 0, 20));
        //menu childs

        menu.getChildren().addAll(pane, fullName, bookings, profile, signout);
        VBox.setMargin(pane, new Insets(25, 0, 0 ,0));
        VBox.setMargin(fullName, new Insets(0, 0, 75 ,0));
        BorderPane.setAlignment(menu, Pos.CENTER);
        borderpane.setLeft(menu);

        Scene scene = new Scene(borderpane, FxApplication.SCENE_WIDTH, FxApplication.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFX/css/style.css");
        this.setScene(scene);
    }
}