package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.driver.order.DriverOrderDetailModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DriverOrderDetailController implements Initializable {
    @FXML
    private Text orderID, pelanggan, restoran, totalPrice, address, status;

    @FXML
    private TableView tableItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = FlowController.getStage();
        DriverOrderElement driverOrderElementObj = (DriverOrderElement) stage.getUserData();
        orderID.setText(Integer.toString(driverOrderElementObj.getId()));
        pelanggan.setText(driverOrderElementObj.getPelanggan());
        restoran.setText(driverOrderElementObj.getRestoran());
        address.setText(driverOrderElementObj.getAddress());
        status.setText(driverOrderElementObj.getStatus());

        ( (TableColumn) tableItems.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ( (TableColumn) tableItems.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("qty"));
        ( (TableColumn) tableItems.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("subtotalPrice"));

        try {
            DriverOrderDetailModel model = new DriverOrderDetailModel();
            tableItems.setItems(model.getOrderDetailById(driverOrderElementObj.getId()));
            totalPrice.setText(String.format("Rp %,d", driverOrderElementObj.getHarga()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onTolakPesananClicked() throws IOException {
        FlowController.createStage("driverOrderRejectConfirm", new Stage());
        FlowController.createScene("driverOrderRejectConfirm", new Scene(new FXMLLoader(MainApp.class.getResource("driver/order/driver_orderrejectconfirmation.fxml")).load()));

        FlowController.setStage("driverOrderRejectConfirm");
        FlowController.getStage().setTitle("Konfirmasi Pesanan");
        FlowController.setScene("driverOrderRejectConfirm");
        FlowController.getStage().show();
    }


    public void onSelesaikanPesananClicked() throws IOException {
        FlowController.createStage("driverOrderCompleteConfirm", new Stage());
        FlowController.createScene("driverOrderCompleteConfirm", new Scene(new FXMLLoader(MainApp.class.getResource("driver/order/driver_ordercompleteconfirmation.fxml")).load()));

        FlowController.setStage("driverOrderCompleteConfirm");
        FlowController.getStage().setTitle("Konfirmasi Pesanan");
        FlowController.setScene("driverOrderCompleteConfirm");
        FlowController.getStage().show();
    }
}