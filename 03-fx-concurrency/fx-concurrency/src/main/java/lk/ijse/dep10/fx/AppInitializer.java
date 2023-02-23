package lk.ijse.dep10.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        launch(args);
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println(Thread.currentThread().getName());
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/scene/ProgressScene.fxml"))));
        primaryStage.setTitle("FX Concurrency");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
