package lk.ijse.dep10.fx2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        System.out.println("Before launching FX Runtime Env.");
        launch(args);
        System.out.println("After launching FX Runtime Env.");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("FX Entry Point");
        Platform.exit();
    }
}
