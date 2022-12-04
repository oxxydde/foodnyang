package com.foodnyang.driver.order;

import com.foodnyang.FlowController;

public class DriverRejectConfirmationController {
    public void onYesClicked() {
        System.out.println("Yes clicked!");
    }
    public void onNoClicked() {
        System.out.println("No clicked!");
        FlowController.setStage("driverOrderRejectConfirm");
        FlowController.getStage().close();
        FlowController.removeScene("driverOrderRejectConfirm");
        FlowController.removeStage("driverOrderRejectConfirm");
    }
}
