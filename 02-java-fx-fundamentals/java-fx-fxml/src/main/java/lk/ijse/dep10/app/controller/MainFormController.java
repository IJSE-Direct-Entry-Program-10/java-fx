package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainFormController {

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView imgPreview;

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    void btnBrowseOnAction(ActionEvent event) {
        System.out.println("Browse Button");
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        System.out.println("Remove Button");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        System.out.println("Save Button");
    }

    @FXML
    void txtAddressOnMouseEntered(MouseEvent event) {
        System.out.println("Address Text Field");
    }

}
