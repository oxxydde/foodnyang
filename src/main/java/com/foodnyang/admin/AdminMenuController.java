package com.foodnyang.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void onDaftarPegawaiButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/employee_list.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("List Pegawai");
        stage.show();
    }

    public void onDaftarRestaurantButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/employee_list.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onDaftarDriverButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/admin/driver_list.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("List Driver");
        stage.show();
    }

    public void onSignOutButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/foodnyang/login/login_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("/com/foodnyang/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
