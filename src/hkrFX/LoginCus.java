package hkrFX;

import hkrDB.DatabaseManager;
import javafx.geometry.Pos;
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

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCus extends Stage {

    private TextField email;
    private PasswordField password;
    private Button log;
    private Button create;
    private Button forgot;
    private Label error;

    public LoginCus(){
        this.setResizable(false);

        email = new TextField();
        email.setLayoutX(200);
        email.setStyle("-fx-background-color: white; -fx-background-radius: 0;");
        email.setLayoutY(166.0);
        email.setAlignment(Pos.CENTER);
        email.setPromptText("email");
        email.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused)
                error.setText("");
        });

        password = new PasswordField();
        password.setLayoutX(200);
        password.setStyle("-fx-background-color: white; -fx-background-radius: 0;");
        password.setLayoutY(208);
        password.setAlignment(Pos.CENTER);
        password.setPromptText("password");
        password.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused){
                error.setText("");
                password.setText("");
            }
        });

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
        log.setOnAction(event -> {
            if(email.getText().isEmpty() || password.getText().isEmpty()){
                redOutField(email);
                redOutField(password);
                return;
            }
            try {
                if(checkCredentials()){
                    new PersonalAreaCus(MainFX.databaseManager.getProfile(email.getText())).show();
                }
            } catch (SQLException e) {
                Logger.logException(e);
            }
        });

        create = new Button();
        create.setPrefHeight(23.0);
        create.setPrefWidth(65.0);
        create.setTextFill(Paint.valueOf("#ffffff"));
        create.setLayoutX(190.0);
        create.setStyle("-fx-background-color: transparent; -fx-background-radius: 8;");
        create.setLayoutY(242.0);
        create.setText("Create!");
        create.setMnemonicParsing(false);
        create.setFont(new Font("System Bold", 12.0));

        forgot = new Button();
        forgot.setPrefHeight(23.0);
        forgot.setPrefWidth(65.0);
        forgot.setTextFill(Paint.valueOf("#ffffff"));
        forgot.setLayoutX(308.0);
        forgot.setStyle("-fx-background-color: transparent; -fx-background-radius: 8;");
        forgot.setLayoutY(242.0);
        forgot.setText("forgot?");
        forgot.setMnemonicParsing(false);
        forgot.setMnemonicParsing(false);
        forgot.setFont(new Font("System Bold", 12.0));

        error = new Label();
        error.setPrefHeight(17.0);
        error.setPrefWidth(200);
        error.setTextAlignment(TextAlignment.CENTER);
        error.setTextFill(Paint.valueOf("RED"));
        error.setLayoutX(200);
        error.setLayoutY(149.0);
        
        createScene();
    }

    private boolean checkCredentials() throws SQLException {
        if(!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            error.setText("Incorrect email format");
            return false;
        }
        ResultSet rs = (ResultSet) MainFX.databaseManager.executeQuery(DatabaseManager.QueryType.READER,
                "SELECT 1 FROM hotel.Account WHERE hotel.Account.email = '"+email.getText()+"' " +
                        "AND hotel.Account.password = SHA1('"+password.getText()+"');");
        if(!rs.next()){
            error.setText("Incorrect password or email");
        }
        else{
            error.setText("");
            return true;
        }

        return false;
    }

    private void redOutField(TextField field){
        //?change CSS class
        field.setStyle("-fx-background-color: #ff4c4c; -fx-background-radius: 0;");
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused)
                field.setStyle("-fx-background-color: white; -fx-background-radius: 0;");
        });
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
        pane.setStyle("-fx-background-color: #ffb053;");

        pane.getChildren().addAll(text, email, password, log, create, error);
        anchorpane.getChildren().add(pane);

        Scene scene = new Scene(anchorpane, MainFX.SCENE_WIDTH, MainFX.SCENE_HEIGHT);
        //scene.getStylesheets().add("hkrFx/css/General.css");
        this.setScene(scene);

        //this.show();
    }
}
