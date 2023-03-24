package com.along.mvc;

import com.along.exceptions.AppException;
import com.along.exceptions.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {

    private Connection connection = null;
    private PreparedStatement ps1 = null;
    private PreparedStatement ps2 = null;
    private PreparedStatement ps3 = null;
    private ResultSet rs = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));
        int count = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.1.129:3306/mvc", "root", "along");
            String sql1 = "select balance from t_act where actno = ?";
            ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, fromActno);
            rs = ps1.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < money) {
                    throw new MoneyNotEnoughException("对不起、余额不足");
                }
                //余额足够走这里
                String sql2 = "update t_act set balance = balance - ? where actno = ?";
                ps2 = connection.prepareStatement(sql2);
                ps2.setDouble(1, money);
                ps2.setString(2, fromActno);
                //开启事务
                connection.setAutoCommit(false);
                count = ps2.executeUpdate();

//                String s = null;
//                s.toString();

                String sql3 = "update t_act set balance = balance + ? where actno = ?";
                ps3 = connection.prepareStatement(sql3);
                ps3.setDouble(1, money);
                ps3.setString(2, toActno);

                count = count + ps3.executeUpdate();

                if (count != 2) {
                    throw new AppException("应用程序异常、联系管理员！");
                }
                connection.commit();
                out.print("转账成功！");
            }
        } catch (Exception e) {
            if (connection != null) {
                try {
                    //回滚事务
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            out.print(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
