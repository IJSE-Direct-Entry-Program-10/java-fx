package lk.ijse.dep10.animations;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppInitializer extends Application {

    private Stage stageTransition;
    private Stage stageAnimation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainScene(primaryStage);
        primaryStage.show();
    }

    private void mainScene(Stage stage){
        Label lblTitle = new Label("Java FX Animation Module");
        Button btnTransitions = new Button("Transitions");
        Button btnAnimations = new Button("Animations");
        Label lblTime = new Label(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        KeyFrame key1 = new KeyFrame(Duration.seconds(1), event -> {
            lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        });

        Timeline timeline = new Timeline(key1);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        lblTime.setFont(new Font(18));
        lblTime.setMaxWidth(Double.MAX_VALUE);
        lblTime.setAlignment(Pos.CENTER);

        lblTitle.setFont(new Font(24));
        lblTitle.setMaxWidth(Double.MAX_VALUE);
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setPadding(new Insets(0,0,5,0));

        Font font = new Font(16);
        btnTransitions.setFont(font);
        btnAnimations.setFont(font);
        btnTransitions.setMaxWidth(Double.MAX_VALUE);
        btnAnimations.setMaxWidth(Double.MAX_VALUE);

        btnTransitions.setOnAction(event -> transitionStage());
        btnAnimations.setOnAction(event -> animationStage());

        VBox root = new VBox(lblTitle, btnTransitions, btnAnimations, lblTime);
        root.setSpacing(20);
        root.setBackground(Background.fill(Color.WHITE));
        root.setPadding(new Insets(10));

        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        stage.setTitle("Java FX Animations Demo");
        stage.setWidth(400);
    }

    private void transitionStage(){
        if (stageTransition != null) return;
        stageTransition = new Stage();
        stageTransition.setOnCloseRequest(event -> stageTransition = null);
        stageTransition.setTitle("Transitions");
        transitionScene();
        stageTransition.show();
        stageTransition.centerOnScreen();
    }

    private void transitionScene(){
        Button btnReset = new Button("Reset");
        Button btnFadeIn = new Button("Fade In");
        Button btnFadeOut = new Button("Fade Out");
        Button btnScale = new Button("Scale");
        Button btnRotate = new Button("Rotate");
        Button btnTranslateX = new Button("Translate X");
        Button btnTranslateY = new Button("Translate Y");

        HBox hBox = new HBox(btnReset, btnFadeIn, btnFadeOut, btnScale, btnRotate, btnTranslateX, btnTranslateY);
        Label lblPreview = new Label("PREVIEW");

        VBox root = new VBox(hBox, lblPreview);

        root.setPrefWidth(800);
        root.setPrefHeight(400);
        root.setPadding(new Insets(10));
        root.setBackground(Background.fill(Color.WHITE));
        root.setSpacing(150);

        hBox.setSpacing(10);
        Font font = new Font(16);
        for (Node child : hBox.getChildren()) {
            Button btn = (Button) child;
            btn.setFont(font);
            btn.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(btn, Priority.ALWAYS);
        }

        lblPreview.setMaxWidth(Double.MAX_VALUE);
        lblPreview.setAlignment(Pos.CENTER);
        lblPreview.setFont(new Font(44));
        lblPreview.setTextFill(Color.LIGHTGRAY);

        Scene scene = new Scene(root);
        stageTransition.setScene(scene);
        stageTransition.setMinWidth(700);

        btnFadeIn.setOnAction(event -> {
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), lblPreview);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });

        btnFadeOut.setOnAction(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), lblPreview);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.play();
        });

        btnScale.setOnAction(event -> {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), lblPreview);
            scale.setFromX(1);
            scale.setToY(1);
            scale.setToX(2);
            scale.setToY(2);
            scale.play();
        });

        btnRotate.setOnAction(event -> {
            RotateTransition rotate = new RotateTransition(Duration.seconds(1), lblPreview);
            rotate.setFromAngle(0);
            rotate.setToAngle(180);
            rotate.play();
        });

        btnTranslateX.setOnAction(event -> {
            TranslateTransition translateX = new TranslateTransition(Duration.seconds(1), lblPreview);
            translateX.setFromX(-500);
            translateX.setToX(0);
            translateX.play();
        });

        btnTranslateY.setOnAction(event -> {
            TranslateTransition translateY = new TranslateTransition(Duration.seconds(1), lblPreview);
            translateY.setFromY(-500);
            translateY.setToY(0);
            translateY.play();
        });

        btnReset.setOnAction(event -> {
            lblPreview.setScaleX(1);
            lblPreview.setScaleY(1);
            lblPreview.setRotate(0);
            lblPreview.setOpacity(1);
        });
    }

    private void animationStage(){
        if (stageAnimation != null) return;
        stageAnimation = new Stage();
        stageAnimation.setOnCloseRequest(event -> stageAnimation = null);
        stageAnimation.setTitle("Animations");
        animationScene();
        stageAnimation.show();
        stageAnimation.centerOnScreen();
    }

    private void animationScene(){
        Button btnPlay = new Button("Play");
        Button btnStop = new Button("Stop");

        VBox vBox = new VBox(btnPlay, btnStop);

        Label lblPreview = new Label("PREVIEW");
        StackPane stackPane = new StackPane(lblPreview);

        HBox root = new HBox(vBox, stackPane);

        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setBackground(Background.fill(Color.WHITE));

        stackPane.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(stackPane, Priority.ALWAYS);
        stackPane.setBorder(Border.stroke(Color.LIGHTGRAY));

        lblPreview.setFont(new Font(44));
        lblPreview.setTextFill(Color.DARKGRAY);

        vBox.setSpacing(10);

        Font font = new Font(16);
        btnPlay.setMinWidth(150);
        btnStop.setMinWidth(150);
        btnPlay.setMaxWidth(Double.MAX_VALUE);
        btnStop.setMaxWidth(Double.MAX_VALUE);
        btnPlay.setFont(font);
        btnStop.setFont(font);

        Scene scene = new Scene(root);
        stageAnimation.setMinWidth(500);
        stageAnimation.setWidth(500);
        stageAnimation.setMinHeight(130);
        stageAnimation.setScene(scene);

        KeyFrame key1 = new KeyFrame(Duration.millis(500), event-> {
            lblPreview.setTextFill(Color.RED);
        });

        KeyFrame key2 = new KeyFrame(Duration.millis(1000), event-> {
            lblPreview.setScaleX(1.5);
            lblPreview.setScaleY(1.5);
        });

        KeyFrame key3 = new KeyFrame(Duration.millis(1250), event-> {
            lblPreview.setTextFill(Color.GREEN);
            lblPreview.setScaleX(1);
            lblPreview.setScaleY(1);
        });

        KeyFrame key4 = new KeyFrame(Duration.millis(1800), event-> {
            lblPreview.setTranslateX(50);
        });

        KeyFrame key5 = new KeyFrame(Duration.millis(2500), event-> {
            lblPreview.setTextFill(Color.BLUE);
            lblPreview.setTranslateX(0);
        });

        Timeline timeline = new Timeline(key1, key2, key3, key4, key5);
        timeline.setCycleCount(Animation.INDEFINITE);

        btnPlay.setOnAction(event -> timeline.playFromStart());
        btnStop.setOnAction(event -> timeline.stop());
    }
}
