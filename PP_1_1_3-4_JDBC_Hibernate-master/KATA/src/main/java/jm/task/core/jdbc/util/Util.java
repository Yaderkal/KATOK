package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mykatatest";
    private static final String username = "root";
    private static final String password = "root";
    public static void main(String[] args) {
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("конект");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("неа");
        }
        return connection;
    }

}


