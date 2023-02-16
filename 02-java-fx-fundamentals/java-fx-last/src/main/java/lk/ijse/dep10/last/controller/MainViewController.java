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

    public Button btnListViewExercise;
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

    public void btnListViewExerciseOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

        URL fxmlFile = getClass().getResource("/view/ListViewExerciseScene.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile);
        AnchorPane root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnListViewExercise.getScene().getWindow());
        stage.setTitle("List View Exercise");
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void btnComboBoxOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();

//        URL fxmlFile = getClass().getResource("/view/CombBoxScene.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CombBoxScene.fxml"));
//        AnchorPane root = new FXMLLoader(getClass().getResource("/view/CombBoxScene.fxml")).load();
//        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/CombBoxScene.fxml")).load());
//        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/view/CombBoxScene.fxml")).load()));

        stage.setScene(new Scene(new FXMLLoader(getClass()
                .getResource("/view/CombBoxScene.fxml")).load()));

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnComboBox.getScene().getWindow());
        stage.setTitle("Combo Box Demo");
        stage.show();
        stage.centerOnScreen();
    }


    @FXML
    void btnSceneCommunicationOnAction(ActionEvent event) {

    }

    @FXML
    void btnTableViewOnAction() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(new FXMLLoader
                (getClass().getResource("/view/TableViewScene.fxml")).load()));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnTableView.getScene().getWindow());
        stage.setTitle("Table View Demo");
        stage.show();
        stage.centerOnScreen();
    }
}
