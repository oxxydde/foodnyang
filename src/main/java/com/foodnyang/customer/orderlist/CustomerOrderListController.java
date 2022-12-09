package com.foodnyang.customer.orderlist;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.customer.orderlist.CustomerOrderListModel;
import com.foodnyang.login.AccountInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerOrderListController implements Initializable {
    @FXML
    private TabPane statusTab;
    @FXML
    private TableView orderTable;
    @FXML
    private TextField searchTxtField;

    private ObservableList getOrderList(int customerId, int orderIdFilter, String restoNameFilter, String custNameFilter, String addressFilter, String status) {
        try {
            return CustomerOrderListModel.getOrders(customerId, orderIdFilter, restoNameFilter, custNameFilter, addressFilter, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane tableViewRoot = FXMLLoader.load(MainApp.class.getResource("driver/order/driver_orderlist_tableview.fxml"));
            orderTable = (TableView) tableViewRoot.lookup("#orderTable");
            ( (TableColumn) orderTable.getColumns().get(0) ).setCellValueFactory(new PropertyValueFactory<>("id"));
            ( (TableColumn) orderTable.getColumns().get(1) ).setCellValueFactory(new PropertyValueFactory<>("driver"));
            ( (TableColumn) orderTable.getColumns().get(2) ).setCellValueFactory(new PropertyValueFactory<>("restoran"));
            ( (TableColumn) orderTable.getColumns().get(3) ).setCellValueFactory(new PropertyValueFactory<>("address"));
            ( (TableColumn) orderTable.getColumns().get(4) ).setCellValueFactory(new PropertyValueFactory<>("status"));
            ( (TableColumn) orderTable.getColumns().get(5) ).setCellValueFactory(new PropertyValueFactory<>("harga"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        statusTab.getSelectionModel().selectedItemProperty().addListener(observable -> {
            try {
                AnchorPane tableViewRoot = FXMLLoader.load(MainApp.class.getResource("customer/order_list/customer_orderlist_tableview.fxml"));
                orderTable = (TableView) tableViewRoot.lookup("#orderTable");
                ( (TableColumn) orderTable.getColumns().get(0) ).setCellValueFactory(new PropertyValueFactory<>("id"));
                ( (TableColumn) orderTable.getColumns().get(1) ).setCellValueFactory(new PropertyValueFactory<>("driver"));
                ( (TableColumn) orderTable.getColumns().get(2) ).setCellValueFactory(new PropertyValueFactory<>("restoran"));
                ( (TableColumn) orderTable.getColumns().get(3) ).setCellValueFactory(new PropertyValueFactory<>("address"));
                ( (TableColumn) orderTable.getColumns().get(4) ).setCellValueFactory(new PropertyValueFactory<>("status"));
                ( (TableColumn) orderTable.getColumns().get(5) ).setCellValueFactory(new PropertyValueFactory<>("harga"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String status = statusTab.getSelectionModel().selectedItemProperty().getValue().getText();
            System.out.println("changed to " + status);

            if (status.equalsIgnoreCase("Aktif")) {
                // set aktif
                int orderIdInput = -1;
                try {
                    orderIdInput = Integer.parseInt(searchTxtField.getText());
                } catch (NumberFormatException e) {
                    orderIdInput = 0;
                }
                ObservableList orderList = this.getOrderList(
                        ( (AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData() ).getId(),
                        orderIdInput,
                        searchTxtField.getText(),
                        searchTxtField.getText(),
                        searchTxtField.getText(),
                        "Aktif"
                );
                orderTable.setItems(orderList);
                statusTab.getTabs().get(0).setContent(orderTable);
            }
            else if (status.equalsIgnoreCase("Selesai")) {
                // set selesai
                int orderIdInput = -1;
                try {
                    orderIdInput = Integer.parseInt(searchTxtField.getText());
                } catch (NumberFormatException e) {
                    orderIdInput = 0;
                }
                ObservableList orderList = this.getOrderList(
                        ( (AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData() ).getId(),
                        orderIdInput,
                        searchTxtField.getText(),
                        searchTxtField.getText(),
                        searchTxtField.getText(),
                        "Selesai"
                );
                orderTable.setItems(orderList);
                statusTab.getTabs().get(1).setContent(orderTable);
            }
        });

        // Query order lists
        int orderIdInput = -1;
        try {
            orderIdInput = Integer.parseInt(searchTxtField.getText());
        } catch (NumberFormatException e) {
            orderIdInput = 0;
        }
        ObservableList orderList = this.getOrderList(
                ( (AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData() ).getId(),
                orderIdInput,
                searchTxtField.getText(),
                searchTxtField.getText(),
                searchTxtField.getText(),
                "Aktif"
        );
        orderTable.setItems(orderList);

        // set aktif
        statusTab.getTabs().get(0).setContent(orderTable);
    }

    public void onOrderDetailBtnClicked() throws IOException {
        CustomerOrderElement selectedCustomerOrderElement = (CustomerOrderElement) orderTable.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCustomerOrderElement != null) {
            if (FlowController.getStageByKey("customerOrderDetail") == null) {
                FlowController.createStage("customerOrderDetail", new Stage());
                FlowController.setStage("customerOrderDetail");
            }
            FlowController.getStage().setTitle("Order detail #" + selectedCustomerOrderElement.getId());
            FlowController.getStage().setUserData(selectedCustomerOrderElement);
            FlowController.createScene("customerOrderDetail", new Scene(new FXMLLoader(MainApp.class.getResource("customer/order_list/customer_orderdetail.fxml")).load()));
            FlowController.setStage("customerOrderDetail");
            FlowController.setScene("customerOrderDetail");
            FlowController.getStage().show();
        } else {
            Alert failedAlert = new Alert(Alert.AlertType.ERROR);
            failedAlert.setContentText("Anda tidak memilih pesanan yang akan ditampilkan.");
            failedAlert.show();
        }
    }

    public void onSearchTxtTyped() {
        System.out.println(searchTxtField.getText());
        // Query order lists
        int orderIdInput = -1;
        try {
            orderIdInput = Integer.parseInt(searchTxtField.getText());
        } catch (NumberFormatException e) {
            orderIdInput = 0;
        }
        ObservableList<CustomerOrderElement> orderList = null;
        try {
            orderList = CustomerOrderListModel.getOrders(
                    ( (AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData() ).getId(),
                    orderIdInput,
                    searchTxtField.getText(),
                    searchTxtField.getText(),
                    searchTxtField.getText(),
                    statusTab.getSelectionModel().selectedItemProperty().getValue().getText()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderTable.setItems(orderList);
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerMenu");
        FlowController.removeScene("CustomerOrderList");
    }
}
