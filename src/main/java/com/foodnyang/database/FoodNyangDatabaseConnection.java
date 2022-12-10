package com.foodnyang.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FoodNyangDatabaseConnection {
        static String connectionUrl =
                "jdbc:sqlserver://LAPTOP-14SHGATG:1433;" +
                "database=FoodNyang;" +
                        "user=sa;"+
                        "password=admin;"+
                "encrypt=true;" +
                "trustServerCertificate=true;" +
                "loginTimeout=30;";
        public static Connection connection() throws SQLException {
            return DriverManager.getConnection(connectionUrl);
        }
}
