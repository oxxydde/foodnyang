package com.foodnyang.driver.order;

import com.foodnyang.FlowController;

public class DriverCompleteConfirmationController {
    public void onYesClicked() {
        System.out.println("Yes clicked!");
    }
    public void onNoClicked() {
        System.out.println("No clicked!");
        FlowController.setStage("driverOrderCompleteConfirm");
        FlowController.getStage().close();
        FlowController.removeScene("driverOrderCompleteConfirm");
        FlowController.removeStage("driverOrderCompleteConfirm");
    }
}
