package lk.ijse.dep10.last.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.last.model.Employee;

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
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

}
