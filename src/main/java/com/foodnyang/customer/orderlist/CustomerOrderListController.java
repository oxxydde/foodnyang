package com.foodnyang.customer.orderlist;

import com.foodnyang.FlowController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerOrderListController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onOrderDetailBtnClicked() {

    }

    public void onSearchTxtTyped() {
        System.out.println();
    }

    public void onAddBtnClicked() {

    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerMenu");
        FlowController.removeScene("CustomerOrderList");
    }
}
