package com.foodnyang.database.customer.ordering;

import com.foodnyang.customer.ordering.RestaurantElement;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRestaurantListModel {
    public static ObservableList<RestaurantElement> getRestaurants(String nama, String tipe_restaurant, String status) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call dbo.listRestoran(?, ?, ?) }"
        );
        query.setString(1, String.format("%%%s%%", nama));
        query.setString(2, String.format("%%%s%%", tipe_restaurant));
        query.setString(3, String.format("%%%s%%", status));
        ResultSet result = query.executeQuery();
        ObservableList<RestaurantElement> restos = FXCollections.observableArrayList();

        while (result.next()) {
            if (result.getString("status").equals("Aktif")) {
                restos.add(new RestaurantElement(
                        result.getInt("id_restaurant"),
                        result.getString("nama"),
                        result.getString("tipe_restaurant"),
                        result.getString("alamat"),
                        result.getDouble("latitude"),
                        result.getDouble("longitude"),
                        result.getDouble("rating")
                ));
            }
        }
        if (query != null) query.close();
        if (result != null) result.close();
        return restos;
    }
}
