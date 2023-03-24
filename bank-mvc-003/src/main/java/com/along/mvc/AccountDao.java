package com.along.mvc;

import com.along.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库查询类
 */
public class AccountDao {


    public int insert(Account account,Connection conn) {
        int count;
        PreparedStatement ps = null;
        try {
            String sql = "insert into t_act(actno,balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getActno());
            ps.setDouble(2, account.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }

        return count;
    }

    public int deleteByActno(String actno,Connection conn) {
        int count;
        PreparedStatement ps = null;

        try {
            String sql = "delete from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);

        }
        return count;
    }

    public int update(Account account,Connection conn) {
        int count;
        PreparedStatement ps = null;

        try {
            String sql = "update t_act set balance = ? , actno = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getActno());
            ps.setInt(3, account.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);

        }
        return count;
    }

    public Account selectByActno(String actno,Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = new Account();
        try {
            String sql = "select id,actno,balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            rs = ps.executeQuery();
            if (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setActno(rs.getString("actno"));
                account.setBalance(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return account;
    }

    public List<Account> selectAll(Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        try {
            String sql = "select id,actno,balance from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setActno(rs.getString("actno"));
                account.setBalance(rs.getDouble("balance"));
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return accountList;
    }


}
