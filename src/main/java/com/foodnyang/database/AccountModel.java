package com.foodnyang.database;

import com.foodnyang.enums.signup.LoginStatus;
import com.foodnyang.enums.signup.SignUpStatus;
import com.foodnyang.login.AccountInfo;
import javafx.event.ActionEvent;

import javax.xml.transform.Result;
import java.sql.*;

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
        CallableStatement query = null;
        ResultSet resultSet = null;
        LoginStatus status = null;

        try {
            conn = FoodNyangDatabaseConnection.connection();
            query = FoodNyangDatabaseConnection.connection().prepareCall("{? = call dbo.loginCheck(?, ?)}");
            query.registerOutParameter(1, Types.NVARCHAR);
            query.setString(2, user_email);
            query.setString(3, password);
            query.execute();

            if (query.getString(1) == null) {
                System.out.println("User not found in the database!");
                status = LoginStatus.NO_ACCOUNT;
            } else {
                    String retrievePassword = query.getString(1);
                    if (retrievePassword.equals("Valid")){
                        status = LoginStatus.SUCCEED;
                    } else {
                        System.out.println("Username or password does not match!");
                        status = LoginStatus.INCORRECT_CREDS;
                    }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

            if (query != null) {
                try {
                    query.close();
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
        return status;
    }

    public static AccountInfo getUserInfo(String username) throws SQLException {
        PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                "SELECT * FROM user_info WHERE email=?"
        );
        query.setString(1, username);
        ResultSet result = query.executeQuery();
        AccountInfo info = null;

        while (result.next()) {
            info = new AccountInfo(
                    result.getInt("id"),
                    result.getString("nama"),
                    result.getString("jenis_kelamin"),
                    result.getString("nomor_telpon"),
                    result.getString("email")
            );
        }
        return info;
    }

    public static boolean isAdmin(int userId) {
        try {
            PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT * FROM pegawai WHERE id_pegawai=?"
            );
            query.setInt(1, userId);
            ResultSet result = query.executeQuery();
            boolean statement = result.isBeforeFirst();
            if (query != null) query.close();
            if (result != null) result.close();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isDriver(int userId) {
        try {
            PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT * FROM driver WHERE id_driver=?"
            );
            query.setInt(1, userId);
            ResultSet result = query.executeQuery();
            boolean statement = result.isBeforeFirst();
            if (query != null) query.close();
            if (result != null) result.close();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isRestaurant(int userId) {
        try {
            PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT * FROM mitra WHERE id_mitra=?"
            );
            query.setInt(1, userId);
            ResultSet result = query.executeQuery();
            boolean statement = result.isBeforeFirst();
            if (query != null) query.close();
            if (result != null) result.close();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPembeli(int userId) {
        try {
            PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT * FROM pembeli WHERE id_pembeli=?"
            );
            query.setInt(1, userId);
            ResultSet result = query.executeQuery();
            boolean statement = result.isBeforeFirst();
            if (query != null) query.close();
            if (result != null) result.close();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
