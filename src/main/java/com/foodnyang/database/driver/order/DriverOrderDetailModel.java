package com.foodnyang.database.driver.order;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.driver.order.FoodOrderItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverOrderDetailModel {
    public ObservableList getOrderDetailById(int orderId) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call dbo.getOrderDetailDriver(?) }"
        );
        query.setInt(1, orderId);
        ResultSet result = query.executeQuery();
        ObservableList<FoodOrderItems> items = FXCollections.observableArrayList();

        while (result.next()) {
            items.add(new FoodOrderItems(
                    result.getInt("id_mr"),
                    result.getString("nama"),
                    result.getInt("jumlah_item"),
                    result.getInt("subtotalHarga")
            ));
        }
        return items;
    }
}
