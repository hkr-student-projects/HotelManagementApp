package se.hkr.fx;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.hkr.bookings.BookingDao;
import se.hkr.rooms.RoomDao;

public class BookingStage extends Stage {

    private TextField[] fields;
    private ComboBox<String> rooms;
    private DatePicker[] dates;
    private Button[] buttons;

    //Data access objects.
    private RoomDao roomDao;
    private BookingDao bookingDao;

    public BookingStage(RoomDao roomDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.bookingDao = bookingDao;
        this.setResizable(false);

        fields = createFields(
                new String[]{"Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006"},
                new double[]{236.0, 412.0, 236.0, 236.0, 236.0},
                new double[]{53.0, 53.0, 98.0, 143.0, 188.0}
        );

        rooms = new ComboBox<>();
        rooms.setLayoutX(238.0);
        rooms.setLayoutY(234.0);
        rooms.prefWidth(155.0);
        rooms.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 111;");

        dates = createDates(new double[]{325.0, 365.0});
        buttons = createButtons(new String[]{"Save", "Reset"}, new double[]{518.0, 236.0}, new double[]{60.0, 82.0});
        buttons[0].setOnAction(event -> closeStage());
        buttons[1].setOnAction(event -> emptyData());
        dates[0].setOnAction(event -> {
            rooms.setValue(null);
            if (dates[1].getValue() != null && dates[0].getValue() != null && (dates[1].getValue().compareTo(dates[0].getValue())) >= 0) {
                this.roomDao.getAvailableRooms(dates[0].getValue(), dates[1].getValue(), roomsData -> {
                    rooms.setItems(roomsData);
                });
            } else {
                rooms.setItems(null);
            }
        });
        dates[1].setOnAction(event -> {
            rooms.setValue(null);
            if (dates[0].getValue() != null && dates[1].getValue() != null && (dates[1].getValue().compareTo(dates[0].getValue())) >= 0) {
                this.roomDao.getAvailableRooms(dates[0].getValue(), dates[1].getValue(), roomsData -> {
                    rooms.setItems(roomsData);
                });
            } else {
                rooms.setItems(null);
            }
        });
        createScene();
    }

    private void createScene() {

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.prefHeight(FxApplication.SCENE_HEIGHT);
        anchorpane.prefWidth(FxApplication.SCENE_WIDTH);
        anchorpane.setStyle("-fx-background-color: white");

        Pane pane = new Pane();
        pane.setMinHeight(FxApplication.SCENE_HEIGHT);
        pane.setPrefWidth(FxApplication.SCENE_WIDTH / 3.0);
        pane.setStyle("-fx-background-color: #ffb053");

        ObservableList<Node> aChilds = anchorpane.getChildren();
        ObservableList<Node> pChilds = pane.getChildren();

        Text label = new Text();
        label.setLayoutX(238.0);
        label.setLayoutY(25.0);
        label.prefHeight(17.0);
        label.prefWidth(125.0);
        label.setText("Booking details");
        label.setFill(Paint.valueOf("#ffb053"));
        label.setFont(new Font("Futura Medium", 14));

        pChilds.addAll(createTexts(
                new String[]{"Name", "SSN", "Phone", "Address", "Available Rooms", "", "Arrival Date", "Departure Date"},
                new double[]{71.0, 116.0, 161.0, 206.0, 253.0, 298.0, 343.0, 388.0},
                new double[]{68.21875, 68.21875, 68.21875, 68.21875, 94.21875, 94.21875, 94.21875, 118.21875}
        ));

        aChilds.add(pane);
        aChilds.addAll(fields);
        aChilds.add(rooms);
        aChilds.addAll(buttons);
        aChilds.addAll(dates);
        aChilds.add(label);

        Scene scene = new Scene(anchorpane, FxApplication.SCENE_WIDTH, FxApplication.SCENE_HEIGHT);
        scene.getStylesheets().add("General.css");
        this.setScene(scene);
    }


    private Text[] createTexts(String[] texts, double[] layoutYs, double[] wrappingWidths) {
        if (texts.length != layoutYs.length || layoutYs.length != wrappingWidths.length) {
            System.out.println("Incorrect arguments lengths in BookingStage.createTexts");

            return null;
        }
        Text[] txts = new Text[texts.length];

        for (byte i = 0; i < txts.length; i++) {
            txts[i] = new Text();
            txts[i].setFill(Paint.valueOf("#ffffff"));
            txts[i].setLayoutX(40.0);
            txts[i].setLayoutY(layoutYs[i]);
            txts[i].setStrokeType(StrokeType.OUTSIDE);
            txts[i].setStrokeWidth(0.0);
            txts[i].setStyle("-fx-font-weight: bold");
            txts[i].setText(texts[i]);
            txts[i].setWrappingWidth(wrappingWidths[i]);
            txts[i].setFont(new Font("Futura Medium", 16));
        }

        return txts;
    }

    private TextField[] createFields(String[] promts, double[] layoutXs, double[] layoutYs) {

        if (promts.length != layoutXs.length || layoutXs.length != layoutYs.length) {
            System.out.println("Incorrect arguments lengths in BookingStage.createFields");

            return null;
        }
        TextField[] fields = new TextField[promts.length];

        for (byte i = 0; i < fields.length; i++) {
            fields[i] = new TextField();
            fields[i].setLayoutX(layoutXs[i]);
            fields[i].setLayoutY(layoutYs[i]);
            fields[i].setPrefHeight(28.0);
            fields[i].setPrefWidth(155.0);
            fields[i].setPromptText(promts[i]);
            fields[i].setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 111;");
        }

        return fields;
    }

    private DatePicker[] createDates(double[] layoutYs) {

        DatePicker[] pickers = new DatePicker[layoutYs.length];
        for (byte i = 0; i < pickers.length; i++) {
            pickers[i] = new DatePicker();
            pickers[i].setLayoutX(238.0);
            pickers[i].setLayoutY(layoutYs[i]);
            pickers[i].maxWidth(155.0);
            pickers[i].setOpacity(0.9);
        }

        return pickers;
    }

    private Button[] createButtons(String[] text, double[] layoutXs, double[] prefWidths) {

        if (text.length != layoutXs.length || layoutXs.length != prefWidths.length) {
            System.out.println("Incorrect arguments lengths in BookingStage.createButtons");

            return null;
        }

        Button[] buttons = new Button[layoutXs.length];
        for (byte i = 0; i < buttons.length; i++) {
            buttons[i] = new Button();
            buttons[i].setText(text[i]);
            buttons[i].setLayoutX(layoutXs[i]);
            buttons[i].setLayoutY(400.0);
            buttons[i].setMnemonicParsing(false);
            buttons[i].setPrefHeight(27.0);
            buttons[i].setPrefWidth(prefWidths[i]);
            buttons[i].setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 8");
            buttons[i].setFont(new Font(12));
        }
        buttons[0].setOnAction(event -> closeStage());

        return buttons;
    }

    private void redOutField(TextField field) {
        field.setStyle("-fx-control-inner-background: #ff4c4c; -fx-background-radius: 111;");
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused)
                field.setStyle("-fx-background-radius: 111;");
        });
    }

    private void emptyData() {
        rooms.setValue(null);
        for (TextField t : fields) {
            t.setText(null);
        }
        for (DatePicker d : dates) {
            d.setValue(null);
        }
    }

    private void closeStage() {

        // "Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006"
        boolean errors = false;
        String name = null;
        String sname = null;
        String ssn = null;
        String phone = null;
        String addr = null;
        for (TextField field : fields) {
            switch (field.getPromptText().toLowerCase()) {

                case "name":
                    name = field.getText();
                case "surname":
                    sname = field.getText();
                    if (!field.getText().matches("[A-Z][a-z]*")) {
                        redOutField(field);
                        errors = true;
                    }
                    break;

                case "19890518-4376":
                    ssn = field.getText();
                    if (!field.getText().matches("\\d{8}\\-\\d{4}")) {
                        redOutField(field);
                        errors = true;
                    }
                    break;

                case "+073-751-06-21":
                    phone = field.getText();
                    if (!field.getText().matches("\\+\\d{3}\\-\\d{3}\\-\\d{2}\\-\\d{2}")) {
                        redOutField(field);
                        errors = true;
                    }
                    break;

                case "storagatan 12a-1006":
                    addr = field.getText();
                    if (!field.getText().matches("[A-Z][a-z]+\\s\\d+[A-Z]?\\-\\d+")) {
                        redOutField(field);
                        errors = true;
                    }
            }
        }
        if (!errors && rooms != null) {
            this.bookingDao.addEntry(ssn, name, sname, addr, phone, this.dates[0].getValue(), this.dates[1].getValue(), this.rooms.getValue());
            this.close();
            emptyData();
        }
    }
}
