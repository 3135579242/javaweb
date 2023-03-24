package com.along.utils;

import java.sql.*;

public class DBUtil {

    private static final String DB_URL = "jdbc:mysql://192.168.1.129:3306/mvc";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "along";

    private DBUtil() {

    }

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ThreadLocal<Connection> local = new ThreadLocal<>();


    public static Connection getConnection() throws SQLException {
        Connection connection = local.get();
        if (connection == null) {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
                local.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
