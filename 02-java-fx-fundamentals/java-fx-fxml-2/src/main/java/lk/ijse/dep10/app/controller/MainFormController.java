package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    public AnchorPane root;

    @FXML
    private Button btnCustomerForm;

    @FXML
    private Button btnStudentForm;

    @FXML
    void btnCustomerFormOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
        AnchorPane root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void btnStudentFormOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
        AnchorPane root1 = fxmlLoader.load();

        Scene scene = new Scene(root1);

        Stage stage = (Stage)(root.getScene().getWindow());
        stage.setScene(scene);
        stage.sizeToScene();
    }

    @FXML
    void btnNothingOnAction(ActionEvent actionEvent) {
        System.out.println("Nothing Happens Here");
    }
}
