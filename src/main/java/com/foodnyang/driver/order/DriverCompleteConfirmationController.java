package com.foodnyang.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.driver.order.DriverUpdateOrderModel;
import com.foodnyang.enums.data.DataUpdate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;

public class DriverCompleteConfirmationController {
    public void onYesClicked() {
        System.out.println("Yes clicked!");
        try {
            int orderId = ((DriverOrderElement) FlowController.getStageByKey("driverOrderDetail").getUserData()).getId();
            DataUpdate result = DriverUpdateOrderModel.updateStatusPesanan(
                    orderId,
                    "Selesai"
            );
            switch (result) {
                case UPDATE_SUCCEED -> {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setContentText(String.format("Pesanan ID %d telah diselesaikan.", orderId));
                    successAlert.show();

                    // tutup window order detail yang telah diubah
                    FlowController.getStageByKey("driverOrderDetail").close();
                    FlowController.removeStage("driverOrderDetail");
                    FlowController.removeScene("driverOrderDetail");
                    FlowController.getStageByKey("driverOrderCompleteConfirm").close();
                    FlowController.removeStage("driverOrderCompleteConfirm");
                    FlowController.removeScene("driverOrderCompleteConfirm");

                    // refresh scene
                    try {
                        FlowController.createScene("driverOrderAktif", new Scene(FXMLLoader.load(MainApp.class.getResource("driver/order/driver_orderlist.fxml"))));
                        FlowController.setStage("MainStage");
                        FlowController.setScene("driverOrderAktif");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                default -> {
                    Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                    failedAlert.setContentText(String.format("Pesanan ID %d mengalami gagal perubahan.", orderId));
                    failedAlert.show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void onNoClicked() {
        System.out.println("No clicked!");
        FlowController.setStage("driverOrderCompleteConfirm");
        FlowController.getStage().close();
        FlowController.removeScene("driverOrderCompleteConfirm");
        FlowController.removeStage("driverOrderCompleteConfirm");
    }
}
