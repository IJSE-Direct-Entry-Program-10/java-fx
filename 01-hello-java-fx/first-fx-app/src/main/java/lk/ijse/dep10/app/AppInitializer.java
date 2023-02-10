package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene mainScene = new Scene(anchorPane);
        Button btnClickMe = new Button("Click Me!");

        anchorPane.getChildren().add(btnClickMe);
        btnClickMe.setLayoutX(20);
        btnClickMe.setLayoutY(20);
        stage.setScene(mainScene);
        stage.setTitle("My First Java FX App");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();

//        Stage stage2 = new Stage();
//        stage2.setTitle("My Second Stage");
//        stage2.show();
    }
}
