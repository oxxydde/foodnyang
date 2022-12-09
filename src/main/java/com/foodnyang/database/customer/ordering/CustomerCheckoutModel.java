package com.foodnyang.database.customer.ordering;

import com.foodnyang.customer.ordering.AddressElement;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCheckoutModel {
    public static ObservableList<AddressElement> listAlamat(int userId) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call listAlamatFromOneUser(?) }"
        );
        query.setInt(1, userId);
        ResultSet result = query.executeQuery();
        ObservableList<AddressElement> addresses = FXCollections.observableArrayList();

        while (result.next()) {
            addresses.add(new AddressElement(
                    result.getInt("id_alamat"),
                    result.getString("nama_lokasi"),
                    result.getString("alamat"),
                    result.getString("zipcode"),
                    result.getDouble("latitude"),
                    result.getDouble("longitude")
            ));
        }
        if (query != null) query.close();
        if (result != null) result.close();
        return addresses;
    }
}
