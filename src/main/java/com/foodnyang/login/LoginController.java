package com.foodnyang.login;

import com.foodnyang.database.LoginModel;
import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    private Parent root;

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
                LoginModel.loginUser(event, tf_username.getText(), tf_password.getText());
            }
        });

        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(MainApp.class.getResource("SignUp/signup_view.fxml"));
                    FlowController.setStage("MainStage");
                    FlowController.createScene("SignUpScene", new Scene(root));
                    FlowController.setScene("SignUpScene");
                    String css = MainApp.class.getResource("css/style.css").toExternalForm();
                    FlowController.getScene().getStylesheets().add(css);
//                    scene.getStylesheets().add(css);
//                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


//    @FXML
//    public void onLoginButtonClick(ActionEvent event) throws IOException {
//         root = FXMLLoader.load(getClass().getResource("/com/src/foodnyang/Admin/admin_menu.fxml"));
//         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         String css = this.getClass().getResource("/com/src/foodnyang/css/style.css").toExternalForm();
//         scene.getStylesheets().add(css);
//         stage.setScene(scene);
//         stage.show();
//    }

//    public void onSignUpButtonClick(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/Sign Up/signup_view.fxml"));
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        String css = this.getClass().getResource("/com/src/foodnyang/css/style.css").toExternalForm();
//        scene.getStylesheets().add(css);
//        stage.setScene(scene);
//        stage.setTitle("Sign Up Page");
//        stage.show();
//    }
}