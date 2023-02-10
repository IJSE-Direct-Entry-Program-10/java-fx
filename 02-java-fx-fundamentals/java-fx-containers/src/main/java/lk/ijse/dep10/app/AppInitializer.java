package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public AppInitializer() {
        System.out.println("Who the heck created me?");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //hBoxDemo();
        //vBoxDemo();
        gridPaneDemo();
    }

    private void gridPaneDemo(){
        Stage stage = new Stage();

        Label lblTitle = new Label("Customer Details");
//        lblTitle.setFont(Font.font(20));

        Label lblId = new Label("ID");
        Label lblName = new Label("Name");
        Label lblAddress = new Label("Address");

        TextField txtId = new TextField();
        TextField txtName = new TextField();
        TextArea txtAddress = new TextArea();

        Button btnSave = new Button("SAVE");
        btnSave.setOnAction(event -> {
            hBoxDemo();
        });

        GridPane container = new GridPane();

        container.add(lblTitle, 0,0, 2, 1);
        container.add(lblId, 0, 1);
        container.add(txtId, 1, 1);
        container.add(lblName, 0, 2);
        container.add(txtName, 1, 2);
        container.add(lblAddress, 0, 3);
        container.add(txtAddress, 1, 3);
        container.add(btnSave, 1, 4);
        container.setHgap(10);
        container.setVgap(10);
//        container.setPadding(new Insets(100,50,25,50));
        container.setPadding(new Insets(20));
//        container.setGridLinesVisible(true);
//
        GridPane.setHalignment(lblTitle, HPos.CENTER);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Grid Pane Demo");
        stage.show();
        stage.centerOnScreen();
    }

    private void vBoxDemo(){
        Stage stage = new Stage();

        TextField[] textFields = new TextField[5];
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new TextField("Text Field " + (i+1));
        }

        VBox container = new VBox(10, textFields);
        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("VBox Demo");
        stage.show();
        stage.centerOnScreen();
    }

    private void hBoxDemo(){
        Stage stage = new Stage();

        Button[] buttons = new Button[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("Button " + (i+1));
        }

        HBox container = new HBox(10, buttons);
        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("HBox Demo");
        stage.show();
        stage.centerOnScreen();
    }
}
