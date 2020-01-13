package se.hkr.fx;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import se.hkr.bookings.Booking;
import se.hkr.bookings.BookingDao;
import se.hkr.rooms.RoomDao;
import se.hkr.user.Profile;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonalAreaCus extends Stage {

    private Profile user;
    private List<Booking> books;
    private BorderPane borderpane;
    private ScrollPane scrollPane;
    private AnchorPane anchorPane;
    private GridPane gridPane;
    private VBox menu;
    private Text initials;
    private Label fullName;
    private Button bookings;
    private Button signout;
    private double HSize;

    private final BookingDao bookingDao;
    private final RoomDao roomDao;

    public PersonalAreaCus(Profile profile, BookingDao bookingDao, RoomDao roomDao) {
        this.setResizable(false);
        this.user = profile;
        this.bookingDao = bookingDao;
        this.roomDao = roomDao;
        this.bookingDao.getBookings(profile.getcId(), bookings1 -> {
            this.books = bookings1;
            createScene();
        });
    }

    protected void showUpdateBookings() {

        HSize = 400.0 + (((int) Math.ceil(books.size() + 1) / 3.0) - 3) * 111;//"+ 1" for extra button for adding booking
        gridPane.getRowConstraints().addAll(defRowCons(gridPane.getRowConstraints().size()));
        loadButtons();
        anchorPane.setPrefHeight(HSize);
        scrollPane.setPrefHeight(HSize);
    }

    protected void loadButtons() {
        Text[] bookButtons = bookingButtons();
        int rowCount = (int) (Math.ceil(books.size() / 3.0));
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getRowConstraints().addAll(defRowCons(rowCount));
        for (int r = 0, n = 0; r < rowCount; r++) {
            for (int c = 0; c < 3 && n < bookButtons.length; c++, n++) {
                gridPane.add(bookButtons[n], c, r);
            }
        }
        int index = 3 - (rowCount * 3 - (books.size()));
        Text plus = createBookButton("+");
        plus.setOnMouseClicked(event -> {
            borderpane.setRight(new AddBooking(this, user,  this.roomDao, this.bookingDao).pane);
        });
        gridPane.add(plus, index == 3 ? 0 : index, index == 3 ? rowCount : rowCount - 1);
    }

    private Text[] bookingButtons() {
        Text[] buttons = new Text[books.size()];
        for (int i = 0; i < buttons.length; i++) {
            Booking book = books.get(i);
            buttons[i] = createBookButton(book.getMovein().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "-" + book.getMoveout().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            buttons[i].setOnMouseClicked(event -> {
                //new BookingInfo(book, this).show();
                borderpane.setRight(new BookingInfo(book, this, this.bookingDao).paneMain);
            });
        }

        return buttons;
    }

    public List<Booking> getBooks() {
        return books;
    }

    public void setBooks(List<Booking> books) {
        this.books = books;
    }

    protected Text createBookButton(String text) {
        Text button = new Text();
        button.getStyleClass().add("gridAdd");
        button.setTextAlignment(TextAlignment.CENTER);
        button.setStyle("-fx-background-color: transparent;");
        button.setWrappingWidth(83);
        button.setText(text);
        button.setFont(text == "+" ? new Font("Apple SD Gothic Neo Regular", 25) : new Font("Avenir Book", 15));

        return button;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    private ColumnConstraints defColCon() {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.NEVER);
        cc.setFillWidth(false);
        cc.setPrefWidth(111);
        cc.setMinWidth(111);
        cc.setMaxWidth(111);
        cc.setHalignment(HPos.CENTER);

        return cc;
    }

    private RowConstraints[] defRowCons(int rows) {
        RowConstraints[] rcs = new RowConstraints[rows];
        for (int i = 0; i < rcs.length; i++) {
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

    private void createScene() {

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

        anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(410.0);
        anchorPane.getChildren().add(gridPane);

        scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(HSize);
        scrollPane.setPrefWidth(428.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(anchorPane);

        menu.getChildren().addAll(pane, fullName, bookings, signout);
        VBox.setMargin(pane, new Insets(25, 0, 0, 0));
        VBox.setMargin(fullName, new Insets(0, 0, 75, 0));
        BorderPane.setAlignment(menu, Pos.CENTER);
        borderpane.setLeft(menu);

        Scene scene = new Scene(borderpane, FxApplication.SCENE_WIDTH, FxApplication.SCENE_HEIGHT);
        //scene.getStylesheets().add("hkrFX/css/style.css");
        this.setScene(scene);

        //this.show();
    }
}