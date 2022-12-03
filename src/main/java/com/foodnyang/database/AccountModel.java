package com.foodnyang.database;

import com.foodnyang.enums.signup.LoginStatus;
import com.foodnyang.enums.signup.SignUpStatus;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel {
    public static SignUpStatus signUpUser(ActionEvent event, String name, String user_email, String password) {
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        Connection conn = null;
        SignUpStatus status = null;

        try {
            conn = FoodNyangDatabaseConnection.connection();
            psCheckUserExists = conn.prepareStatement("SELECT nama, user_email, password  FROM user_info WHERE user_email = ?");
            psCheckUserExists.setString(1, user_email);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                status = SignUpStatus.CONFLICT;
            } else {
                psInsert = conn.prepareStatement("INSERT INTO user_info VALUES (1234, '0808', ?, ?, 'L', CONVERT(datetime, '2001-08-22'), CONVERT(datetime, '2001-08-22'), ?)");
                psInsert.setString(1, name);
                psInsert.setString(2, user_email);
                psInsert.setString(3, password);
                psInsert.executeUpdate();
                status = SignUpStatus.SUCCEED;
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
        return status;
    }

    public static LoginStatus loginUser(ActionEvent event, String user_email, String password) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LoginStatus status = null;

        try {
            conn = FoodNyangDatabaseConnection.connection();
            preparedStatement = conn.prepareStatement("SELECT password FROM user_info WHERE user_email = ?");
            preparedStatement.setString(1, user_email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                status = LoginStatus.NO_ACCOUNT;
            } else {
                while (resultSet.next()){
                    String retrievePassword = resultSet.getString("password");
                    if (retrievePassword.equals(password)){
                        status = LoginStatus.SUCCEED;
                    } else {
                        System.out.println("Username or password does not match!");
                        status = LoginStatus.INCORRECT_CREDS;
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
        return status;
    }
}
