package com.foodnyang.database.customer.ordering;

import com.foodnyang.customer.ordering.FoodMenuElement;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRestoMenuListModel {
    public static ObservableList<FoodMenuElement> getMenus(int id_resto, String filter) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call listFoodMenuFromRestoId(?, ?) }"
        );
        query.setInt(1, id_resto);
        query.setString(2, String.format("%%%s%%", filter));
        ResultSet result = query.executeQuery();
        ObservableList<FoodMenuElement> menus = FXCollections.observableArrayList();

        while (result.next()) {
            menus.add(new FoodMenuElement(
                    result.getInt("id_mr"),
                    result.getString("nama"),
                    result.getString("deskripsi"),
                    result.getInt("harga"),
                    result.getInt("ketersediaan"),
                    result.getString("gambarMakanan")
            ));
        }
        return menus;
    }
}
