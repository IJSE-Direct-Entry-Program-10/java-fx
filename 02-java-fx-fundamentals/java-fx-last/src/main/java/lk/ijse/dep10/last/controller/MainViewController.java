package lk.ijse.dep10.last.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainViewController {

    @FXML
    private Button btnComboBox;

    @FXML
    private Button btnListView;

    @FXML
    private Button btnSceneCommunication;

    @FXML
    private Button btnTableView;

    @FXML
    void btnListViewOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        URL fxmlFile = getClass().getResource("/view/ListViewScene.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile);
        AnchorPane root = fxmlLoader.load();

        stage.setTitle("List View Demo");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnListView.getScene().getWindow());
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void btnComboBoxOnAction(ActionEvent event) {

    }


    @FXML
    void btnSceneCommunicationOnAction(ActionEvent event) {

    }

    @FXML
    void btnTableViewOnAction(ActionEvent event) {

    }

}
