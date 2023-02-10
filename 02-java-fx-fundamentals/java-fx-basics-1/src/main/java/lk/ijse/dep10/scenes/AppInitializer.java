package lk.ijse.dep10.scenes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("JVM is about to shutdown");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scene Manipulation");
        primaryStage.show();
//        primaryStage.setMinWidth(250);
//        primaryStage.setMaxWidth(750);
        mainScene(primaryStage);
    }

    private void mainScene(Stage stage){
        Label lblTitle = new Label("Main Scene");
        Button btn1 = new Button("Scene 1");
        Button btn2 = new Button("Scene 2");
        Button btn3 = new Button("Scene 3");
        Button btn4 = new Button("Platform Exit");
        Button btn5 = new Button("System Exit");

        btn1.setOnAction(event -> firstScene(stage));
        btn2.setOnAction(event -> secondScene(stage));
        btn3.setOnAction(event -> thirdScene(stage));

        btn4.setOnAction(event -> {
            System.out.println("Platform Exit");
            Platform.exit();
        });

        btn5.setOnAction(event -> {
            System.out.println("System Exit");
            System.exit(0);
        });

//        Paint paint4Btn4 = new Color(0.8,1,0,1);
//        Paint paint4Btn4 = Color.rgb(255,0,0,1);

        lblTitle.setBackground(Background.fill(Color.web("black")));
        lblTitle.setTextFill(Color.web("white"));

        btn4.setBackground(Background.fill(Color.web("orange")));

        VBox root = new VBox(lblTitle, btn1, btn2, btn3, btn4, btn5);
        root.setPrefWidth(400);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Font font = new Font("Ubuntu", 20);
//        lblTitle.setFont(font);
//        btn1.setFont(font);
//        btn2.setFont(font);
//        btn3.setFont(font);
//        btn4.setFont(font);
//        btn5.setFont(font);

        for (Node child : root.getChildren()) {
            if (!(child instanceof Labeled)) continue;
            Labeled lbl = (Labeled) child;
            lbl.setFont(font);
            lbl.setMaxWidth(Double.MAX_VALUE);

//            if (child instanceof Button){
//                ((Button) child).setFont(font);
//            }else if (child instanceof  Label){
//                ((Label) child).setFont(font);
//            }
        }
        lblTitle.setAlignment(Pos.CENTER);

        root.setBackground(Background.fill(Color.web("lightgreen")));

        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    private void firstScene(Stage stage){
        Label lblTitle = new Label("First Scene");
        Button btnBack = new Button("Back");

        lblTitle.setFont(new Font(24));
        btnBack.setOnAction(event -> mainScene(stage));

        VBox root = new VBox(lblTitle, btnBack);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPrefWidth(400);
        root.setPrefHeight(400);
        root.setBackground(Background.fill(Color.web("lightblue")));

        Scene firstScene = new Scene(root);

        stage.setScene(firstScene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    private void secondScene(Stage stage){
        Label lblTitle = new Label("Second Scene");
        Button btnBack = new Button("Back");

        lblTitle.setFont(new Font(24));
        btnBack.setOnAction(event -> mainScene(stage));

        VBox root = new VBox(lblTitle, btnBack);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPrefWidth(400);
        root.setPrefHeight(400);
        root.setBackground(Background.fill(Color.web("lightyellow")));

        Scene secondScene = new Scene(root);

        stage.setScene(secondScene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    private void thirdScene(Stage stage){
        Label lblTitle = new Label("Third Scene");
        Button btnBack = new Button("Back");

        lblTitle.setFont(new Font(24));
        btnBack.setOnAction(event -> mainScene(stage));

        VBox root = new VBox(lblTitle, btnBack);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPrefWidth(400);
        root.setPrefHeight(400);
        root.setBackground(Background.fill(Color.web("lightgray")));

        Scene thirdScene = new Scene(root);

        stage.setScene(thirdScene);
        stage.sizeToScene();
        stage.centerOnScreen();
    }
}
