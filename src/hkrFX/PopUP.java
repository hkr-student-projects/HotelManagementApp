package hkrFX;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUP extends Stage {

    public PopUP(String message, String colorCode){
        Text text = new Text();
        text.setStrokeWidth(0.0);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setLayoutX(29.0);
        text.setLayoutY(54.0);
        text.setText(message);
        text.setFill(Paint.valueOf("WHITE"));
        text.setWrappingWidth(223.47265625);
        text.setFont(new Font("AppleGothic Regular", 15));
        Button button = new Button();
        button.setPrefHeight(114.0);
        button.setPrefWidth(282.0);
        button.setTextFill(Paint.valueOf("WHITE"));
        button.setStyle("-fx-background-color: transparent;");
        button.setLayoutY(1.0);
        button.setMnemonicParsing(false);
        button.setOnAction(event -> {
            this.close();
        });

        Pane pane = new Pane();
        pane.setPrefHeight(114.0);
        pane.setPrefWidth(282.0);
        pane.setStyle("-fx-background-color: #"+colorCode+";");

        pane.getChildren().addAll(text, button);
        this.setScene(new Scene(pane, 282, 114));
        this.initStyle(StageStyle.UNDECORATED);
    }
}
