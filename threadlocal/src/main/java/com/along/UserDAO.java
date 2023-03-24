package com.along;

public class UserDAO {

    public void insert(){
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        System.out.println("userDAO insert");
    }

}
