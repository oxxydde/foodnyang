package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.driver.order.DriverOrderListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
                ObservableList orderList = this.getOrderList(17, orderIdInput, searchTxtField.getText(), searchTxtField.getText(), searchTxtField.getText(), "Aktif");
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
                ObservableList orderList = this.getOrderList(17, orderIdInput, searchTxtField.getText(), searchTxtField.getText(), searchTxtField.getText(), "Selesai");
                orderTable.setItems(orderList);
                statusTab.getTabs().get(1).setContent(orderTable);
            }
        });

        // Add selection listener (Ide dari James_D pada StackOverflow : https://stackoverflow.com/questions/26424769/javafx8-how-to-create-listener-for-selection-of-row-in-tableview)
//        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
//            System.out.println(obs.getValue().toString());
//        });

        // Query order lists
        int orderIdInput = -1;
        try {
            orderIdInput = Integer.parseInt(searchTxtField.getText());
        } catch (NumberFormatException e) {
            orderIdInput = 0;
        }
        ObservableList orderList = this.getOrderList(17, orderIdInput, searchTxtField.getText(), searchTxtField.getText(), searchTxtField.getText(), "Aktif");
        orderTable.setItems(orderList);

        // set aktif
        statusTab.getTabs().get(0).setContent(orderTable);
    }

    // JavaFX Mechanics Testing Purposes
    public void onAddBtnClicked() {
//        orderTable.getItems().add(new OrderElement(1234, "Ananda", "Lalapan Ja'i", "Jl. Anggrek", 12000));
//        System.out.println(orderTable.getItems());
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
                15,
                orderIdInput,
                searchTxtField.getText(),
                searchTxtField.getText(),
                searchTxtField.getText(),
                statusTab.getSelectionModel().selectedItemProperty().getValue().getText()
        );
        orderTable.setItems(orderList);
    }
    public void onOrderDetailBtnClicked() throws IOException {
        OrderElement selectedOrderElement = (OrderElement) orderTable.getSelectionModel().selectedItemProperty().getValue();
        if (selectedOrderElement != null) {
            FlowController.createStage("driverOrderDetail", new Stage());
            FlowController.setStage("driverOrderDetail");
            FlowController.getStage().setUserData(selectedOrderElement);
            FlowController.getStage().setTitle("Order detail");
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

