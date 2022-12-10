package com.foodnyang.admin;

import com.foodnyang.FlowController;
import com.foodnyang.database.admin.customer.Customer;
import com.foodnyang.database.admin.customer.CustomerListModel;
import com.foodnyang.database.admin.driver.Driver;
import com.foodnyang.database.admin.driver.DriverListModel;
import com.foodnyang.database.admin.employee.EmployeeListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerListController implements Initializable {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> idCustomer;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, String> phone_num;
    @FXML
    private TableColumn<Customer, String> birthPlace;
    @FXML
    private TableColumn<Customer, String> birthDate;
    @FXML
    private TableColumn<Customer, String> gender;
    @FXML
    private TableColumn<Customer, String> jenisMembership;
    @FXML
    private TextField txtIdCustomer;
    @FXML
    private TextField txtJenisMembership;
    @FXML
    private TextField txtSearchCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCustomer.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("idCustomer"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phone_num.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone_num"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<Customer, String>("birthPlace"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("birthDate"));
        gender.setCellValueFactory(new PropertyValueFactory<Customer, String>("gender"));
        jenisMembership.setCellValueFactory(new PropertyValueFactory<Customer, String>("jenisMembership"));

        //Add data to columns (ide dari James_D yang dibagikan pada laman StackOverFlow,
        //berikut adalah link untuk menuju laman tersebut: https://stackoverflow.com/questions/31318713/simple-way-to-add-data-to-columns)

        //Driver Lists Query
        CustomerListModel model = new CustomerListModel();
        ObservableList customerList = model.getCustomerList(txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText());
        customerTable.setItems(customerList);

        //Calling a function that use to fill the text field with the value within tableview
        setCellValueFromTableToTextField();
    }
    public void setCellValueFromTableToTextField() {
        customerTable.setOnMouseClicked(mouseEvent -> {
            Customer customer = customerTable.getItems().get(customerTable.getSelectionModel().getSelectedIndex());
            txtIdCustomer.setText(String.valueOf(customer.getIdCustomer()));
            txtJenisMembership.setText(customer.getJenisMembership());

        });
    }
    public void onAddBtnClicked() {
        CustomerListModel model = new CustomerListModel();
        model.addCustomer(Integer.parseInt(txtIdCustomer.getText()), txtJenisMembership.getText());
        customerTable.refresh();
        customerTable.setItems(customerTable.getItems());
    }
    public void onEditBtnClicked() {
        CustomerListModel model = new CustomerListModel();
        model.editSelectedCustomer(Integer.parseInt(txtIdCustomer.getText()), txtJenisMembership.getText());
        customerTable.refresh();
        customerTable.setItems(customerTable.getItems());
    }
    public void onSearchBtnClicked() {
        System.out.println(txtSearchCustomer.getText());
        //Employee Lists Query
        CustomerListModel model = new CustomerListModel();
        ObservableList driverList = model.getCustomerList(txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText(), txtSearchCustomer.getText());
        customerTable.refresh();
        customerTable.setItems(driverList);
    }
    public void onDeleteBtnClicked() {
        try {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            customerTable.getItems().remove(selectedCustomer);
            CustomerListModel.deleteSelectedCustomer(selectedCustomer.getIdCustomer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("AdminMenu");
    }
}
