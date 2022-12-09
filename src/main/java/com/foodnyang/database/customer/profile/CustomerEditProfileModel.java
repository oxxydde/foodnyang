package com.foodnyang.database.customer.profile;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.data.DataUpdate;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class CustomerEditProfileModel {
    public static DataUpdate updateProfile(
            int userId,
            String nama,
            String jenisKelamin,
            String nomorTelp,
            String email
    ) {
        try {
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call updateProfile(?, ?, ?, ?, ?) }"
            );
            query.setInt(1, userId);
            query.setString(2, nama);
            query.setString(3, jenisKelamin);
            query.setString(4, nomorTelp);
            query.setString(5, email);
            int rowAffected = query.executeUpdate();
            DataUpdate updateStatus = null;

            if (rowAffected != 0) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
            if (query != null) query.close();
            return updateStatus;
        } catch (SQLException e) {
                e.printStackTrace();
                return null;
        }
    }
    public static DataUpdate addAddress(int idPembeli, String namaLokasi, String alamat, int zipcode, double latitude, double longitude) {
        try {
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call tambahAlamat(?, ?, ?, ?, ?, ?) }"
            );
            query.setInt(1, idPembeli);
            query.setString(2, namaLokasi);
            query.setString(3, alamat);
            query.setInt(4, zipcode);
            query.setDouble(5, latitude);
            query.setDouble(6, longitude);
            int rowAffected = query.executeUpdate();
            DataUpdate updateStatus = null;
            if (rowAffected != 0) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
           if (query != null) query.close();
           return updateStatus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DataUpdate editAddress(int idAlamat, String namaLokasi, String alamat, int zipcode, double latitude, double longitude) {
        try {
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call editAlamat(?, ?, ?, ?, ?, ?) }"
            );
            query.setInt(1, idAlamat);
            query.setString(2, namaLokasi);
            query.setString(3, alamat);
            query.setInt(4, zipcode);
            query.setDouble(5, latitude);
            query.setDouble(6, longitude);
            int rowAffected = query.executeUpdate();
            DataUpdate updateStatus = null;

            if (rowAffected != 0) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
            if (query != null) query.close();
            return updateStatus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DataUpdate deleteAddress(int idAlamat) {
        try {
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call hapusAlamat(?) }"
            );
            query.setInt(1, idAlamat);
            int rowAffected = query.executeUpdate();
            DataUpdate updateStatus = null;

            if (rowAffected != 0) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
            if (query != null) query.close();
            return updateStatus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DataUpdate updatePassword(int userId, String password) {
        try {
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call updatePassword(?, ?) }"
            );
            query.setInt(1, userId);
            query.setString(2, password);
            int rowAffected = query.executeUpdate();
            DataUpdate updateStatus = null;

            if (rowAffected != 0) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
            if (query != null) query.close();
            return updateStatus;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
