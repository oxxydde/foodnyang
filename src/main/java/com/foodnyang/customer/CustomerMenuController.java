package com.foodnyang.customer;

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

public class CustomerMenuController implements Initializable {

    @FXML
    private Text greetTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greetTxt.setText("Halo, " + ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getNama().split(" ")[0]);
    }

    public void onPesanMakananClicked() throws IOException {
        if (FlowController.getStageByKey("RestaurantList") == null) {
            FlowController.setStage("MainStage");
            FlowController.createScene("RestaurantList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_restaurant_list.fxml"))));
            FlowController.setScene("RestaurantList");
        } else {
            FlowController.getStageByKey("RestaurantList").requestFocus();
        }
    }
    public void onOrderListClicked() throws IOException {
        FlowController.createScene("CustomerOrderList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/order_list/customer_orderlist.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerOrderList");
    }
    public void onSignOutBtnClicked() throws IOException {
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.createScene("LoginScene", new Scene(FXMLLoader.load(getClass().getResource("/com/foodnyang/login/login_view.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);
        FlowController.removeScene("CustomerMenu");
    }
    public void onEditProfileClicked() throws IOException {
        FlowController.createScene("EditProfile", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/profile/customer_edit_profile.fxml"))));
        FlowController.setStage("MainStage");
        FlowController.setScene("EditProfile");
    }
}
