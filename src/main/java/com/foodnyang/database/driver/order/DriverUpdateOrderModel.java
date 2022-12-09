package com.foodnyang.database.driver.order;

import com.foodnyang.FlowController;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.data.DataUpdate;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DriverUpdateOrderModel {
    public static DataUpdate updateStatusPesanan(int order_id, String status) throws SQLException {
        CallableStatement query = FoodNyangDatabaseConnection.connection().prepareCall(
                "{ call dbo.updateStatusPesanan(?, ?, ?) }"
        );
        query.setInt(1, order_id);
        query.setString(2, status);
        query.registerOutParameter(3, Types.NVARCHAR);
        int rowAffected = query.executeUpdate();

        DataUpdate stat = null;

        if (query.getString(3).equals("Success")) {
            stat = DataUpdate.UPDATE_SUCCEED;
        } else {
            stat = DataUpdate.UPDATE_FAILED;
        }

        if (query != null) query.close();
        return stat;
    }
}