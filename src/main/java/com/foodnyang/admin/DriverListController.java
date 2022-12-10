package com.foodnyang.admin;

import com.foodnyang.FlowController;
import com.foodnyang.database.admin.driver.Driver;
import com.foodnyang.database.admin.driver.DriverListModel;
import com.foodnyang.database.admin.employee.Employee;
import com.foodnyang.database.admin.employee.EmployeeListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DriverListController implements Initializable{
    @FXML
    private TableView<Driver> driverTable;
    @FXML
    private TableColumn<Driver, Integer> idDriver;
    @FXML
    private TableColumn<Driver, String> nama;
    @FXML
    private TableColumn<Driver, String> email;
    @FXML
    private TableColumn<Driver, String> phone_num;
    @FXML
    private TableColumn<Driver, String> birthPlace;
    @FXML
    private TableColumn<Driver, String> birthDate;
    @FXML
    private TableColumn<Driver, String> gender;
    @FXML
    private TableColumn<Driver, String> tanggalBergabung;
    @FXML
    private TableColumn<Driver, String> nomorPolisi;
    @FXML
    private TableColumn<Driver, String> tipeKendaraan;
    @FXML
    private TableColumn<Driver, String> warnaKendaraan;
    @FXML
    private TextField txtIdDriver;
    @FXML
    private TextField txtTipeKendaraan;
    @FXML
    private TextField txtNomorPolisi;
    @FXML
    private TextField txtWarnaKendaraan;
    @FXML
    private TextField txtSearchDriver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idDriver.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("idDriver"));
        nama.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Driver, String>("email"));
        phone_num.setCellValueFactory(new PropertyValueFactory<Driver, String>("phone_num"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<Driver, String>("birthPlace"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Driver, String>("birthDate"));
        gender.setCellValueFactory(new PropertyValueFactory<Driver, String>("gender"));
        tanggalBergabung.setCellValueFactory(new PropertyValueFactory<Driver, String>("tanggalBergabung"));
        tipeKendaraan.setCellValueFactory(new PropertyValueFactory<Driver, String>("jenisKendaraan"));
        warnaKendaraan.setCellValueFactory(new PropertyValueFactory<Driver, String>("warnaKendaraan"));
        nomorPolisi.setCellValueFactory(new PropertyValueFactory<Driver, String>("nomorPolisi"));

        //Add data to columns (ide dari James_D yang dibagikan pada laman StackOverFlow,
        //berikut adalah link untuk menuju laman tersebut: https://stackoverflow.com/questions/31318713/simple-way-to-add-data-to-columns)

        //Driver Lists Query
        DriverListModel model = new DriverListModel();
        ObservableList driverList = model.getDriverList(txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText());
        driverTable.setItems(driverList);

        //Calling a function that use to fill the text field with the value within tableview
        setCellValueFromTableToTextField();
    }

    public void setCellValueFromTableToTextField() {
        driverTable.setOnMouseClicked(mouseEvent -> {
            Driver driver = driverTable.getItems().get(driverTable.getSelectionModel().getSelectedIndex());
            txtIdDriver.setText(String.valueOf(driver.getIdDriver()));
            txtTipeKendaraan.setText(driver.getJenisKendaraan());
            txtWarnaKendaraan.setText(driver.getWarnaKendaraan());
            txtNomorPolisi.setText(driver.getNomorPolisi());

        });
    }
    public void onAddBtnClicked() {
        DriverListModel model = new DriverListModel();
        model.addDriver(Integer.parseInt(txtIdDriver.getText()), txtTipeKendaraan.getText(), txtNomorPolisi.getText(), txtWarnaKendaraan.getText());
        driverTable.refresh();
        driverTable.setItems(driverTable.getItems());
    }
    public void onEditBtnClicked() {
        DriverListModel model = new DriverListModel();
        model.editSelectedDriver(Integer.parseInt(txtIdDriver.getText()), txtTipeKendaraan.getText(), txtNomorPolisi.getText(), txtWarnaKendaraan.getText());
        driverTable.refresh();
        driverTable.setItems(driverTable.getItems());
    }
    public void onSearchBtnClicked() {
        System.out.println(txtSearchDriver.getText());
        //Employee Lists Query
        DriverListModel model = new DriverListModel();
        ObservableList driverList = model.getDriverList(txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText(), txtSearchDriver.getText());
        driverTable.refresh();
        driverTable.setItems(driverList);
    }
    public void onDeleteBtnClicked() {
        try {
            Driver selectedDriver = driverTable.getSelectionModel().getSelectedItem();
            driverTable.getItems().remove(selectedDriver);
            DriverListModel.deleteSelectedDriver(selectedDriver.getIdDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("AdminMenu");
    }
}
