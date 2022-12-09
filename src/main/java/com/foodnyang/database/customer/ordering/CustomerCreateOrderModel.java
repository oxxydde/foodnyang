package com.foodnyang.database.customer.ordering;

import com.foodnyang.customer.ordering.FoodMenuElement;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.data.DataUpdate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerCreateOrderModel {
    public static DataUpdate createOrder (
            int id_customer,
            int id_resto,
            int id_alamat_kirim,
            ArrayList<FoodMenuElement> items
    ) throws SQLException {
        int totalItem = 0, totalHarga = 0;
        DataUpdate updateStatus = null;

        for (FoodMenuElement item : items) {
            totalItem += item.getQty();
            totalHarga += item.getTotalHarga();
        }
        CallableStatement createPesanan = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call buatPesanan(?, ?, ?, ?, ?, ?) }"
        );
        createPesanan.setInt(1, id_customer);
        createPesanan.setInt(2, id_resto);
        createPesanan.setInt(3, id_alamat_kirim);
        createPesanan.setDate(4, new java.sql.Date(
                (new java.util.Date()).getTime()
        ));
        createPesanan.setInt(5, totalItem);
        createPesanan.setInt(6, totalHarga);
        int rowAffected = createPesanan.executeUpdate();

        if (rowAffected != 0) {
            PreparedStatement queryNewIdPesanan = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "SELECT TOP 1 * FROM pesanan p " +
                            "WHERE p.id_pesanan NOT IN " +
                            "(SELECT m.id_pesanan FROM memesan m)" +
                            " ORDER BY id_pesanan DESC"
            );
            ResultSet idPesananResult = queryNewIdPesanan.executeQuery();
            idPesananResult.next();
            int newId = idPesananResult.getInt("id_pesanan");

            CallableStatement createMemesan = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{ call memesanItem(?, ?, ?) }"
            );
            for (FoodMenuElement item : items) {
                createMemesan.clearParameters();
                createMemesan.setInt(1, item.getId());
                createMemesan.setInt(2, newId);
                createMemesan.setInt(3, item.getQty());
                createMemesan.addBatch();
            }
            int[] memesanRowAffected = createMemesan.executeBatch();

            if (createPesanan != null) createPesanan.close();
            if (createMemesan != null) createMemesan.close();
            if (idPesananResult != null) idPesananResult.close();

            // i don't know why calculate like this, but it works for row affection checking
            if (-(Arrays.stream(memesanRowAffected).sum()+1) == items.size()) {
                updateStatus = DataUpdate.UPDATE_SUCCEED;
            } else {
                updateStatus = DataUpdate.UPDATE_FAILED;
            }
        }
        else {
            updateStatus = DataUpdate.UPDATE_FAILED;
        }
        return updateStatus;
    }
}
