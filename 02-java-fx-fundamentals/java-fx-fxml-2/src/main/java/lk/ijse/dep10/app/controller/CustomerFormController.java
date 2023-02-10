package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFormController {

    @FXML
    private Button btnBack;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        AnchorPane root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage)(btnBack.getScene().getWindow());
        stage.setScene(scene);
        stage.sizeToScene();

    }

}
