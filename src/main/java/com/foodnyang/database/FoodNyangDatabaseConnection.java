package com.foodnyang.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FoodNyangDatabaseConnection {
        static String connectionUrl =
                "jdbc:sqlserver://LAPTOP-7LFLS68P;" +
                "database=FoodNyang;" +
                "encrypt=true;" +
                "trustServerCertificate=true;" +
                "loginTimeout=30;" + "integratedSecurity=true";
        public static Connection connection () throws SQLException {
            return DriverManager.getConnection(connectionUrl);
        }
}
