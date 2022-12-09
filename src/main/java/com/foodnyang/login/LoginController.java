package com.foodnyang.login;

import com.foodnyang.database.AccountModel;
import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.enums.signup.LoginStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
        login_button.setOnAction(event -> {
            // handle controller
            LoginStatus result = AccountModel.loginUser(event, tf_username.getText(), tf_password.getText());

            switch (result) {
                case SUCCEED -> {
                    FlowController.createScene("AccountInfo", new Scene(new Label()));
                    try {
                        FlowController.getSceneByKey("AccountInfo").setUserData(AccountModel.getUserInfo(tf_username.getText()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // show the window
                    DialogPane roleSelect = new DialogPane();

                    VBox optionsBox = new VBox();
                    optionsBox.setSpacing(7);
                    roleSelect.setPrefWidth(275);
                    roleSelect.setPrefHeight(275);

                    Button adminBtn = new Button("Admin");
                    adminBtn.setId("adminBtn");
                    adminBtn.setOnMouseClicked(event1 -> {
                        FlowController.setStage("MainStage");
                        String css = MainApp.class.getResource("css/style.css").toExternalForm();
                        try {
                            FlowController.createScene("AdminMenu", new Scene(FXMLLoader.load(MainApp.class.getResource("admin/admin_menu.fxml"))));
                            FlowController.setScene("AdminMenu");
                            FlowController.getScene().getStylesheets().add(css);
                            FlowController.getStageByKey("LoginRolePrompt").close();
                            FlowController.removeStage("LoginRolePrompt");
                            FlowController.removeScene("LoginRolePrompt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Button customerBtn = new Button("Pembeli");
                    customerBtn.setId("customerBtn");
                    customerBtn.setOnMouseClicked(event1 -> {
                        FlowController.setStage("MainStage");
                        String css = MainApp.class.getResource("css/style.css").toExternalForm();
                        try {
                            FlowController.createScene("CustomerMenu", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/customer_menu.fxml"))));
                            FlowController.setScene("CustomerMenu");
                            FlowController.getScene().getStylesheets().add(css);
                            FlowController.getStageByKey("LoginRolePrompt").close();
                            FlowController.removeStage("LoginRolePrompt");
                            FlowController.removeScene("LoginRolePrompt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Button driverBtn = new Button("Driver");
                    driverBtn.setId("driverBtn");
                    driverBtn.setOnMouseClicked(event1 -> {
                        try {
                            FlowController.setStage("MainStage");
//                                String css = MainApp.class.getResource("css/style.css").toExternalForm();
                            FlowController.createScene("DriverMenu", new Scene(FXMLLoader.load(MainApp.class.getResource("driver/driver_menu.fxml"))));

                            FlowController.setScene("DriverMenu");
//                                FlowController.getScene().getStylesheets().add(css);
                            FlowController.getStageByKey("LoginRolePrompt").close();
                            FlowController.removeStage("LoginRolePrompt");
                            FlowController.removeScene("LoginRolePrompt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    Button restaurantBtn = new Button("Restoran");
                    restaurantBtn.setId("restaurantBtn");
                    restaurantBtn.setOnMouseClicked(event1 -> {
                    });

                    if (AccountModel.isAdmin(((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId())) optionsBox.getChildren().add(adminBtn);
                    if (AccountModel.isPembeli(((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId())) optionsBox.getChildren().add(customerBtn);
                    if (AccountModel.isDriver(((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId())) optionsBox.getChildren().add(driverBtn);
                    if (AccountModel.isRestaurant(((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId())) optionsBox.getChildren().add(restaurantBtn);

                    roleSelect.setContent(optionsBox);

                    if (FlowController.getStageByKey("LoginRolePrompt") == null) {
                        FlowController.createStage("LoginRolePrompt", new Stage());
                        FlowController.createScene("LoginRolePrompt", new Scene(roleSelect));
                        FlowController.setStage("LoginRolePrompt");
                        FlowController.setScene("LoginRolePrompt");
                        FlowController.getStage().show();
                    } else {
                        FlowController.setStage("LoginRolePrompt");
                        FlowController.getStage().requestFocus();
                    }
                }
                case INCORRECT_CREDS -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Username or password does not match!");
                    alert.show();
                }
            }
        });

        signup_button.setOnAction(event -> {
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
        });
    }
}