package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.last.model.Employee;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TableViewSceneController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<Employee> tblEmployees;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    public void initialize() {
        /* Column Mapping */
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        Employee e001 = new Employee("E001", "Kasun Sampath", "Galle Road, Panadura");
        Employee e002 = new Employee("E002", "Nuwan Sampath", "Galle Road, Kalutara");
        Employee e003 = new Employee("E003", "Ruwan Sampath", "Galle Road, Moratuwa");
        Employee e004 = new Employee("E004", "Nimal Sampath", "Galle Road, Dehiwala");

        ObservableList<Employee> employeeList = tblEmployees.getItems();
        employeeList.addAll(e001, e002, e003, e004);

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        tblEmployees.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);
            if (current == null) return;

            txtId.setText(current.getId());
            txtName.setText(current.getName());
            txtAddress.setText(current.getAddress());
        });
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure to delete this employee?",
                ButtonType.YES, ButtonType.NO).showAndWait();

        if (optButton.isEmpty() || optButton.get() == ButtonType.NO) return;

        ObservableList<Employee> employeeList = tblEmployees.getItems();
        Employee selectedEmployee = tblEmployees.getSelectionModel().getSelectedItem();
        employeeList.remove(selectedEmployee);
        btnNew.fire();
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        btnSave.setDisable(false);

        txtId.clear();
        txtName.clear();
        txtAddress.clear();

        txtId.requestFocus();
        tblEmployees.getSelectionModel().clearSelection();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText().strip();
        String name = txtName.getText().strip();
        String address = txtAddress.getText().strip();

        /* Data Validation */
        for (TextField txt : new TextField[]{txtId, txtName, txtAddress}) {
            if (txt.getText().isBlank()){
                txt.selectAll();
                txt.requestFocus();
                return;
            }
        }

        /* Business Validation */
        for (Employee employee : tblEmployees.getItems()) {
            if (employee.getId().equals(id)){
                txtId.selectAll();
                txtId.requestFocus();
                return;
            }
        }

        Employee selectedEmployee = tblEmployees.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null){      // Add
            Employee employee = new Employee(id, name, address);
            tblEmployees.getItems().add(employee);
        }else{      // Update
            selectedEmployee.setId(id);
            selectedEmployee.setName(name);
            selectedEmployee.setAddress(address);
        }
        btnNew.fire();
    }

}
