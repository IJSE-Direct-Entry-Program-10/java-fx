package lk.ijse.dep10.last.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsViewController {

    @FXML
    private Button btnApply;

    @FXML
    private TextField txtTitle;

    private SimpleStringProperty observable;

    public void initData(SimpleStringProperty observable){
        this.observable = observable;
        txtTitle.setText(observable.getValue());
    }

    @FXML
    void btnApplyOnAction(ActionEvent event) {
        observable.setValue(txtTitle.getText());
    }

}
