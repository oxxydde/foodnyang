package com.foodnyang.database.restaurant;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListMenuModel {
    public ObservableList getListMenu(String nama, String deskripsi) {
        ObservableList<Makanan> listMenu = FXCollections.observableArrayList();
        try {
            CallableStatement menuQuery = FoodNyangDatabaseConnection.connection().prepareCall(
              "{call dbo.getRestoMenu(?, ?)}"
            );
            menuQuery.setString(1, String.format("%%%s%%", nama));
            menuQuery.setString(2, String.format("%%%s%%", deskripsi));
            ResultSet menus = menuQuery.executeQuery();
            while (menus.next()) {
                listMenu.add(new Makanan(
                        menus.getInt("id_mr"),
                        menus.getInt("id_restaurant"),
                        menus.getString("gambarMakanan"),
                        menus.getString("nama"),
                        menus.getString("deskripsi"),
                        menus.getInt("harga"),
                        menus.getInt("ketersediaan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMenu;
    }
    public static void addMenu(int id, String nama_menu, int harga, String deskripsi_menu, String gambar_makanan, int ketersediaan) {
        CallableStatement add_menu = null;
        ListStatus status = null;
        try {
            add_menu = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.daftarMenu(?, ?, ?, ?, ?, ?)}"
            );
            add_menu.setInt(1, id);
            add_menu.setString(2, nama_menu);
            add_menu.setInt(3, harga);
            add_menu.setString(4, deskripsi_menu);
            add_menu.setString(5, gambar_makanan);
            add_menu.setInt(6, ketersediaan);
            add_menu.executeUpdate();
            System.out.println("Data Berhasil Ditambahkan!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (add_menu != null) {
                try {
                    add_menu.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void editSelectedMenu(int id, String nama_menu, int harga, String deskripsi_menu, String gambar_makanan, int ketersediaan) {
        CallableStatement edit_mn = null;
        ListStatus status = null;
        try {
            edit_mn = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.editMenu(?, ?, ?, ?, ?, ?)}"
            );
            edit_mn.setInt(1, id);
            edit_mn.setString(2, nama_menu);
            edit_mn.setInt(3, harga);
            edit_mn.setString(4, deskripsi_menu);
            edit_mn.setString(5, gambar_makanan);
            edit_mn.setInt(6, ketersediaan);
            edit_mn.executeUpdate();
            System.out.println("Data Berhasil Diedit!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (edit_mn != null) {
                try {
                    edit_mn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void deleteSelectedMenu(int id_restaurant) throws SQLException {
        PreparedStatement delete = null;
        try {
            delete = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "DELETE FROM menu_restaurant WHERE id_restaurant = ?"
            );
            delete.setInt(1,id_restaurant);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (delete != null) {
                delete.close();
            }
        }
    }
}
