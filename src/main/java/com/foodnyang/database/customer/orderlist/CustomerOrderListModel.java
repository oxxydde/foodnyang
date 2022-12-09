package com.foodnyang.database.customer.orderlist;

import com.foodnyang.customer.orderlist.CustomerOrderElement;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.driver.order.DriverOrderElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOrderListModel {
    public static ObservableList getOrders(
            int customerId,
            int orderIdFilter,
            String restoNameFilter,
            String driverNameFilter,
            String addressFilter,
            String status
    ) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call listOrderOnCustomer(?, ?, ?, ?, ?) }"
        );
        query.setInt(1, customerId);
        query.setInt(2, orderIdFilter);
        query.setString(3, driverNameFilter);
        query.setString(4, restoNameFilter);
        query.setString(5, addressFilter);
        ResultSet result = query.executeQuery();

        ObservableList<CustomerOrderElement> orders = FXCollections.observableArrayList();

        while (result.next()) {
            String statusQuery = result.getString("statusPesanan");
            boolean insert = false;
            if (status.equals("Selesai")) {
                if (statusQuery.equals("Ditolak") || statusQuery.equals("Selesai")) {
                    insert = true;
                }
            }
            else if (status.equals("Aktif")) {
                if (!(statusQuery.equals("Ditolak") || statusQuery.equals("Selesai"))) {
                    insert = true;
                }
            }
            if (insert) {
                orders.add(new CustomerOrderElement(
                        result.getInt("id_pesanan"),
                        result.getString("namaDriver"),
                        result.getString("namaResto"),
                        result.getString("alamatTujuan"),
                        result.getInt("totalHarga") + 9000,
                        statusQuery
                ));
            }
        }

        if (result != null) result.close();
        if (query != null) query.close();

        return orders;
    }
}
