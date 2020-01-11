package hkrFX;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginEmp extends Stage {

    private TextField email;
    private PasswordField password;
    private Button log;
    private Button create;
    private Button forgot;
    private Label error;

    public LoginEmp(){
        this.setResizable(false);

        email = new TextField();
        email.setLayoutX(198.0);
        email.setStyle("-fx-background-color: white; -fx-background-radius: 0;");
        email.setLayoutY(166.0);
        email.setAlignment(Pos.CENTER);
        email.setPromptText("email");

        password = new PasswordField();
        password.setLayoutX(198.0);
        password.setStyle("-fx-background-color: white; -fx-background-radius: 0;");
        password.setLayoutY(166.0);
        password.setAlignment(Pos.CENTER);
        password.setPromptText("email");

        log = new Button();
        log.setPrefHeight(23.0);
        log.setPrefWidth(53.0);
        log.setTextFill(Paint.valueOf("#3c3c3c"));
        log.setLayoutX(255.0);
        log.setStyle("-fx-background-color: white; -fx-background-radius: 8;");
        log.setLayoutY(242.0);
        log.setText("Log");
        log.setMnemonicParsing(false);
        log.setFont(new Font(12.0));

        error = new Label();
        error.setPrefHeight(17.0);
        error.setPrefWidth(167.0);
        error.setTextAlignment(TextAlignment.CENTER);
        error.setTextFill(Paint.valueOf("RED"));
        error.setLayoutX(198.0);
        error.setLayoutY(149.0);

        createScene();
    }

    private void createScene(){

        Text text = new Text();
        text.setStrokeWidth(0.0);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setLayoutX(150.0);
        text.setLayoutY(132.0);
        text.setFill(Paint.valueOf("#ffffff"));
        text.setWrappingWidth(263.21875);
        text.setFont(new Font("Futura Medium", 31));
        text.setText("Log in");

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.setPrefHeight(MainFX.SCENE_WIDTH);
        anchorpane.setPrefWidth(MainFX.SCENE_WIDTH);
        Pane pane = new Pane();
        pane.setPrefHeight(MainFX.SCENE_WIDTH);
        pane.setPrefWidth(MainFX.SCENE_WIDTH);
        pane.setStyle("-fx-background-color: #3de377;");

        ObservableList<Node> aChilds = anchorpane.getChildren();
        ObservableList<Node> pChilds = pane.getChildren();
        pChilds.addAll(text, email, password, log, create, error);
        aChilds.add(pane);

        Scene scene = new Scene(anchorpane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        scene.getStylesheets().add("hkrFx/General.css");
        this.setScene(scene);

        //this.show();
    }
}