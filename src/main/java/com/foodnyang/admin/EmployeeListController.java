package com.foodnyang.admin;

import com.foodnyang.FlowController;
import com.foodnyang.database.admin.employee.Employee;
import com.foodnyang.database.admin.employee.EmployeeListModel;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.lang.invoke.StringConcatException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idPegawai;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, String> phone_num;
    @FXML
    private TableColumn<Employee, String> birthPlace;
    @FXML
    private TableColumn<Employee, String> birthDate;
    @FXML
    private TableColumn<Employee, String> gender;
    @FXML
    private TableColumn<Employee, String> tanggalPerekrutan;
    @FXML
    private TableColumn<Employee, String> pekerjaan;
    @FXML
    private TableColumn<Employee, String> departemen;
    @FXML
    private TableColumn<Employee, Integer> salary;
    @FXML
    private TextField txtIdPegawai;
    @FXML
    private TextField txtPekerjaan;
    @FXML
    private TextField txtDepartemen;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtSearchEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPegawai.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("idPegawai"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phone_num.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone_num"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthPlace"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthDate"));
        gender.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
        tanggalPerekrutan.setCellValueFactory(new PropertyValueFactory<Employee, String>("tanggalPerekrutan"));
        pekerjaan.setCellValueFactory(new PropertyValueFactory<Employee, String>("pekerjaan"));
        departemen.setCellValueFactory(new PropertyValueFactory<Employee, String>("departemen"));
        salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));

        //Add data to columns (ide dari James_D yang dibagikan pada laman StackOverFlow,
        //berikut adalah link untuk menuju laman tersebut: https://stackoverflow.com/questions/31318713/simple-way-to-add-data-to-columns)

        //Employee Lists Query
        EmployeeListModel model = new EmployeeListModel();
        ObservableList employeeList = model.getEmployeeList(txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText());
        employeeTable.setItems(employeeList);

        //Calling a function that use to fill the text field with the value within tableview
        setCellValueFromTableToTextField();
    }

    public void setCellValueFromTableToTextField() {
        employeeTable.setOnMouseClicked(mouseEvent -> {
            Employee employee = employeeTable.getItems().get(employeeTable.getSelectionModel().getSelectedIndex());
            txtIdPegawai.setText(String.valueOf(employee.getIdPegawai()));
            txtPekerjaan.setText(employee.getPekerjaan());
            txtDepartemen.setText(employee.getDepartemen());
            txtSalary.setText(String.valueOf(employee.getSalary()));

        });
    }
    public void onAddBtnClicked() {
        EmployeeListModel model = new EmployeeListModel();
        model.addEmployee(Integer.parseInt(txtIdPegawai.getText()), txtPekerjaan.getText(), 0, txtDepartemen.getText(), Integer.parseInt(txtSalary.getText()));
        employeeTable.refresh();
        employeeTable.setItems(employeeTable.getItems());
    }
    public void onSearchBtnClicked() {
        System.out.println(txtSearchEmployee.getText());
        //Employee Lists Query
        EmployeeListModel model = new EmployeeListModel();
        ObservableList employeeList = model.getEmployeeList(txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText(), txtSearchEmployee.getText());
        employeeTable.refresh();
        employeeTable.setItems(employeeList);
    }
    public void onEditBtnClicked() {
        EmployeeListModel model = new EmployeeListModel();
        model.editSelectedEmployee(Integer.parseInt(txtIdPegawai.getText()), txtPekerjaan.getText(), txtDepartemen.getText(), Integer.parseInt(txtSalary.getText()));
        employeeTable.refresh();
        employeeTable.setItems(employeeTable.getItems());
    }
    public void onDeleteBtnClicked() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            employeeTable.getItems().remove(selectedEmployee);
            EmployeeListModel.deleteSelectedEmployee(selectedEmployee.getIdPegawai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("AdminMenu");
    }

}
