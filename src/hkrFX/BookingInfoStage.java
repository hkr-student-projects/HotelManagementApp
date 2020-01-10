package hkrFX;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class BookingInfoStage extends Stage {

    private BookingStage bookingStage;
    private Pane _pane1;
    private Pane _pane2;
    private Button _buttonPlus;
    private TextField _fieldSearch;

    public BookingInfoStage(){

        this.setResizable(false);
        bookingStage = new BookingStage();

        _pane1 = new Pane();
        _pane1.setPrefHeight(MainFX.SCENE_HEIGHT >> 1);
        _pane1.setPrefWidth(MainFX.SCENE_WIDTH);
        _pane1.setStyle("-fx-background-color: #ffb053");

        _buttonPlus = new Button();
        _buttonPlus.setLayoutX(542);
        _buttonPlus.setMnemonicParsing(false);
        _buttonPlus.setPrefSize(40 ,49);
        _buttonPlus.setStyle("-fx-background-color: transparent");
        _buttonPlus.setText("+");
        _buttonPlus.setTextFill(Paint.valueOf("#ffffff"));
        _buttonPlus.setFont(new Font("Arial Rounded MT Bold", 30));
        _buttonPlus.setOnAction(event -> bookingStage.show());

        _fieldSearch = new TextField();
        _fieldSearch.setLayoutX(217);
        _fieldSearch.setLayoutY(100);
        _fieldSearch.setPromptText("Booking reference");
        _fieldSearch.setStyle("-fx-background-color: white; -fx-background-radius: 0;");

        Text text = new Text();
        text.setFill(Paint.valueOf("#ffffff"));
        text.setLayoutX(168);
        text.setLayoutY(74);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(0);
        text.setText("Search booking");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(263.21875);
        text.setFont(new Font("Futura Medium", 31));

        _pane2 = new Pane();
        _pane2.setLayoutY(MainFX.SCENE_HEIGHT >> 1);
        _pane2.setPrefHeight(MainFX.SCENE_HEIGHT >> 1);
        _pane2.setPrefWidth(MainFX.SCENE_WIDTH);
        _pane2.setStyle("-fx-background-color: white");

        _pane1.getChildren().addAll(_buttonPlus, _fieldSearch, text);

        createStage();
    }

    private void createStage(){

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(MainFX.SCENE_HEIGHT);
        anchorPane.setPrefWidth(MainFX.SCENE_WIDTH);
        showBooking("");
        anchorPane.getChildren().addAll(_pane1, _pane2);
        Scene scene = new Scene(anchorPane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFx/General.css");

        this.setScene(scene);
    }

    private void showBooking(String refNumber){//bool (_pane2)
        //DB search result

        //if false..
        //_pane2.setVisible(false);
        //return;

        //if true..

        Text bookingInfo = new Text();
        bookingInfo.setLayoutX(168);
        bookingInfo.setLayoutY(39);
        bookingInfo.setStrokeType(StrokeType.OUTSIDE);
        bookingInfo.setStrokeWidth(0.0);
        bookingInfo.setText("Booking #DA001");
        bookingInfo.setTextAlignment(TextAlignment.CENTER);
        bookingInfo.setWrappingWidth(263.21875);
        bookingInfo.setFont(new Font("Futura Medium", 24));

        Button buttonRem = new Button();
        buttonRem.setLayoutX(540.0);
        buttonRem.setLayoutY(-8);
        buttonRem.setMnemonicParsing(false);
        buttonRem.setPrefHeight(40);
        buttonRem.setPrefWidth(49);
        buttonRem.setStyle("-fx-background-color: transparent");
        buttonRem.setText("-");
        buttonRem.setTextFill(Paint.valueOf("#ffb053"));
        buttonRem.setFont(new Font("Arial Rounded MT Bold", 40));

        ObservableList<Node> pChilds = _pane2.getChildren();
        pChilds.addAll(createTexts(
                new String[] { "Move In", "Move out", "Room", "Guests", "23 May 2019", "06 June 2019", "4", "Double" },
                new double[] { 56, 56, 351, 351, 151, 151, 416, 416 },
                new double[] { 85, 126, 86, 125, 87, 128, 127, 87  }
        ));
        pChilds.addAll(buttonRem);
        pChilds.addAll(bookingInfo);

        //return _pane2;
    }

    private Text[] createTexts(String[] texts, double[] layoutXs, double[] layoutYs){

        if(texts.length != layoutYs.length || layoutYs.length != layoutXs.length){
            System.out.println("Incorrect arguments lengths in BookingInfoStage.createTexts");

            return null;
        }
        Text[] txts = new Text[texts.length];

        for (byte i = 0; i < txts.length; i++){
            txts[i] = new Text();
            txts[i].setLayoutX(layoutXs[i]);
            txts[i].setLayoutY(layoutYs[i]);
            txts[i].setStrokeType(StrokeType.OUTSIDE);
            txts[i].setStrokeWidth(0.0);
            txts[i].setText(texts[i]);
            txts[i].setFont(new Font("AppleGothic Regular", 18));
        }

        return txts;
    }

    public TextField getSearchField(){
        return _fieldSearch;
    }

    public Button getButtonPlus(){
        return _buttonPlus;
    }

    public Pane getSearchPane(){
        return _pane1;
    }

    public Pane getResultPane(){
        return _pane2;
    }
}
