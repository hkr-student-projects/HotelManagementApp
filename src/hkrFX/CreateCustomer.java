package hkrFX;

import hkrDB.DatabaseManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCustomer extends Stage{

    private Text[] texts;
    private Button[] buttons;
    private PasswordField passwordfield;
    private PasswordField passwordfield2;
    private TextField[] fields;

    public CreateCustomer(){

        this.setResizable(false);
        fields = createFields(
                new String[] { "Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006" },
                new double[] { 236.0, 412.0, 236.0, 236.0, 236.0 },
                new double[] { 53.0, 53.0, 98.0, 143.0, 188.0 }
        );
        createScene();
    }

    private void createScene(){

        Text[] texts = createTexts(
                new String[] { "Name", "SSN", "Phone", "Address", "Email", "Password", "Repeat password" },
                new double[] { 71, 116, 156, 200, 268, 311, 351 },
                new double[] { 68.21875, 68.21875, 68.21875, 68.21875, 68.21875, 90.21875, 146.21875}
        );

        fields = createFields(
                new String[] { "Name", "Surname", "19890518-1234", "+073-751-06-21", "Storagatan 12A-1006", "example@exmaple.com"},
                new double[] { 236, 412, 236, 236, 236, 236},
                new double[] { 53, 53, 95, 137, 180, 247}
        );

        buttons = createButtons(
                new String[] {"Save", "Reset"},
                new double[] {60, 82},
                new double[] {518, 321}
        );

        passwordfield = new PasswordField();
        passwordfield.setPrefHeight(27.0);
        passwordfield.setPrefWidth(155.0);
        passwordfield.setLayoutX(236.0);
        passwordfield.setStyle("-fx-background-color: #f5f5f5 #f5f5f5; -fx-background-radius: 111;");
        passwordfield.setLayoutY(288.0);
        passwordfield.setPromptText("password");

        passwordfield2 = new PasswordField();
        passwordfield2.setPrefHeight(27.0);
        passwordfield2.setPrefWidth(155.0);
        passwordfield2.setLayoutX(237.0);
        passwordfield2.setStyle("-fx-background-color: #f5f5f5 #f5f5f5; -fx-background-radius: 111;");
        passwordfield2.setLayoutY(328.0);
        passwordfield2.setPromptText("password");

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.setPrefHeight(437.0);
        anchorpane.setPrefWidth(600.0);
        anchorpane.setStyle("-fx-background-color: white;");

        Pane pane = new Pane();
        pane.setPrefHeight(437.0);
        pane.setPrefWidth(216.0);
        pane.setStyle("-fx-background-color: #ffb053;");

        anchorpane.getChildren().add(pane);



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
            txts[i].setLayoutX(24);
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
            buttons[i].setLayoutY(396);
            buttons[i].setMnemonicParsing(false);
            buttons[i].setPrefHeight(27.0);
            buttons[i].setPrefWidth(prefWidths[i]);
            buttons[i].setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 8");
            buttons[i].setFont(new Font(12));

        }
        buttons[0].setOnAction(event -> closeStage());

        return buttons;
    }

    private void redOutField(TextField field){
        //?change CSS class
        field.setStyle("-fx-control-inner-background: #ff4c4c; -fx-background-radius: 111;");
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused)
                field.setStyle("-fx-background-radius: 111;");
        });
    }

    private void closeStage(){

        // "Name", "Surname", "19890518-4376", "+073-751-06-21", "Storagatan 12A-1006"
        boolean errors = false;
        String name = null;
        String sname = null;
        String ssn = null;
        String phone = null;
        String addr = null;
        String email = null;
        String pass = passwordfield.getText();

        if(passwordfield.getText().isEmpty() || passwordfield2.getText().isEmpty() || passwordfield.getText() != passwordfield2.getText()){
            redOutField(passwordfield);
            redOutField(passwordfield2);
            errors = true;
        }
        for(TextField field : fields) {
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
                    break;
                case "example@example.com" :
                    ResultSet rs = (ResultSet) MainFX.databaseManager.executeQuery(DatabaseManager.QueryType.READER,
                            "SELECT 1 FROM hotel.Account WHERE hotel.Account.email = '"+email+"';");
                    try {
                        if(!rs.next() || !field.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                            redOutField(field);
                            errors = true;
                        }
                    } catch (SQLException e) {
                        Logger.logException(e);
                    }

                    break;
            }
        }
        if(!errors){
            MainFX.databaseManager.createPerson(email, pass, ssn, name, sname, addr, phone);
            this.close();
        }

    }
}
