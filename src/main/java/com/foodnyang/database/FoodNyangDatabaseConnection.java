package com.foodnyang.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FoodNyangDatabaseConnection {
        static String connectionUrl =
                "jdbc:sqlserver://LERTGA;" +
                "database=FoodNyang;" +
                "encrypt=true;" +
                "trustServerCertificate=true;" +
                "integratedSecurity=true;" +
                "loginTimeout=30;";
        public static Connection connection() throws SQLException {
            return DriverManager.getConnection(connectionUrl);
        }
}
