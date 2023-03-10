package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.dep10.last.model.Student;

import java.util.Optional;

public class ListViewSceneController {

    public Button btnNew;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSave;
    public Button btnDelete;
    public ListView<Student> lstStudents;

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

    public void initialize() {
        lstNames.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {

            if (current == null) {
                lblSelectedName.setText("No name has been selected");
                btnRemove.setDisable(true);
                return;
            }

            lblSelectedName.setText("Selected Name: " + current);
            btnRemove.setDisable(false);
        });

        lstStudents.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {

            if (current == null){
                btnDelete.setDisable(true);
                return;
            }

            btnDelete.setDisable(false);
            txtId.setText(current.id);
            txtName.setText(current.name);
            txtAddress.setText(current.address);

            txtId.setDisable(true);
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
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete " + selectedName, ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optButton = confirmMsg.showAndWait();
        if (optButton.isEmpty() || optButton.get() == ButtonType.NO) return;

        ObservableList<String> names = lstNames.getItems();
        names.remove(lstNames.getSelectionModel().getSelectedIndex());

        lstNames.getSelectionModel().clearSelection();
        txtInput.requestFocus();
    }

    public void lstNamesOnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            btnRemove.fire();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        btnSave.setDisable(false);

        txtId.clear();
        txtName.clear();
        txtAddress.clear();

        txtId.getStyleClass().remove("invalid");
        txtName.getStyleClass().remove("invalid");
        txtAddress.getStyleClass().remove("invalid");

        lstStudents.getSelectionModel().clearSelection();

        txtId.requestFocus();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        txtId.getStyleClass().remove("invalid");
        txtName.getStyleClass().remove("invalid");
        txtAddress.getStyleClass().remove("invalid");

        // Data Validation
        boolean isDataValid = true;

        if (address.isBlank() || address.length() < 3){
            txtAddress.getStyleClass().add("invalid");
            txtAddress.selectAll();
            txtAddress.requestFocus();
            isDataValid = false;
        }

        if (name.isBlank()){
            txtName.getStyleClass().add("invalid");
            txtName.selectAll();
            txtName.requestFocus();
            isDataValid = false;
        }

        if (id.isBlank() || id.charAt(0) != 'S' ||
                id.substring(1).length() < 3 ||
                !isNumber(id.substring(1))){
            txtId.getStyleClass().add("invalid");
            txtId.selectAll();
            txtId.requestFocus();
            isDataValid = false;
        }

        if (!isDataValid) return;

        ObservableList<Student> studentList = lstStudents.getItems();

        if (lstStudents.getSelectionModel().getSelectedItem() == null) {        // Add
            // Business Validation
            for (Student student : studentList) {
                if (student.id.equals(id)) {
                    txtId.getStyleClass().add("invalid");
                    txtId.selectAll();
                    txtId.requestFocus();
                    return;
                }
            }
            Student student = new Student(id, name, address);
            studentList.add(student);
        }else{  // Update
            Student selectedStudent = lstStudents.getSelectionModel().getSelectedItem();
            selectedStudent.name = name;
            selectedStudent.address = address;
        }

        btnNew.fire();

    }

    private boolean isNumber(String input){
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Student selectedStudent = lstStudents.getSelectionModel().getSelectedItem();
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,
                String.format("Are you sure to delete the Student Id:%s ?", selectedStudent.id),
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optButton = confirmAlert.showAndWait();
        if (optButton.isEmpty() || optButton.get() == ButtonType.NO) return;

        lstStudents.getItems().remove(selectedStudent);
        btnNew.fire();
    }


    public void txtOnAction(ActionEvent actionEvent) {
        btnSave.fire();
    }

    public void lstStudentsOnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE){
            btnDelete.fire();
        }
    }
}
