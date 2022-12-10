package com.foodnyang.restaurant;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RestoMenuController {
    @FXML
    private Parent root;

    @FXML
    public void onCekMenuAndStockBtnClicked() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/restaurant/food_and_stock_list.fxml"));
        FlowController.createScene("MenuList", new Scene(root));
        FlowController.setStage("MainStage");
        FlowController.setScene("MenuList");
    }

    @FXML
    public void onSignOutBtnClicked() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/login/login_view.fxml"));
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.createScene("LoginScene", new Scene(root));
        FlowController.setStage("MainStage");
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);

    }
}
