package com.foodnyang.database.admin.customer;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerListModel {
    public ObservableList getCustomerList(String nama, String email, String no_telp, String tempat_lahir, String jenis_kelamin, String jenis_membership) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            CallableStatement customerQuery = FoodNyangDatabaseConnection.connection().prepareCall(
              "{call dbo.getCustomer(?, ?, ?, ?, ?, ?)}"
            );
            customerQuery.setString(1, String.format("%%%s%%", nama));
            customerQuery.setString(2, String.format("%%%s%%", email));
            customerQuery.setString(3, String.format("%%%s%%", no_telp));
            customerQuery.setString(4, String.format("%%%s%%", tempat_lahir));
            customerQuery.setString(5, String.format("%%%s%%", jenis_kelamin));
            customerQuery.setString(6, String.format("%%%s%%", jenis_membership));
            ResultSet customers = customerQuery.executeQuery();
            while(customers.next()) {
                customerList.add(new Customer(
                   customers.getInt("id_pembeli"),
                   customers.getString("nama"),
                   customers.getString("email"),
                   customers.getString("nomor_telpon"),
                   customers.getString("tempat_lahir"),
                   customers.getString("tanggal_lahir"),
                   customers.getString("jenis_kelamin"),
                   customers.getString("membership_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public static void addCustomer(int id, String tipe_membership) {
        CallableStatement add_cust = null;
        ListStatus status = null;
        try {
            add_cust = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.daftarPembeli(?, ?)}"
            );
            add_cust.setInt(1, id);
            add_cust.setString(2, tipe_membership);
            add_cust.executeUpdate();
            System.out.println("Data Berhasil Ditambahkan!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (add_cust != null) {
                try {
                    add_cust.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void editSelectedCustomer(int id, String tipe_membership) {
        CallableStatement edit_cust = null;
        ListStatus status = null;
        try {
            edit_cust = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.editCustomer(?, ?)}"
            );
            edit_cust.setInt(1, id);
            edit_cust.setString(2, tipe_membership);
            edit_cust.executeUpdate();
            System.out.println("Data Berhasil Diedit!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (edit_cust != null) {
                try {
                    edit_cust.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteSelectedCustomer(int id_pembeli) throws SQLException {
        PreparedStatement delete = null;
        try {
            delete = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "DELETE FROM pembeli WHERE id_pembeli = ?"
            );
            delete.setInt(1,id_pembeli);
            delete.executeUpdate();
            System.out.println("Data berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (delete != null) {
                delete.close();
            }
        }
    }
}
