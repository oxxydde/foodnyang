package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.driver.order.DriverOrderListModel;
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
import java.util.ResourceBundle;

public class DriverOrderListController implements Initializable {
    @FXML
    private TabPane statusTab;
    @FXML
    private TableView orderTable;
    @FXML
    private TextField searchTxtField;

    private ObservableList getOrderList(int driver_id, int orderIdFilter, String restoNameFilter, String custNameFilter, String addressFilter, String status) {
        DriverOrderListModel model = new DriverOrderListModel();
        return model.getOrderList(driver_id, orderIdFilter, restoNameFilter, custNameFilter, addressFilter, status);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AnchorPane tableViewRoot = FXMLLoader.load(MainApp.class.getResource("driver/order/driver_orderlist_tableview.fxml"));
            orderTable = (TableView) tableViewRoot.lookup("#orderTable");
            ( (TableColumn) orderTable.getColumns().get(0) ).setCellValueFactory(new PropertyValueFactory<>("id"));
            ( (TableColumn) orderTable.getColumns().get(1) ).setCellValueFactory(new PropertyValueFactory<>("pelanggan"));
            ( (TableColumn) orderTable.getColumns().get(2) ).setCellValueFactory(new PropertyValueFactory<>("restoran"));
            ( (TableColumn) orderTable.getColumns().get(3) ).setCellValueFactory(new PropertyValueFactory<>("address"));
            ( (TableColumn) orderTable.getColumns().get(4) ).setCellValueFactory(new PropertyValueFactory<>("status"));
            ( (TableColumn) orderTable.getColumns().get(5) ).setCellValueFactory(new PropertyValueFactory<>("harga"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        statusTab.getSelectionModel().selectedItemProperty().addListener(observable -> {
            try {
                AnchorPane tableViewRoot = FXMLLoader.load(MainApp.class.getResource("driver/order/driver_orderlist_tableview.fxml"));
                orderTable = (TableView) tableViewRoot.lookup("#orderTable");
                ( (TableColumn) orderTable.getColumns().get(0) ).setCellValueFactory(new PropertyValueFactory<>("id"));
                ( (TableColumn) orderTable.getColumns().get(1) ).setCellValueFactory(new PropertyValueFactory<>("pelanggan"));
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

    public void onSearchTxtTyped() {
        System.out.println(searchTxtField.getText());
        // Query order lists
        int orderIdInput = -1;
        try {
            orderIdInput = Integer.parseInt(searchTxtField.getText());
        } catch (NumberFormatException e) {
            orderIdInput = 0;
        }
        DriverOrderListModel model = new DriverOrderListModel();
        ObservableList orderList = model.getOrderList(
                ( (AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData() ).getId(),
                orderIdInput,
                searchTxtField.getText(),
                searchTxtField.getText(),
                searchTxtField.getText(),
                statusTab.getSelectionModel().selectedItemProperty().getValue().getText()
        );
        orderTable.setItems(orderList);
    }
    public void onOrderDetailBtnClicked() throws IOException {
        DriverOrderElement selectedDriverOrderElement = (DriverOrderElement) orderTable.getSelectionModel().selectedItemProperty().getValue();
        if (selectedDriverOrderElement != null) {
            FlowController.createStage("driverOrderDetail", new Stage());
            FlowController.setStage("driverOrderDetail");
            FlowController.getStage().setUserData(selectedDriverOrderElement);
            FlowController.getStage().setTitle("Order detail #" + selectedDriverOrderElement.getId());
            FlowController.createScene("driverOrderDetail", new Scene(new FXMLLoader(MainApp.class.getResource("driver/order/driver_orderdetail.fxml")).load()));
            FlowController.setStage("driverOrderDetail");
            FlowController.setScene("driverOrderDetail");
            FlowController.getStage().show();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("DriverMenu");
    }
}

