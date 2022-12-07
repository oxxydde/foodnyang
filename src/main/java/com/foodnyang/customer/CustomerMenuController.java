package com.foodnyang.customer;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class CustomerMenuController {

    public void onPesanMakananClicked() throws IOException {
        if (FlowController.getStageByKey("RestaurantList") == null) {
            FlowController.setStage("MainStage");
            FlowController.createScene("RestaurantList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_restaurant_list.fxml"))));
            FlowController.setScene("RestaurantList");
        } else {
            FlowController.getStageByKey("RestaurantList").requestFocus();
        }
    }

    public void onSignOutBtnClicked() throws IOException {
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.createScene("LoginScene", new Scene(FXMLLoader.load(getClass().getResource("/com/foodnyang/login/login_view.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);
        FlowController.removeScene("CustomerMenu");
    }

    public void onOrderListClicked() throws IOException {
        FlowController.createScene("CustomerOrderList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/order_list/customer_orderlist.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerOrderList");
    }
}
