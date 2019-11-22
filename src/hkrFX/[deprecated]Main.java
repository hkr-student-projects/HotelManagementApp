//package sample;
//
//import hkrFX.LoginStage;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Paint;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import java.io.IOException;
//
////class ReceptionStage extends Stage{
////
////    private Button _add;
////    private Button _rem;
////
////    public ReceptionStage(){
////
////        this.setResizable(false);
////
////        _add = new Button("add");
////        _rem = new Button("Remove");
////        _add.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                new CustomerAddStage();
////            }
////        });
////        //back.setAlignment(Pos.BOTTOM_CENTER);
////        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 10, 10, _add, _rem);
////        root.setAlignment(Pos.CENTER);
////        this.setTitle("Create customer");
////
////        this.setScene(new Scene(root, Main.SCENE_WIDTH >> 1, Main.SCENE_HEIGHT >> 1));
////        this.show();
////    }
////}
//
//class CustomerAddStage extends Stage{
//
//    private TextField _ssn;
//    private TextField _name;
//    private TextField _adrs;
//    private TextField _phone;
//    private Text _output;
//    private Button _saveButton;
//    private static Paint _errorColor;
//
//    static {
//        _errorColor = Paint.valueOf("ff4c4c");
//    }
//
//    public CustomerAddStage(){
//        this.setResizable(false);
//        _ssn = new TextField();
//        _name = new TextField();
//        _adrs = new TextField();
//        _phone = new TextField();
//        _output = new Text();
//        _saveButton = new Button("Save");
//
//        _ssn.setTooltip(new Tooltip("example: 19980422-5491"));
//        _name.setTooltip(new Tooltip("example: John Cena"));
//        _name.setPromptText("Ray Musterio");
//        _adrs.setTooltip(new Tooltip("example: Storagatan 12A-1006"));
//        _phone.setTooltip(new Tooltip("example: +073-751-06-21"));
//        _output.setStyle("-fx-font-weight: bold");
//        _saveButton.setTooltip(new Tooltip("save data"));
//        _saveButton.setPrefWidth(100);
//        _saveButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                closeStage();
//            }
//        });
//
//        Scene scene = new Scene(new Group(), Main.SCENE_WIDTH >> 1 , Main.SCENE_HEIGHT);
//        GridPane grid = new GridPane();
//        grid.setVgap(5);
//        grid.setHgap(10);
//        grid.setPadding(new Insets(5, 5, 5 ,5));
//        grid.add(new Label("SSN: "), 0, 0);
//        grid.add(_ssn, 1, 0);
//        grid.add(new Label("Name: "), 0, 1);
//        grid.add(_name, 1, 1);
//        grid.add(new Label("address: "), 0, 2);
//        grid.add(_adrs, 1, 2);
//        grid.add(new Label("Phone №: "), 0, 3);
//        grid.add(_phone, 1, 3);
//        grid.add(_output, 1, 4);
//        grid.add(_saveButton, 1, 10);
//
//        Group root = (Group)scene.getRoot();
//        root.getChildren().add(grid);
//
//        this.setScene(scene);
//        this.show();
//    }
//
//
////    public void createScene(){
////
////        Scene scene = new Scene(new Group(), Main.SCENE_WIDTH >> 1 , Main.SCENE_HEIGHT);
////        GridPane grid = new GridPane();
////
////        grid.setVgap(5);
////        grid.setHgap(10);
////        grid.setPadding(new Insets(5, 5, 5 ,5));
////        grid.add(new Label("SSN: "), 0, 0);
////        grid.add(_ssn, 1, 0);
////        grid.add(new Label("Name: "), 0, 1);
////        grid.add(_name, 1, 1);
////        grid.add(new Label("address: "), 0, 2);
////        grid.add(_adrs, 1, 2);
////        grid.add(new Label("Phone №: "), 0, 3);
////        grid.add(_phone, 1, 3);
////        grid.add(_output, 1, 4);
////        grid.add(_saveButton, 1, 10);
////
////        Group root = (Group)scene.getRoot();
////        root.getChildren().add(grid);
////
////        this.setScene(scene);
////        this.show();
////    }
//
//    private void closeStage(){
//
//        String outErrors = "";
//        if(!_ssn.getText().matches("\\d{8}\\-\\d{4}")){
//            outErrors += " \nSSN";
//            _ssn.setStyle("-fx-control-inner-background: #"+_errorColor.toString().substring(2));
//            _ssn.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//                if (isNowFocused)
//                    _ssn.setStyle("");
//            });
//        }
//
//        if(!_name.getText().matches("[A-Z][a-z]*\\s[A-Z][a-z]*")){
//            outErrors += " \nName";
//            _name.setStyle("-fx-control-inner-background: #"+_errorColor.toString().substring(2));
//            _name.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//                if (isNowFocused)
//                    _name.setStyle("");
//            });
//        }
//
//        if(!_phone.getText().matches("\\+\\d{3}\\-\\d{3}\\-\\d{2}\\-\\d{2}")){
//            outErrors += " \nPhone";
//            _phone.setStyle("-fx-control-inner-background: #"+_errorColor.toString().substring(2));
//            _phone.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//                if (isNowFocused)
//                    _phone.setStyle("");
//            });
//        }
//
//        if(!_adrs.getText().matches("[A-Z][a-z]+\\s\\d+[A-Z]?\\-\\d+")){
//            outErrors += " \naddress";
//            _adrs.setStyle("-fx-control-inner-background: #"+_errorColor.toString().substring(2));
//            _adrs.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//                if (isNowFocused)
//                    _adrs.setStyle("");
//            });
//        }
//
//        if (outErrors.isEmpty()){
//            //create customer
//            this.close();
//        }
//
//        else{
//
//            _output.setText("FORMAT ERRORS:" + outErrors + "");
//        }
//
//    }
//}
//
//class CustomerStage extends Stage{
//
//    public CustomerStage(){
//
//    }
//}
//
//public class Main extends Application{
//
//    public static short SCENE_HEIGHT = 437;
//    public static short SCENE_WIDTH = 600;
//
//    private BookingStage bookingStage;
//    private HomeStage homeStage;
//
//
//    @Override
//    public void start(Stage stage) throws IOException {
//
//        bookingStage = new BookingStage();
//        homeStage = new HomeStage();
//        stage.setResizable(false);
//        initializeEvents();
//        stage.setScene(homeStage.getScene());
//        stage.show();
//
//        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//    }
//
//    private void initializeEvents(){
//
//        homeStage.getButton("Customer").setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                bookingStage.show();
//            }
//        });
//
//        homeStage.getButton("Employee").setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                //new ReceptionStage();
//            }
//        });
//
//        homeStage.getButton("Admin").setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                new LoginStage();
//            }
//        });
//
//        //scene.setFill(Color.TRANSPARENT);
//        //stage.initStyle(StageStyle.TRANSPARENT);
//    }
//
////    private Scene goReceptionScene(Stage stage){
////
////        Button add = new Button("add");
////        Button rem = new Button("Remove");
////        Button back = new Button("Home");
////        back.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                stage.setScene(initializeScene(stage));
////            }
////        });
////        add.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                new CustomerAddStage();
////            }
////        });
////        //back.setAlignment(Pos.BOTTOM_CENTER);
////        FlowPane root = new FlowPane(Orientation.HORIZONTAL, 10, 10, add, rem, back);
////        root.setAlignment(Pos.CENTER);
////        stage.setTitle("Reception mode (Create customer)");
////
////        return new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
////    }
//
////    private Scene goAdminScene(Stage stage){
////
////        GridPane grid = new GridPane();
////        grid.setAlignment(Pos.CENTER);
////        grid.setHgap(10);
////        grid.setVgap(10);
////        grid.setPadding(new Insets(25, 25, 25, 25));
////
////        final Text actiontarget = new Text();
////        grid.add(actiontarget, 1, 6);
////
////        Text scenetitle = new Text("Welcome");
////        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
////        grid.add(scenetitle, 0, 0, 2, 1);
////        Label userName = new Label("User Name:");
////        grid.add(userName, 0, 1);
////        TextField userTextField = new TextField();
////        grid.add(userTextField, 1, 1);
////        Label pw = new Label("Password:");
////        grid.add(pw, 0, 2);
////        PasswordField pwBox = new PasswordField();
////        grid.add(pwBox, 1, 2);
////
////        scenetitle.setId("welcome-text");
////        actiontarget.setId("actiontarget");
////
////        Button btn = new Button("Sign in");
////        btn.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent e) {
////                actiontarget.setFill(Color.FIREBRICK);
////                actiontarget.setText("Sign in button pressed");
////            }
////        });
////        HBox hbBtn = new HBox(10);
////        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
////        hbBtn.getChildren().add(btn);
////        grid.add(hbBtn, 1, 4);
////
////        stage.setTitle("Login page");
////        Scene scene = new Scene(grid, Main.SCENE_WIDTH, Main.SCENE_HEIGHT);
////        scene.getStylesheets().add("Global.css");
////
////        return scene;
////    }
//
//
//
//
//
//
////    @Override
////    public void start(Stage stage) throws Exception {
////
////        Label lbl = new Label();
////        TextField textField = new TextField();
////        textField.setPrefColumnCount(11);
////        Button btn = new Button("Click");
////        btn.setOnAction(event -> lbl.setText("Input: " + textField.getText()));
////        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, btn, lbl);
////        Scene scene = new Scene(root, 250, 200);
////
////        stage.setScene(scene);
////        stage.setTitle("TextField in JavaFX");
////        stage.show();
////    }
//
////    @Override
////    public void start(Stage primaryStage) {
////
////        final int numTextFields = 20 ;
////        TextField[] textFields = new TextField[numTextFields];
////        VBox root = new VBox(5);
////        for (int i = 1; i <= numTextFields; i++) {
////            TextField tf = new TextField();
////            String name = "Text field "+i ;
////            tf.setOnAction(e -> {
////                System.out.println("Action on "+name+": text is "+tf.getText());
////            });
////            root.getChildren().add(tf);
////            textFields[i-1] = tf ;
////        }
////        Scene scene = new Scene(new ScrollPane(root), 250, 600);
////        primaryStage.setScene(scene);
////        primaryStage.show();
////    }
//
//
//
////    @Override
////    public void start(Stage stage) {
////
//////        Button left = new Button("Left");
//////        BorderPane.setAlignment(left, Pos.CENTER);
////
//////        Button right = new Button("Right");
//////        BorderPane.setAlignment(right, Pos.BOTTOM_LEFT);
////
////        Button top = new Button("Top");
////        BorderPane.setAlignment(top, Pos.CENTER);
////
//////        Button bottom = new Button("Bottom");
//////        BorderPane.setAlignment(bottom, Pos.CENTER);
////
////        Button center = new Button("Center");
////        Button center1 = new Button("Center1");
////        Button center2 = new Button("Center2");
////        BorderPane root = new BorderPane(center, top, center1, center2, center1);
////
////        Scene scene = new Scene(root, 500, 300);
////        stage.setScene(scene);
////
////        stage.setTitle("BorderPane in JavaFX");
////
////        stage.show();
////    }
////    @Override
////    public void start(Stage stage) throws Exception {
////
////        ToggleButton lightBtn = new ToggleButton("Light");
////        Label stateLbl = new Label();
////        // обработка нажатия
////        lightBtn.setOnAction(event -> {
////
////            if(lightBtn.isSelected()){
////                stateLbl.setText("Light on!");
////            }
////            else{
////                stateLbl.setText("Light off...");
////            }
////        });
////
////        FlowPane root = new FlowPane(10, 10);
////        root.getChildren().addAll(lightBtn, stateLbl);
////        root.setPadding(new Insets(10));
////        Scene scene = new Scene(root, 250, 200);
////
////        stage.setScene(scene);
////
////        stage.setTitle("Hello JavaFX");
////
////        stage.show();
////    }
//
//
////    @Override
////    public void start(Stage stage) {
////
////        Button okBtn = new Button("OK");
////        Button cancelBtn = new Button("Cancel");
////        Label lbl = new Label("Select");
////
////        VBox vbox = new VBox(15);
////
////        VBox.setVgrow(okBtn, Priority.ALWAYS);
////        okBtn.setMaxHeight(100);
////        okBtn.setMaxWidth(100);
////
////        VBox.setVgrow(cancelBtn, Priority.ALWAYS);
////        cancelBtn.setMaxHeight(100);
////        cancelBtn.setMaxWidth(100);
////
////        vbox.getChildren().addAll(lbl, okBtn, cancelBtn);
////
////        Scene scene = new Scene(vbox, 300, 150);
////        stage.setScene(scene);
////
////        stage.setTitle("VBox in JavaFX");
////
////        stage.show();
////    }
//
////    @Override
////    public void start(Stage stage) {
////
////        HBox hbox = new HBox();
////        Button button1 = new Button("add");
////        Button button2 = new Button("Remove");
////        HBox.setHgrow(button1, Priority.ALWAYS);
////        HBox.setHgrow(button2, Priority.ALWAYS);
////        button1.setMaxWidth(Double.MAX_VALUE);
////        button2.setMaxWidth(Double.MAX_VALUE);
////        hbox.getChildren().addAll(button1, button2);
////        Scene scene = new Scene(hbox, 300, 150);
////        stage.setScene(scene);
////
////        stage.setTitle("HBox in JavaFX");
////
////        stage.show();
////    }
////
////    @Override
////    public void start(Stage stage) {
////
////        Label label1 = new Label("Label1");
////        Label label2 = new Label("Label2");
////
////        FlowPane root = new FlowPane(10, 10, label1, label2);
////        root.setAlignment(Pos.CENTER);
////        Scene scene = new Scene(root, 300, 150);
////        stage.setScene(scene);
////
////        stage.setTitle("FlowPane in JavaFX");
////
////        stage.show();
////    }
//    //@Override
////    public void start(Stage stage) {
////
////        Button btn = new Button();
////        btn.setText("Click!");
////
////
////        btn.setOnAction(new EventHandler<ActionEvent>() {
////
////            @Override
////            public void handle(ActionEvent event) {
////                if(flag){
////                    btn.setText("You've clicked!");
////                    flag = false;
////                }
////                else
////                {
////                    btn.setText("Click!");
////                    flag = true;
////                }
////            }
////        });
//
////        Group root = new Group(btn);
////        Scene scene = new Scene(root);
////        stage.setScene(scene);
////
////        stage.setTitle("Hello JavaFX");
////        stage.setWidth(250);
////        stage.setHeight(200);
////
////        stage.show();
////    }
//
////    @Override
////    public void start(Stage stage) {
////
////        stage.setTitle("Hello JavaFX"); // установка заголовка
////        stage.setWidth(700);           // установка ширины
////        stage.setHeight(300);           // установка длины
////        stage.show();                   // отображение окна на экране
////    }
//
////    @Override
////    public void start(Stage stage) {
////
////        // установка надписи
////        Text text = new Text("Hello from JavaFX!");
////        text.setLayoutY(80);    // установка положения надписи по оси Y
////        text.setLayoutX(100);   // установка положения надписи по оси X
////
////        Group group = new Group(text);
////
////        Scene scene = new Scene(group);
////        stage.setScene(scene);
////        stage.setTitle("First Application");
////        stage.setWidth(300);
////        stage.setHeight(250);
////        stage.show();
////    }
//}
