package com.foodnyang.admin;

import com.foodnyang.FlowController;

public class EmployeeListController {
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("AdminMenu");
    }
}
