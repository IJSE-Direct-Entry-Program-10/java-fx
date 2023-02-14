package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Optional;

public class ListViewSceneController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblSelectedName;

    @FXML
    private ListView<String> lstNames;

    @FXML
    private TextField txtInput;

    public void initialize(){
        lstNames.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {

            if (current == null){
                lblSelectedName.setText("No name has been selected");
                btnRemove.setDisable(true);
                return;
            }

            lblSelectedName.setText("Selected Name: " + current);
            btnRemove.setDisable(false);
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String input = txtInput.getText();
        if (input.isBlank()) {
            txtInput.selectAll();
            txtInput.requestFocus();
            return;
        }

        ObservableList<String> names = lstNames.getItems();
        names.add(input.strip());

        txtInput.clear();
        txtInput.requestFocus();
        lstNames.getSelectionModel().clearSelection();
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String selectedName = lstNames.getSelectionModel().getSelectedItem();
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete " + selectedName,
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optButton = confirmMsg.showAndWait();
        if (optButton.isEmpty() || optButton.get() == ButtonType.NO) return;

        ObservableList<String> names = lstNames.getItems();
        names.remove(lstNames.getSelectionModel().getSelectedIndex());

        lstNames.getSelectionModel().clearSelection();
        txtInput.requestFocus();
    }

    public void lstNamesOnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE){
            btnRemove.fire();
        }
    }
}
