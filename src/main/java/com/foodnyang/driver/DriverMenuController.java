package com.foodnyang.driver;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.login.AccountInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverMenuController implements Initializable {
    @FXML
    private Text greetTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greetTxt.setText("Halo, " + ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getNama().split(" ")[0]);
    }
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
