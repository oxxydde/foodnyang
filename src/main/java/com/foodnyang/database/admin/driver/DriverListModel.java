package com.foodnyang.database.admin.driver;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverListModel {
    public ObservableList getDriverList(String nama, String email, String no_telp, String tempat_lahir, String jenis_kelamin, String tipe_kendaraan, String warna_kendaraan, String nomor_polisi) {
        ObservableList<Driver> driverList = FXCollections.observableArrayList();
        try {
            CallableStatement driverQuery = FoodNyangDatabaseConnection.connection().prepareCall(
              "{call dbo.getDriver(?, ?, ?, ?, ?, ?, ?, ?)}"
            );
            driverQuery.setString(1, String.format("%%%s%%", nama));
            driverQuery.setString(2, String.format("%%%s%%", email));
            driverQuery.setString(3, String.format("%%%s%%", no_telp));
            driverQuery.setString(4, String.format("%%%s%%", tempat_lahir));
            driverQuery.setString(5, String.format("%%%s%%", jenis_kelamin));
            driverQuery.setString(6, String.format("%%%s%%", tipe_kendaraan));
            driverQuery.setString(7, String.format("%%%s%%", warna_kendaraan));
            driverQuery.setString(8, String.format("%%%s%%", nomor_polisi));
            ResultSet drivers = driverQuery.executeQuery();

            while(drivers.next()) {
                driverList.add(new Driver(
                        drivers.getInt("id_driver"),
                        drivers.getString("nama"),
                        drivers.getString("email"),
                        drivers.getString("nomor_telpon"),
                        drivers.getString("tempat_lahir"),
                        drivers.getString("tanggal_lahir"),
                        drivers.getString("jenis_kelamin"),
                        drivers.getString("tanggal_bergabung"),
                        drivers.getString("tipe_kendaraan"),
                        drivers.getString("warna_kendaraan"),
                        drivers.getString("nomor_polisi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }
    public static void addDriver(int id, String tipe_kendaraan, String nomor_polisi, String warna_kendaraan) {
        CallableStatement add_emp = null;
        ListStatus status = null;
        try {
            add_emp = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.daftarDriver(?, ?, ?, ?)}"
            );
            add_emp.setInt(1, id);
            add_emp.setString(2, tipe_kendaraan);
            add_emp.setString(3, nomor_polisi);
            add_emp.setString(4, warna_kendaraan);
            add_emp.executeUpdate();
            System.out.println("Data Berhasil Ditambahkan!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (add_emp != null) {
                try {
                    add_emp.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void editSelectedDriver(int id, String tipe_kendaraan, String nomor_polisi, String warna_kendaraan) {
        CallableStatement edit_dr = null;
        ListStatus status = null;
        try {
            edit_dr = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.editDriver(?, ?, ?, ?)}"
            );
            edit_dr.setInt(1, id);
            edit_dr.setString(2, tipe_kendaraan);
            edit_dr.setString(3, nomor_polisi);
            edit_dr.setString(4, warna_kendaraan);
            edit_dr.executeUpdate();
            System.out.println("Data Berhasil Diedit!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (edit_dr != null) {
                try {
                    edit_dr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        public static void deleteSelectedDriver(int driver_id) {
        PreparedStatement delete = null;
        try {
            delete = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "DELETE FROM driver WHERE id_driver = ?"
            );
            delete.setInt(1,driver_id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (delete != null) {
                try {
                    delete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
