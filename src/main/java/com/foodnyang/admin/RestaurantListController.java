package com.foodnyang.admin;

import com.foodnyang.FlowController;
import com.foodnyang.database.admin.customer.Customer;
import com.foodnyang.database.admin.customer.CustomerListModel;
import com.foodnyang.database.admin.employee.EmployeeListModel;
import com.foodnyang.database.admin.restaurant.Restaurant;
import com.foodnyang.database.admin.restaurant.RestaurantListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RestaurantListController implements Initializable {
    @FXML
    private TableView<Restaurant> restoTable;
    @FXML
    private TableColumn<Restaurant, Integer> idResto;
    @FXML
    private TableColumn<Restaurant, Integer> idMitra;
    @FXML
    private TableColumn<Restaurant, String> namaResto;
    @FXML
    private TableColumn<Restaurant, String> namaOwner;
    @FXML
    private TableColumn<Restaurant, String> tipeResto;
    @FXML
    private TableColumn<Restaurant, String> alamatResto;
    @FXML
    private TableColumn<Restaurant, Integer> rating;
    @FXML
    private TextField txtIdMitra;
    @FXML
    private TextField txtNamaResto;
    @FXML
    private TextField txtTipeResto;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtSearchResto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idResto.setCellValueFactory(new PropertyValueFactory<Restaurant, Integer>("idResto"));
        idMitra.setCellValueFactory(new PropertyValueFactory<Restaurant, Integer>("idMitra"));
        namaResto.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("namaResto"));
        namaOwner.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("namaOwner"));
        tipeResto.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("tipeResto"));
        alamatResto.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("alamat"));
        rating.setCellValueFactory(new PropertyValueFactory<Restaurant, Integer>("rating"));

        //Add data to columns (ide dari James_D yang dibagikan pada laman StackOverFlow,
        //berikut adalah link untuk menuju laman tersebut: https://stackoverflow.com/questions/31318713/simple-way-to-add-data-to-columns)

        //Driver Lists Query
        RestaurantListModel model = new RestaurantListModel();
        ObservableList restoList = model.getRestoList(txtSearchResto.getText(), txtSearchResto.getText(), txtSearchResto.getText(), txtSearchResto.getText());
        restoTable.setItems(restoList);

        //Calling a function that use to fill the text field with the value within tableview
        setCellValueFromTableToTextField();
    }
    public void setCellValueFromTableToTextField() {
        restoTable.setOnMouseClicked(mouseEvent -> {
            Restaurant restaurant = restoTable.getItems().get(restoTable.getSelectionModel().getSelectedIndex());
            txtNamaResto.setText(restaurant.getNamaResto());
            txtIdMitra.setText(String.valueOf(restaurant.getIdMitra()));
            txtTipeResto.setText(restaurant.getTipeResto());
            txtAlamat.setText(restaurant.getAlamat());
        });
    }
    public void onAddBtnClicked() {
        RestaurantListModel model = new RestaurantListModel();
        model.addRestaurant(Integer.parseInt(txtIdMitra.getText()), txtNamaResto.getText(), txtTipeResto.getText(), txtAlamat.getText());
        restoTable.refresh();
        restoTable.setItems(restoTable.getItems());
    }
    public void onSearchBtnClicked() {
        System.out.println(txtSearchResto.getText());
        //Employee Lists Query
        RestaurantListModel model = new RestaurantListModel();
        ObservableList restoList = model.getRestoList(txtSearchResto.getText(), txtSearchResto.getText(), txtSearchResto.getText(), txtSearchResto.getText());
        restoTable.refresh();
        restoTable.setItems(restoList);
    }
    public void onEditBtnClicked() {
        RestaurantListModel model = new RestaurantListModel();
        model.editSelectedRestaurant(Integer.parseInt(txtIdMitra.getText()), txtNamaResto.getText(), txtTipeResto.getText(), txtAlamat.getText());
        restoTable.refresh();
        restoTable.setItems(restoTable.getItems());
    }
    public void onDeleteBtnClicked() {
        try {
            Restaurant selectedRestaurant = restoTable.getSelectionModel().getSelectedItem();
            restoTable.getItems().remove(selectedRestaurant);
            RestaurantListModel.deleteSelectedRestaurant(selectedRestaurant.getIdResto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("AdminMenu");
    }
}
