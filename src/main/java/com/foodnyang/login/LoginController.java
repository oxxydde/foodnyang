package com.foodnyang.login;

import com.foodnyang.database.AccountModel;
import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.enums.signup.LoginStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // handle controller
                LoginStatus result = AccountModel.loginUser(event, tf_username.getText(), tf_password.getText());

                switch (result) {
                    case SUCCEED -> {
                        // change scene and send user data
                        try {
                            String css = MainApp.class.getResource("css/style.css").toExternalForm();
                            FlowController.createScene("AdminMenu", new Scene(FXMLLoader.load(MainApp.class.getResource("admin/admin_menu.fxml"))));
                            FlowController.setStage("MainStage");
                            FlowController.setScene("AdminMenu");
                            FlowController.getScene().getStylesheets().add(css);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case INCORRECT_CREDS -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or password does not match!");
                        alert.show();
                    }
                }
            }
        });

        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader signup = new FXMLLoader(MainApp.class.getResource("SignUp/signup_view.fxml"));
                    FlowController.setStage("MainStage");
                    FlowController.createScene("SignUpScene", new Scene(signup.load()));
                    FlowController.setScene("SignUpScene");
                    String css = MainApp.class.getResource("css/style.css").toExternalForm();
                    FlowController.getScene().getStylesheets().add(css);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}