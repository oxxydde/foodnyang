package com.foodnyang.database.customer.orderlist;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.driver.order.FoodOrderItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOrderDetailModel {
    public static ObservableList getOrderDetailById(int orderId) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call dbo.getOrderDetail(?) }"
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
        if (query != null) query.close();
        if (result != null) result.close();
        return items;
    }
}
