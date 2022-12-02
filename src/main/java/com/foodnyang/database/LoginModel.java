package com.foodnyang.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String user_email, String password){
        Parent root = null;

        try {
            root = FXMLLoader.load(LoginModel.class.getResource(fxmlFile));
        } catch (IOException e) {
                e.printStackTrace();
        }

        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        String css = LoginModel.class.getResource("/com/foodnyang/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        if(fxmlFile.equals("/com/foodnyang/admin/admin_menu.fxml")){
            stage.setTitle("Admin Menu");
        }
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String name, String user_email, String password) {
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = FoodNyangDatabaseConnection.connection();
            psCheckUserExists = conn.prepareStatement("SELECT nama, user_email, password  FROM user_info WHERE user_email = ?");
            psCheckUserExists.setString(1, user_email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or password already exists!");
                alert.show();
            } else {
                psInsert = conn.prepareStatement("INSERT INTO user_info(id, nama, user_email, password) VALUES (1234,?, ?, ?)");
                psInsert.setString(1, name);
                psInsert.setString(2, user_email);
                psInsert.setString(3, password);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Account has been successfully registered!");
                alert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if (psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }

    public static void loginUser(ActionEvent event, String user_email, String password) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = FoodNyangDatabaseConnection.connection();
            preparedStatement = conn.prepareStatement("SELECT password FROM user_info WHERE user_email = ?");
            preparedStatement.setString(1, user_email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User not found! Please register first.");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievePassword = resultSet.getString("password");
                    if (retrievePassword.equals(password)){
                        changeScene(event, "/com/foodnyang/driver/driver_menu.fxml","Driver Menu", user_email, password);
                    } else {
                        System.out.println("Username or password does not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or password does not match!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
