package com.foodnyang.customer.ordering;

import com.foodnyang.FlowController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerCheckoutController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("MenuList");
        FlowController.removeScene("CheckoutOrder");
    }
}