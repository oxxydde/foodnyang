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
    private Text orderID, pelanggan, restoran, harga;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = FlowController.getStage();
        Order orderObj = (Order) stage.getUserData();
        orderID.setText(Integer.toString(orderObj.getId()));
        pelanggan.setText(orderObj.getPelanggan());
        restoran.setText(orderObj.getRestoran());
    }

    public void onSelesaikanPesananClicked() throws IOException {
        FlowController.createStage("driverOrderCompleteConfirm", new Stage());
        FlowController.createScene("driverOrderCompleteConfirm", new Scene(new FXMLLoader(MainApp.class.getResource("driver/order/driver_ordercompleteconfirmation.fxml")).load()));

        FlowController.setStage("driverOrderCompleteConfirm");
        FlowController.getStage().setTitle("Konfirmasi Pesanan");
        FlowController.setScene("driverOrderCompleteConfirm");
        FlowController.showStage("driverOrderCompleteConfirm");
    }
}