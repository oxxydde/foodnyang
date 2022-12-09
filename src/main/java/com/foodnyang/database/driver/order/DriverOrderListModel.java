package com.foodnyang.database.driver.order;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.driver.order.DriverOrderElement;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverOrderListModel {

    public ObservableList getOrderList(int driver_id, int orderIdFilter, String restoNameFilter, String custNameFilter, String addressFilter, String status) {
        ObservableList<DriverOrderElement> driverOrderElementList = FXCollections.observableArrayList();
        try {
            // Here we will add driver_id condition, for development phase not included 
            CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall("{ call listOrderDriver(?, ?, ?, ?, ?) }");
            query.setInt(1, driver_id);
            query.setInt(2, orderIdFilter);
            query.setString(3, custNameFilter);
            query.setString(4, restoNameFilter);
            query.setString(5, addressFilter);
            ResultSet orders = query.executeQuery();
            System.out.println("Query success!");
            while (orders.next()) {
                String statusQuery = orders.getString("statusPesanan");
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
                    driverOrderElementList.add(new DriverOrderElement(
                            orders.getInt("id_pesanan"),
                            orders.getString("namaPembeli"),
                            orders.getString("namaResto"),
                            orders.getString("alamatTujuan"),
                            orders.getInt("totalHarga"),
                            statusQuery
                    ));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return driverOrderElementList;
    }
}
