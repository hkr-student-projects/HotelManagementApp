package hkrFX;

import hkrDB.DatabaseManager;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PersonalAreaCus extends Stage {

    private DatabaseManager.Profile user;
    protected ArrayList<DatabaseManager.Booking> books;
    protected BorderPane borderpane;
    protected ScrollPane scrollPane;
    private AnchorPane anchorPane;
    private GridPane gridPane;
    private VBox menu;
    private Text initials;
    private Label fullName;
    private Button bookings;
    //private Button profile;
    private Button signout;
    private double HSize;

    public PersonalAreaCus(DatabaseManager.Profile profile){

        this.setResizable(false);
        user = profile;
        books = MainFX.databaseManager.getBookings(profile.cId);
        createScene();
    }
    
//    protected void refreshBooks(){
//        int existingRows = gridPane.getRowConstraints().size();
//        gridPane.getRowConstraints().addAll(defRowCons(existingRows));
//    }

    protected void showUpdateBookings(){

        books = MainFX.databaseManager.getBookings(user.cId);
        HSize = HSize + (((int)Math.ceil(books.size() + 1) / 3.0) - 3) * 111;//"+ 1" for extra button for adding booking
        gridPane.getRowConstraints().addAll(defRowCons());
        loadButtons();
        anchorPane.setPrefHeight(HSize);
        scrollPane.setPrefHeight(HSize);
    }

    private void loadButtons(){
        Text[] bookButtons = bookingButtons();
        int rowCount = (int)(Math.ceil(books.size()) / 3.0);
        for(int r = 0, n = 0; r < rowCount; r++){
            for(int c = 0; c < 3 && n < bookButtons.length; c++, n++){
                gridPane.add(bookButtons[n], c, r);
            }
        }
        int index = 3 - (rowCount * 3 - books.size());
        Text plus = createBookButton("+");
        plus.setOnMouseClicked(event -> {
            borderpane.setRight(new AddBooking(this, user).pane);
        });
        System.out.println(books.size());
        System.out.println(rowCount);
        System.out.println(index);
        if(rowCount == 0){
            gridPane.add(plus, 0, 0);
            return;
        }
        gridPane.add(plus, index == 3 ? 0 : index, index == 3 ? rowCount + 1 : rowCount);
    }

    private Text[] bookingButtons(){
        Text[] buttons = new Text[books.size()];
        for(int i = 0; i < buttons.length; i++){
            DatabaseManager.Booking book = books.get(i);
            buttons[i] = createBookButton(book.movein.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "-" + book.moveout.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            buttons[i].setOnMouseClicked(event -> {
                //new BookingInfo(book, this).show();
                borderpane.setRight(new BookingInfo(book, this).paneMain);
            });
        }

        return buttons;
    }

    protected void insertInGrid(){

    }

    protected Text createBookButton(String text){
        Text button = new Text();
//        button.setPrefHeight(22.0);
//        button.setPrefWidth(22.0);
//        button.maxWidth(22.0);
//        button.minHeight(22.0);
//        button.minWidth(22.0);
//        button.maxHeight(22.0);
        button.getStyleClass().add("gridAdd");
        //button.setMnemonicParsing(false);
        //button.getStylesheets().add("hkrFX/css/style.css");
        button.setTextAlignment(TextAlignment.CENTER);
        //button.setTextFill(Paint.valueOf("#909090"));
        button.setStyle("-fx-background-color: transparent;");
        //button.setContentDisplay(ContentDisplay.CENTER);
        button.setWrappingWidth(83);
        button.setText(text);
        button.setFont(text == "+" ? new Font("Apple SD Gothic Neo Regular", 25) : new Font("Avenir Book",15));

        return button;
    }

    private ColumnConstraints defColCon(){
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.NEVER);
        cc.setFillWidth(false);
        cc.setPrefWidth(111);
        cc.setMinWidth(111);
        cc.setMaxWidth(111);
        cc.setHalignment(HPos.CENTER);

        return cc;
    }

    private RowConstraints[] defRowCons(){
        RowConstraints[] rcs = new RowConstraints[((int)(Math.ceil(books.size() + 1) / 3.0))];
        for(int i = 0; i < rcs.length; i++){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(false);
            rc.setMinHeight(111);
            rc.setMaxHeight(111);
            rc.setPrefHeight(111);
            rc.setValignment(VPos.CENTER);
            rc.setVgrow(Priority.NEVER);
            rcs[i] = rc;
        }

        return rcs;
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
                initials.setText(user.name.substring(0, 1) + user.surname.substring(0, 1));
                initials.setFill(Paint.valueOf("#ffb053"));
                initials.setWrappingWidth(57.21875);
                initials.setFont(new Font(38.0));
                //pane childs
            pane.getChildren().addAll(circle, initials);

            fullName = new Label();
            fullName.setTextFill(Paint.valueOf("#ffffff"));
            fullName.setText(user.name + " " + user.surname);
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
            bookings.setOnAction(event -> {
                showUpdateBookings();
                borderpane.setRight(scrollPane);
            });

//            profile = new Button();
//            profile.setPrefHeight(42.0);
//            profile.getStylesheets().add("hkrFX/css/style.css");
//            profile.setGraphicTextGap(22.0);
//            profile.setPrefWidth(259.0);
//            profile.setText("Profile");
//            profile.setAlignment(Pos.BASELINE_LEFT);
//            profile.getStyleClass().add("sideButton");
//            profile.setMnemonicParsing(false);
//            //graphic
//                Image image2 = new Image("hkrFX/img/icons8_Person_32px.png");
//                ImageView imageview2 = new ImageView(image2);
//                imageview2.setPickOnBounds(true);
//                imageview2.setFitWidth(27.0);
//                imageview2.setFitHeight(23.0);
//                imageview2.setPreserveRatio(true);
//            //graphic
//            profile.setGraphic(imageview2);
//            profile.setPadding(new Insets(0, 0, 0, 20));

            HSize = 400.0 + (((int)Math.ceil(books.size() + 1) / 3.0) - 3) * 111;

            signout = new Button();
            signout.setPrefHeight(42.0);
            signout.getStylesheets().add("hkrFX/css/style.css");
            signout.setGraphicTextGap(22.0);
            signout.setPrefWidth(259.0);
            signout.setText("Sign out");
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
            signout.setOnAction(event -> {
                this.close();
            });
        //menu childs

        gridPane = new GridPane();
        gridPane.setPrefHeight(333.0);
        gridPane.setPrefWidth(333.0);
        gridPane.setLayoutX(46.0);
        gridPane.setLayoutY(33.0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getColumnConstraints().addAll(defColCon(), defColCon(), defColCon());
        //gridPane.getRowConstraints().addAll(defRowCons(0));

        anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(410.0);
        anchorPane.getChildren().add(gridPane);

        scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(HSize);
        scrollPane.setPrefWidth(428.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(anchorPane);

        menu.getChildren().addAll(pane, fullName, bookings, signout);
        VBox.setMargin(pane, new Insets(25, 0, 0 ,0));
        VBox.setMargin(fullName, new Insets(0, 0, 75 ,0));
        BorderPane.setAlignment(menu, Pos.CENTER);
        borderpane.setLeft(menu);

        Scene scene = new Scene(borderpane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        //scene.getStylesheets().add("hkrFX/css/style.css");
        this.setScene(scene);

        //this.show();
    }
}
