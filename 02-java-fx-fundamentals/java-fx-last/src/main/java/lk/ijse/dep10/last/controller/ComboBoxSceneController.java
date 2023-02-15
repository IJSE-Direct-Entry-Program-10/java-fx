package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ComboBoxSceneController {

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<String> cmbSomething;

    @FXML
    private Label lblOutput;

    @FXML
    private TextField txtInput;

    public void initialize(){
        ObservableList<String> itemList = cmbSomething.getItems();
        itemList.addAll("Kasun", "Nuwan", "Ruwan");
        cmbSomething.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            if (current == null){
                lblOutput.setText("Select something to display".toUpperCase());
            }else{
                lblOutput.setText(("Selected: " + current).toUpperCase());
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (txtInput.getText().isBlank()) return;
        ObservableList<String> itemList = cmbSomething.getItems();
        itemList.add(txtInput.getText().strip());
        txtInput.clear();
        txtInput.requestFocus();
    }

}
