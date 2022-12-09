package com.foodnyang.customer.orderlist;

import com.foodnyang.FlowController;
import com.foodnyang.database.customer.orderlist.CustomerOrderDetailModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerOrderDetailController implements Initializable {
    @FXML
    private Text orderID, driver, restoran, totalPrice, address, status;

    @FXML
    private TableView tableItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerOrderElement customerObj = (CustomerOrderElement) FlowController.getStageByKey("customerOrderDetail").getUserData();
        orderID.setText(Integer.toString(customerObj.getId()));
        driver.setText(customerObj.getDriver());
        restoran.setText(customerObj.getRestoran());
        address.setText(customerObj.getAddress());
        status.setText(customerObj.getStatus());

        ( (TableColumn) tableItems.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ( (TableColumn) tableItems.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("qty"));
        ( (TableColumn) tableItems.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("subtotalPrice"));

        try {
            tableItems.setItems(CustomerOrderDetailModel.getOrderDetailById(customerObj.getId()));
            totalPrice.setText(String.format("Rp %,d", customerObj.getHarga()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onPrintClicked() {
        System.out.println("Print, now jasper here!");
    }
}
