package hkrFX;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class BookingStage extends Stage{

//    private AnchorPane _anchorpane;
//    private Pane _pane;
//    private Label _label;
    private Text[] _texts;
    private TextField[] fields;
    private ComboBox[] _boxes;
    private DatePicker[] _dates;
    private Button[] buttons;

    public BookingStage(){

        this.setResizable(false);
//        _anchorpane = new AnchorPane();
//        _anchorpane.prefHeight(MainFX.SCENE_HEIGHT);
//        _anchorpane.prefWidth(MainFX.SCENE_WIDTH);
//        _anchorpane.setStyle("-fx-background-color: white");

//        _pane = new Pane();
//        _pane.setMinHeight(MainFX.SCENE_HEIGHT );
//        _pane.prefWidth(220);
//        _pane.setStyle("-fx-background-color: #ffb053");

//        _label = new Label();
//        _label.setLayoutX(238.0);
//        _label.setLayoutY(14.0);
//        _label.prefHeight(17.0);
//        _label.prefWidth(125.0);
//        _label.setText("Booking details");
//        _label.setTextFill(Paint.valueOf("#ffb053"));
//        _label.setFont(new Font("Futura Medium", 13));

//        _texts = createTexts(
//                new String[] { "Name", "SSN", "Phone", "Address", "Room Type", "Guests", "Arrival Date", "Departure Date" },
//                new double[] { 71.0, 116.0, 161.0, 206.0, 253.0, 298.0, 343.0, 388.0 },
//                new double[] { 68.21875, 68.21875, 68.21875, 68.21875, 94.21875, 94.21875, 94.21875, 118.21875}
//        );

        fields = createFields(
                new String[] { "Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006" },
                new double[] { 236.0, 412.0, 236.0, 236.0, 236.0 },
                new double[] { 53.0, 53.0, 98.0, 143.0, 188.0 }
        );

        _boxes = createBoxes(new double[] { 234.0, 274.0 });
        _dates = createDates(new double[] { 325.0, 365.0 });
        buttons = createButtons(new String[] { "Save", "Reset" }, new double[] { 518.0, 236.0 }, new double[] { 60.0, 82.0 });
        
        createScene();
    }

    private void createScene(){

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.prefHeight(MainFX.SCENE_HEIGHT);
        anchorpane.prefWidth(MainFX.SCENE_WIDTH);
        anchorpane.setStyle("-fx-background-color: white");

        Pane pane = new Pane();
        pane.setMinHeight(MainFX.SCENE_HEIGHT );
        pane.setPrefWidth(MainFX.SCENE_WIDTH / 3.0);
        pane.setStyle("-fx-background-color: #ffb053");

        ObservableList<Node> aChilds = anchorpane.getChildren();
        ObservableList<Node> pChilds = pane.getChildren();

//        Button b = new Button();//temporary
//        b.setLayoutY(-14);
//        b.setLayoutX(40);
//        b.setMnemonicParsing(false);
//        b.prefHeight(42);
//        b.prefWidth(45);
//        b.setStyle("-fx-background-color: transparent;");
//        b.setText("⬅");
//        //b.setText("-");
//        b.setTextFill(Paint.valueOf("WHITE"));
//        b.setFont(new Font("Arial Rounded MT Bold", 40));
//        b.setOnAction(event -> this.close());

        Text label = new Text();
        label.setLayoutX(238.0);
        label.setLayoutY(25.0);
        label.prefHeight(17.0);
        label.prefWidth(125.0);
        label.setText("Booking details");
        label.setFill(Paint.valueOf("#ffb053"));
        label.setFont(new Font("Futura Medium", 14));

        pChilds.addAll(createTexts(
                new String[] { "Name", "SSN", "Phone", "Address", "Room Type", "Guests", "Arrival Date", "Departure Date" },
                new double[] { 71.0, 116.0, 161.0, 206.0, 253.0, 298.0, 343.0, 388.0 },
                new double[] { 68.21875, 68.21875, 68.21875, 68.21875, 94.21875, 94.21875, 94.21875, 118.21875}
        ));

        //pChilds.add(b);
        aChilds.add(pane);
        aChilds.addAll(fields);
        aChilds.addAll(_boxes);
        aChilds.addAll(buttons);
        aChilds.addAll(_dates);
        aChilds.add(label);

        //this.setScene(new Scene(_anchorpane, Main.SCENE_WIDTH, Main.SCENE_HEIGHT));
        Scene scene = new Scene(anchorpane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFx/General.css");
        this.setScene(scene);

        //this.show();
    }


    private Text[] createTexts(String[] texts, double[] layoutYs, double[] wrappingWidths){

        if(texts.length != layoutYs.length || layoutYs.length != wrappingWidths.length){
            System.out.println("Incorrect arguments lengths in BookingStage.createTexts");

            return null;
        }
        Text[] txts = new Text[texts.length];

        for (byte i = 0; i < txts.length; i++){
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

    private TextField[] createFields(String[] promts, double[] layoutXs, double[] layoutYs){

        if(promts.length != layoutXs.length || layoutXs.length != layoutYs.length){
            System.out.println("Incorrect arguments lengths in BookingStage.createFields");

            return null;
        }
        TextField[] fields = new TextField[promts.length];

        for (byte i = 0; i < fields.length; i++){
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

    private ComboBox[] createBoxes(double[] layoutYs){

        ComboBox[] boxes = new ComboBox[layoutYs.length];
        for (byte i = 0; i < boxes.length; i++){
            boxes[i] = new ComboBox();
            boxes[i].setLayoutX(238.0);
            boxes[i].setLayoutY(layoutYs[i]);
            boxes[i].prefWidth(155.0);

            boxes[i].setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 111;");
        }

        return boxes;
    }

    private DatePicker[] createDates(double[] layoutYs){

        DatePicker[] pickers = new DatePicker[layoutYs.length];
        for (byte i = 0; i < pickers.length; i++){
            pickers[i] = new DatePicker();
            pickers[i].setLayoutX(238.0);
            pickers[i].setLayoutY(layoutYs[i]);
            pickers[i].maxWidth(155.0);
            pickers[i].setOpacity(0.9);
        }

        return pickers;
    }

    private Button[] createButtons(String[] text, double[] layoutXs, double[] prefWidths){

        if(text.length != layoutXs.length || layoutXs.length != prefWidths.length){
            System.out.println("Incorrect arguments lengths in BookingStage.createButtons");

            return null;
        }

        Button[] buttons = new Button[layoutXs.length];
        for (byte i = 0; i < buttons.length; i++){
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

    public Button getButton(String name){
        for (Button b : buttons){
            if(MainFX.equals(b.getText().toLowerCase().toCharArray(), name.toLowerCase().toCharArray()))
                return b;
        }


        System.out.println("Unable to find button: "+ name +" in "+ this.getClass().getName() +"");
        return null;
    }

    private void redOutField(TextField field){
        field.setStyle("-fx-control-inner-background: #ff4c4c; -fx-background-radius: 111;");
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused)
                field.setStyle("-fx-background-radius: 111;");
        });
    }

    private void closeStage(){

        // "Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006"
        boolean errors = false;
        for(TextField field : fields) {
            switch (field.getPromptText().toLowerCase()) {

                case "name":
                case "surname":
                    if (!field.getText().matches("[A-Z][a-z]*")) {
                        redOutField(field);
                        errors = true;
                    }
                break;

                case "19890518-4376":
                    if (!field.getText().matches("\\d{8}\\-\\d{4}")) {
                        redOutField(field);
                        errors = true;
                    }
                break;

                case "+073-751-06-21":
                    if (!field.getText().matches("\\+\\d{3}\\-\\d{3}\\-\\d{2}\\-\\d{2}")) {
                        redOutField(field);
                        errors = true;
                    }
                break;

                case "storagatan 12a-1006":
                    if (!field.getText().matches("[A-Z][a-z]+\\s\\d+[A-Z]?\\-\\d+")) {
                        redOutField(field);
                        errors = true;
                    }
            }
        }
        if(!errors)
            this.close();
    }
}
