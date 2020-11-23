package com.example.myappfestival.business.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static Connection connection;
    private static String url="jdbc:mysql://192.168.0.26:3306/Festival";
    private static String username = "android";
    private static String password = "abc123";

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection = null;
    }
    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url,username,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}
