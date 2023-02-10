package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application {

    private double mouseX = 0;
    private double mouseY = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Image img = new Image(getClass().getResource("/image/background.png").toString());
        ImageView imgView = new ImageView(img);
        Label lblClose = new Label("X");
        Label lblMinimize = new Label("➖");
        lblMinimize.setPadding(new Insets(5, 0, 0, 0));

        lblClose.setFont(new Font(28));
        lblClose.setCursor(Cursor.HAND);
        lblMinimize.setFont(new Font(28));
        lblMinimize.setCursor(Cursor.HAND);

        HBox hBox = new HBox(lblMinimize, lblClose);
        hBox.setSpacing(20);

        Slider slider = new Slider(0,100, 30);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(10);
//        slider.setSnapToTicks(true);

        slider.valueProperty().addListener((value, previous, current) -> {
            System.out.println(current);
        });

        AnchorPane root = new AnchorPane(imgView, hBox, slider);
        imgView.setOpacity(0.9);
        AnchorPane.setTopAnchor(hBox, 15.0);
        AnchorPane.setRightAnchor(hBox, 80.0);
        AnchorPane.setLeftAnchor(slider, 100.0);
        AnchorPane.setRightAnchor(slider, 100.0);
        AnchorPane.setTopAnchor(slider, 200.0);

        root.setBackground(Background.fill(Color.TRANSPARENT));         // <- 1
        Scene mainScene = new Scene(root);
        mainScene.setFill(Color.TRANSPARENT);                           // <- 2
        primaryStage.setScene(mainScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);                 // <- 3
        primaryStage.setTitle("Undecorated Windows");
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.centerOnScreen();

        lblClose.setOnMouseEntered(event -> lblClose.setTextFill(Color.RED));
        lblClose.setOnMouseExited(event -> lblClose.setTextFill(Color.BLACK));
        lblClose.setOnMouseClicked(event -> primaryStage.close());

        lblMinimize.setOnMouseEntered(event -> lblMinimize.setTextFill(Color.GREENYELLOW));
        lblMinimize.setOnMouseExited(event -> lblMinimize.setTextFill(Color.BLACK));
        lblMinimize.setOnMouseClicked(event -> primaryStage.setIconified(true));

        root.setOnMousePressed(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });

        root.setOnMouseReleased(event -> root.setCursor(Cursor.DEFAULT));

        root.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                primaryStage.setX(event.getScreenX() - mouseX);
                primaryStage.setY(event.getScreenY() - mouseY);
                root.setCursor(Cursor.MOVE);
            }
        });
    }
}
