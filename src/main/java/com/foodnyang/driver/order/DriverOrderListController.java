package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.driver.order.DriverOrderListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverOrderListController implements Initializable {
    @FXML
    private TableView orderTable;
    @FXML
    private TableColumn<Order, Integer> id;
    @FXML
    private TableColumn<Order, String> pelanggan;
    @FXML
    private TableColumn<Order, String> restoran;
    @FXML
    private TableColumn<Order, Integer> harga;
    @FXML
    private TextField searchTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        pelanggan.setCellValueFactory(new PropertyValueFactory<Order, String>("pelanggan"));
        restoran.setCellValueFactory(new PropertyValueFactory<Order, String>("restoran"));
        harga.setCellValueFactory(new PropertyValueFactory<Order, Integer>("harga"));

        // Add selection listener (Ide dari James_D pada StackOverflow : https://stackoverflow.com/questions/26424769/javafx8-how-to-create-listener-for-selection-of-row-in-tableview)
//        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
//            System.out.println(obs.getValue().toString());
//        });

        // Query order lists
        DriverOrderListModel model = new DriverOrderListModel();
        ObservableList orderList = model.getOrderList(1234, searchTxtField.getText());
        orderTable.setItems(orderList);
    }

    // JavaFX Mechanics Testing Purposes
    public void onAddBtnClicked() {
        orderTable.getItems().add(new Order(12345, "Ananda", "Lalapan Ja'i", 12000));
        System.out.println(orderTable.getItems());
    }

    public void onSearchTxtTyped() {
        System.out.println(searchTxtField.getText());
        // Query order lists
        DriverOrderListModel model = new DriverOrderListModel();
        ObservableList orderList = model.getOrderList(1234, searchTxtField.getText());
        orderTable.setItems(orderList);
    }
    public void onOrderDetailBtnClicked() throws IOException {
        Order selectedOrder = (Order) orderTable.getSelectionModel().selectedItemProperty().getValue();
        if (selectedOrder != null) {
            FlowController.createStage("driverOrderDetail", new Stage());
            FlowController.setStage("driverOrderDetail");
            FlowController.getStage().setUserData(selectedOrder);
            FlowController.getStage().setTitle("Order detail");
            FlowController.createScene("driverOrderDetail", new Scene(new FXMLLoader(MainApp.class.getResource("driver/order/driver_orderdetail.fxml")).load()));
            FlowController.setStage("driverOrderDetail");
            FlowController.setScene("driverOrderDetail");
            FlowController.showStage("driverOrderDetail");
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("DriverMenu");
    }
}

