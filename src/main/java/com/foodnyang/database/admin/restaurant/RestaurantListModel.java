package com.foodnyang.database.admin.restaurant;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantListModel {
    public ObservableList getRestoList(String namaResto, String namaOwner, String tipeResto, String alamatResto) {
        ObservableList<Restaurant> restoList = FXCollections.observableArrayList();
        try {
            CallableStatement restoQuery = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.getRestaurant(?, ?, ?, ?)}"
            );
            restoQuery.setString(1, String.format("%%%s%%", namaResto));
            restoQuery.setString(2, String.format("%%%s%%", namaOwner));
            restoQuery.setString(3, String.format("%%%s%%", tipeResto));
            restoQuery.setString(4, String.format("%%%s%%", alamatResto));
            ResultSet restaurants = restoQuery.executeQuery();

            while (restaurants.next()) {
                restoList.add(new Restaurant(
                        restaurants.getInt("id_restaurant"),
                        restaurants.getInt("id_mitra"),
                        restaurants.getString("nama_resto"),
                        restaurants.getString("nama"),
                        restaurants.getString("tipe_restaurant"),
                        restaurants.getString("alamat"),
                        restaurants.getInt("rating")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restoList;
    }
    public static void addRestaurant(int id, String nama_restaurant, String tipe_restaurant, String alamat_restaurant) {
        CallableStatement add_resto = null;
        ListStatus status = null;
        try {
            add_resto = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.addRestaurant(?, ?, ?, ?)}"
            );
            add_resto.setInt(1, id);
            add_resto.setString(2, nama_restaurant);
            add_resto.setString(3, tipe_restaurant);
            add_resto.setString(4, alamat_restaurant);
            add_resto.executeUpdate();
            System.out.println("Data Berhasil Ditambahkan!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (add_resto != null) {
                try {
                    add_resto.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void editSelectedRestaurant(int id, String nama_restoran, String tipe_restoran, String alamat) {
        CallableStatement edit_resto = null;
        ListStatus status = null;
        try {
            edit_resto = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.editRestaurant(?, ?, ?, ?)}"
            );
            edit_resto.setInt(1, id);
            edit_resto.setString(2, nama_restoran);
            edit_resto.setString(3, tipe_restoran);
            edit_resto.setString(4, alamat);
            edit_resto.executeUpdate();
            System.out.println("Data Berhasil Diedit!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (edit_resto != null) {
                try {
                    edit_resto.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteSelectedRestaurant(int id_restaurant) throws SQLException {
        PreparedStatement delete = null;
        try {
            delete = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "DELETE FROM restaurant WHERE id_restaurant = ?"
            );
            delete.setInt(1,id_restaurant);
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
