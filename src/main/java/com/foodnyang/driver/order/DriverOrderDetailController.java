package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverOrderDetailController implements Initializable {
    @FXML
    private Text orderID, pelanggan, restoran, harga, address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = FlowController.getStage();
        OrderElement orderElementObj = (OrderElement) stage.getUserData();
        orderID.setText(Integer.toString(orderElementObj.getId()));
        pelanggan.setText(orderElementObj.getPelanggan());
        restoran.setText(orderElementObj.getRestoran());
        address.setText(orderElementObj.getAddress());
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