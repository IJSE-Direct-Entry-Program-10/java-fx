package lk.ijse.dep10.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        System.out.println("Just before the launching of Java FX Runtime Env.");
        launch(args);
        System.out.println("After Java FX Runtime Env. has been destroyed");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("FX Entry Point");
        Platform.exit();
    }
}
