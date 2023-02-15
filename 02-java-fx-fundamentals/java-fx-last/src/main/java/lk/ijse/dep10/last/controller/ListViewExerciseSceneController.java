package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lk.ijse.dep10.last.model.StudentInfo;
import lk.ijse.dep10.last.util.Gender;

import java.util.ArrayList;
import java.util.Optional;

public class ListViewExerciseSceneController {

    public Label lblGender;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnForward;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private ToggleGroup gender;

    @FXML
    private ListView<String> lstContacts;

    @FXML
    private ListView<String> lstModules;

    @FXML
    private ListView<String> lstSelectedModules;

    @FXML
    private ListView<StudentInfo> lstStudents;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private RadioButton rdoMale;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    public void initialize() {
        lstModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstSelectedModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        txtName.textProperty().addListener((value, previousText, currentText) -> {
            txtName.getStyleClass().remove("invalid");

            for (char c : currentText.toCharArray()) {
                if (!(Character.isLetter(c) || Character.isSpaceChar(c))) {
                    txtName.getStyleClass().add("invalid");
                    return;
                }
            }
        });

        txtContact.textProperty().addListener((value, previousContact, currentContact) -> {
            txtContact.getStyleClass().remove("invalid");
            if (currentContact.isEmpty()) {
                btnAdd.setDisable(true);
                return;
            }

            btnAdd.setDisable(false);
            currentContact = currentContact.strip();

            if (!(currentContact.length() == 11 &&
                    isNumber(currentContact.substring(0, 3)) &&
                    currentContact.charAt(3) == '-' &&
                    isNumber(currentContact.substring(4)))) {
                txtContact.getStyleClass().add("invalid");
                btnAdd.setDisable(true);
            }
        });

        lstContacts.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemove.setDisable(current == null);
        });

        lstModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnForward.setDisable(current == null);
        });

        lstSelectedModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnBack.setDisable(current == null);
        });

        lstStudents.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);
            if (current == null) return;

            txtId.setText(current.id);
            txtName.setText(current.name);
            if (current.gender == Gender.MALE){
                rdoMale.getToggleGroup().selectToggle(rdoMale);
            }else{
                rdoFemale.getToggleGroup().selectToggle(rdoFemale);
            }
            txtContact.clear();
            lstContacts.getItems().clear();
            lstContacts.getItems().addAll(current.contacts);
            lstSelectedModules.getItems().clear();
            lstSelectedModules.getItems().addAll(current.modules);

            lstModules.getItems().clear();
            lstModules.getItems().addAll("Object Oriented Programming", "Reactive Programming",
                    "Event-Driven Programming", "Aspect Oriented Programming", "Declarative Programming",
                    "Proto-type based OOP");
            lstModules.getItems().removeAll(current.modules);
        });
    }

    private boolean isNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        for (String contact : lstContacts.getItems()) {
            if (contact.equals(txtContact.getText().strip())) {
                txtContact.getStyleClass().add("invalid");
                return;
            }
        }

        lstContacts.getItems().add(txtContact.getText().strip());
        txtContact.clear();
        txtContact.requestFocus();
        lstContacts.getSelectionModel().clearSelection();
        lstContacts.getStyleClass().remove("invalid");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        ObservableList<String> selectedModules = lstSelectedModules.getItems();
        ObservableList<String> modules = lstModules.getItems();

        modules.addAll(lstSelectedModules.getSelectionModel().getSelectedItems());
        selectedModules.removeAll(lstSelectedModules.getSelectionModel().getSelectedItems());

        lstSelectedModules.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure to delete this student?",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (optButton.isEmpty() || optButton.get() ==  ButtonType.NO) return;

        lstStudents.getItems().remove(lstStudents.getSelectionModel().getSelectedItem());
        btnNewStudent.fire();
    }

    @FXML
    void btnForwardOnAction(ActionEvent event) {
        ObservableList<String> modules = lstModules.getItems();
        ObservableList<String> selectedModules = lstSelectedModules.getItems();

        selectedModules.addAll(lstModules.getSelectionModel().getSelectedItems());
        modules.removeAll(lstModules.getSelectionModel().getSelectedItems());

        lstModules.getSelectionModel().clearSelection();
        lstSelectedModules.getStyleClass().remove("invalid");
    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        lstStudents.getSelectionModel().clearSelection();

        txtId.setText(generateNewStudentId());
        lblGender.setTextFill(Color.BLACK);

        txtName.getStyleClass().remove("invalid");
        txtContact.getStyleClass().remove("invalid");
        lstContacts.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtContact.setDisable(false);
        rdoMale.setDisable(false);
        rdoFemale.setDisable(false);
        lstContacts.setDisable(false);
        lstModules.setDisable(false);
        lstSelectedModules.setDisable(false);
//        btnForward.setDisable(false);
        btnSave.setDisable(false);

        txtName.clear();
        txtContact.clear();
        lstContacts.getItems().clear();
        lstSelectedModules.getItems().clear();

        ObservableList<String> moduleList = lstModules.getItems();
        moduleList.clear();
        moduleList.addAll("Object Oriented Programming", "Reactive Programming",
                "Event-Driven Programming", "Aspect Oriented Programming", "Declarative Programming",
                "Proto-type based OOP");

        rdoMale.getToggleGroup().selectToggle(null);

        txtName.requestFocus();
    }

    private String generateNewStudentId() {
        ObservableList<StudentInfo> studentList = lstStudents.getItems();
        if (studentList.isEmpty()) return "S001";

        String lastStudentId = studentList.get(studentList.size() - 1).id;  // S005 - > 005 -> 5 + 1 -> 6
        var newId = Integer.parseInt(lastStudentId.substring(1)) + 1;

        return String.format("S%03d", newId);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        lstContacts.getItems().remove(lstContacts.getSelectionModel().getSelectedItem());
        lstContacts.getSelectionModel().clearSelection();
        txtContact.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isDataValid = true;
        lblGender.setTextFill(Color.BLACK);
        lstContacts.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");

        String name = txtName.getText();

        if (lstSelectedModules.getItems().size() < 2){
            isDataValid = false;
            lstSelectedModules.getStyleClass().add("invalid");
            lstModules.requestFocus();
        }

        if (lstContacts.getItems().isEmpty()){
            isDataValid = false;
            lstContacts.getStyleClass().add("invalid");
            txtContact.selectAll();
            txtContact.requestFocus();
        }

        if (rdoMale.getToggleGroup().getSelectedToggle() == null) {
            isDataValid = false;
            rdoMale.requestFocus();
            lblGender.setTextFill(Color.RED);
        }

        if (name.isBlank() || txtName.getStyleClass().contains("invalid")) {
            isDataValid = false;
            if (!txtName.getStyleClass().contains("invalid")) txtName.getStyleClass().add("invalid");
            txtName.selectAll();
            txtName.requestFocus();
        }

        if (!isDataValid) return;

        ObservableList<StudentInfo> studentList = lstStudents.getItems();

        /* Business Validation */
        StudentInfo selectedStudent = lstStudents.getSelectionModel().getSelectedItem();

        for (String enteredContact : lstContacts.getItems()) {
            for (StudentInfo student : studentList) {
                if (student == selectedStudent) continue;
                if (student.contacts.contains(enteredContact)){
                    new Alert(Alert.AlertType.ERROR,
                            String.format("Contact number: %s already exists", enteredContact)).showAndWait();
                    lstContacts.getStyleClass().add("invalid");
                    lstContacts.requestFocus();
                    return;
                }
            }
        }

        if (selectedStudent == null) {  // Add
            StudentInfo student = new StudentInfo(txtId.getText(),
                    txtName.getText().strip(),
                    rdoMale.isSelected() ? Gender.MALE : Gender.FEMALE,
                    new ArrayList<>(lstContacts.getItems()),
                    new ArrayList<>(lstSelectedModules.getItems()));
            studentList.add(student);
        }else{  // Update
            selectedStudent.name = txtName.getText().strip();
            selectedStudent.gender = rdoMale.isSelected() ? Gender.MALE : Gender.FEMALE;
            selectedStudent.contacts.clear();
            selectedStudent.contacts.addAll(new ArrayList<>(lstContacts.getItems()));
            selectedStudent.modules.clear();
            selectedStudent.modules.addAll(new ArrayList<>(lstSelectedModules.getItems()));
        }
        btnNewStudent.fire();
    }

    @FXML
    void lstContactsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnRemove.fire();
    }

    @FXML
    void lstModulesOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) btnForward.fire();
    }

    @FXML
    void lstSelectedModulesOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnBack.fire();
    }

    @FXML
    void lstStudentsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnDelete.fire();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        btnAdd.fire();
    }

}
