package com.foodnyang.signup;

import com.foodnyang.FlowController;
import com.foodnyang.database.AccountModel;
import com.foodnyang.enums.signup.SignUpStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button sign_up_button;
    @FXML
    private Button back_button;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sign_up_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_name.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    SignUpStatus result = AccountModel.signUpUser(event, tf_name.getText(), tf_username.getText(), tf_password.getText());

                    switch (result) {
                        case SUCCEED -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Account has been successfully registered! Now do login.");
                            alert.show();
                            FlowController.setStage("MainStage");
                            FlowController.setScene("LoginScene");
                        }
                        case CONFLICT -> {
                            System.out.println("User already exists!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Username or password already exists!");
                            alert.show();
                        }
                    }
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        back_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FlowController.setScene("LoginScene");
            }
        });
    }
}
