package com.tourismAgency.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection con = null;

    public Connection connectDB(){
        try {
            this.con = DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME, Config.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.con;
    }

    public static Connection getInstances(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }
}
