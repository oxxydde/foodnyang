package com.foodnyang.database.driver.order;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.driver.order.Order;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverOrderListModel {

    public ObservableList getOrderList(int driver_id, String filter) {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        try {
            // Here we will add driver_id condition, for development phase not included 
            PreparedStatement query = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT p.order_id, r.nama_restaurant, ui.nama, p.subtotal_harga FROM pesanan p " +
                        "JOIN restaurant r ON p.restaurant_id=r.id " +
                        "JOIN user_info ui ON p.buyer_id=ui.id " +
                        "WHERE p.order_id LIKE ? OR r.nama_restaurant LIKE ? OR ui.nama LIKE ?"

            );
            query.setString(1, String.format("%%%s%%", filter));
            query.setString(2, String.format("%%%s%%", filter));
            query.setString(3, String.format("%%%s%%", filter));
            ResultSet orders = query.executeQuery();
            System.out.println("Query success!");
            while (orders.next()) {
                orderList.add(new Order(
                        orders.getInt("order_id"),
                        orders.getString("nama"),
                        orders.getString("nama_restaurant"),
                        orders.getInt("subtotal_harga")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
