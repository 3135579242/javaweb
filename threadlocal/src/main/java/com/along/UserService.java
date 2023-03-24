package com.along;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public void save(){
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        userDAO.insert();
    }

}
