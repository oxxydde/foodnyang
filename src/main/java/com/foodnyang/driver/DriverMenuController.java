package com.foodnyang.driver;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class DriverMenuController {
    public void onPesananAktifClicked() throws IOException {
        System.out.println("Go to pesanan aktif");
        FXMLLoader orderAktifScene = new FXMLLoader(MainApp.class.getResource("driver/order/driver_orderlist.fxml"));
        FlowController.createScene("driverOrderAktif", new Scene(orderAktifScene.load()));
        FlowController.setScene("driverOrderAktif");
    }

    public void onKeuanganClicked() {

    }

    public void onSignOutBtnClicked() throws IOException {
        String css = MainApp.class.getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.createScene("LoginScene", new Scene(FXMLLoader.load(MainApp.class.getResource("/com/foodnyang/login/login_view.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);
        FlowController.removeScene("DriverMenu");
    }
}
