package com.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_case_study3";
    private static final String USER = "root";
    private static final String PASSWORD = "Kieulinh1@";
    private static Connection connection;

    private ConnectionJDBC() {
    }
    public static Connection getConnection(){
        if(connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection =  DriverManager.getConnection(URL,USER,PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
