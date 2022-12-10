package com.foodnyang.admin;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Flow;

public class AdminMenuController {
    @FXML
    private Parent root;
    @FXML
    public void onDaftarPegawaiButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/employee_list.fxml"));
        FlowController.createScene("EmployeeListScene", new Scene(root));
        FlowController.setScene("EmployeeListScene");
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.getScene().getStylesheets().add(css);
        FlowController.getStage().setTitle("Employee List Menu");
    }

    public void onDaftarRestaurantButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/restaurant_list.fxml"));
        FlowController.createScene("RestaurantListScene", new Scene(root));
        FlowController.setScene("RestaurantListScene");
        FlowController.getStage().setTitle("Restaurant List Menu");
    }

    public void onDaftarDriverButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/driver_list.fxml"));
        FlowController.createScene("DriverListScene", new Scene(root));
        FlowController.setScene("DriverListScene");
        FlowController.getStage().setTitle("Driver List Menu");

    }
    public void onDaftarCustomerButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/customer_list.fxml"));
        FlowController.createScene("CustomerListScene", new Scene(root));
        FlowController.setScene("CustomerListScene");
        FlowController.getStage().setTitle("Customer List Menu");

    }

    public void onSignOutButtonClick() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/login/login_view.fxml"));
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        FlowController.createScene("LoginScene", new Scene(root));
        FlowController.setStage("MainStage");
        FlowController.setScene("LoginScene");
        FlowController.getScene().getStylesheets().add(css);
    }
}
