package com.along;

public class DBUtil {

    private static MyThreadLocal<Connection> local = new MyThreadLocal<>();

    public static Connection getConnection(){
        Connection connection = local.get();
        if (connection==null) {
            connection = new Connection();
            local.set(connection);
        }
        return connection;
    }


}
