package lk.ijse.dep10.exp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppInitializer extends Application {

    private int i = 0;
    private int textIndex = 0;
    private int pos = 0;
    private boolean reverse = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loginScene(primaryStage);
        primaryStage.show();
    }

    private void tempScene(Stage stage) {
        Button btn = new Button("Click Me..!");

        AnchorPane root = new AnchorPane(btn);
        AnchorPane.setLeftAnchor(btn, 10.0);
        AnchorPane.setRightAnchor(btn, 10.0);

        Scene tempScene = new Scene(root);
        stage.setScene(tempScene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.centerOnScreen();
    }

    private void loginScene(Stage stage) {
        Label lblTitle = new Label("LOG IN");
        Label lblPassword = new Label("Enter password");
        PasswordField txtPassword = new PasswordField();
        Label lblHint = new Label("Invalid Password, Try Again!");
        Button btnLogIn = new Button("LOG IN");

        btnLogIn.setOnAction(event -> {
            String password = txtPassword.getText();
            if (!password.equals("Admin")) {
                lblHint.setVisible(true);
                txtPassword.selectAll();
            } else {
                mainScene(stage);
            }
        });

        lblTitle.setFont(new Font(44));
        lblTitle.setTextFill(Color.DARKBLUE);
        lblPassword.setPadding(new Insets(25, 0, 0, 0));
        txtPassword.setMaxWidth(350);
        txtPassword.setAlignment(Pos.CENTER);
        lblHint.setTextFill(Color.RED);
        lblHint.setVisible(false);
        btnLogIn.setFont(new Font(16));
        btnLogIn.setDefaultButton(true);

        VBox root = new VBox(lblTitle, lblPassword, txtPassword, lblHint, btnLogIn);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(400);
        root.setPrefHeight(400);

        Scene loginScene = new Scene(root);
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    private void mainScene(Stage stage) {
        Label lblGreeting = new Label("Hi! Welcome to the App");
        Label lbl = new Label("I am moving!");
        Rectangle cursor = new Rectangle(10, 16);

        HBox hBox = new HBox(lbl, cursor);
        hBox.setVisible(false);

        lblGreeting.setFont(new Font(32));
        lblGreeting.setTextFill(new Color(0, 0, 0, 0.2));

        AnchorPane root = new AnchorPane(lblGreeting, hBox);
        AnchorPane.setRightAnchor(lblGreeting, 0.0);
        AnchorPane.setLeftAnchor(lblGreeting, 0.0);
        AnchorPane.setTopAnchor(lblGreeting, 0.0);
        AnchorPane.setBottomAnchor(lblGreeting, 0.0);

        lblGreeting.setAlignment(Pos.CENTER);
        root.setBackground(Background.fill(Color.WHITE));
        root.setPrefHeight(600);
        root.setPrefWidth(800);

        root.setOnMouseEntered(event -> hBox.setVisible(true));
        root.setOnMouseExited(event -> hBox.setVisible(false));
        root.setOnMouseMoved(event -> {
            hBox.setLayoutX(event.getX() + 20);
            hBox.setLayoutY(event.getY() + 20);
        });

        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.sizeToScene();
        stage.setTitle("My App");
        stage.centerOnScreen();

//        KeyFrame key0 = new KeyFrame(Duration.ZERO, event -> {
//            lblGreeting.setTranslateX(-500);
//        });

        KeyFrame key0 = new KeyFrame(Duration.ZERO, new KeyValue(lblGreeting.translateXProperty(), -500));

//        KeyFrame key1 = new KeyFrame(Duration.millis(1000), event -> {
//            lblGreeting.setTranslateX(100);
//        });

        KeyFrame key1 = new KeyFrame(Duration.millis(500), new KeyValue(lblGreeting.translateXProperty(), 200));

//        KeyFrame key2 = new KeyFrame(Duration.millis(1500), event -> {
//            lblGreeting.setTranslateX(0);
//        });

        KeyFrame key2 = new KeyFrame(Duration.millis(650), new KeyValue(lblGreeting.translateXProperty(), 0));

//        KeyFrame key3 = new KeyFrame(Duration.millis(1850), event -> {
//            lblGreeting.setScaleX(1.5);
//            lblGreeting.setScaleY(1.5);
//        });

        KeyFrame key3 = new KeyFrame(Duration.millis(800), new KeyValue(lblGreeting.scaleXProperty(), 1.5), new KeyValue(lblGreeting.scaleYProperty(), 1.5));

//        KeyFrame key4 = new KeyFrame(Duration.millis(2000), event -> {
//            lblGreeting.setScaleX(1);
//            lblGreeting.setScaleY(1);
//        });

        KeyFrame key4 = new KeyFrame(Duration.millis(1000), new KeyValue(lblGreeting.scaleXProperty(), 1), new KeyValue(lblGreeting.scaleYProperty(), 1));

        Timeline timeline = new Timeline(key0, key1, key2, key3, key4);
        timeline.play();

        blinkCursor(cursor);
        animateText(lbl);
    }

    private void blinkCursor(Rectangle cursor) {
        String[] colors = {"red", "black", "green", "blue"};
        KeyFrame keyFrame = new KeyFrame(Duration.millis(400), event -> {
            cursor.setVisible(!cursor.isVisible());
            if (cursor.isVisible()){
                cursor.setFill(Color.web(colors[i]));
                i++;
                if (i == colors.length) i = 0;
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void animateText(Label lbl) {
        String[] texts = {"Hello!", "How are you?", "Nice to meet you"};

        KeyFrame keyFrame = new KeyFrame(Duration.millis(75), event -> {
            String text = texts[textIndex];
            lbl.setText(text.substring(0, !reverse? pos++: pos--));
            if (pos == text.length()) reverse = true;
            if (pos == 0) {
                reverse = false;
                textIndex++;
                if (textIndex == texts.length) textIndex = 0;
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
}
