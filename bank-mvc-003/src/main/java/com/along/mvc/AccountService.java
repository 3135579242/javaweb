package com.along.mvc;

import com.along.exception.AppException;
import com.along.exception.MoneyNotEnoughException;
import com.along.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * service （逻辑类（业务代码））
 */
public class AccountService {

    AccountDao accountDao = new AccountDao();

    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false);
            Account fromAct = accountDao.selectByActno(fromActno,connection);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("余额不足");
            }
            Account toAct = accountDao.selectByActno(toActno,connection);
            //修改内存余额
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            //更新数据库余额
            int count = accountDao.update(fromAct,connection);
            count += accountDao.update(toAct,connection);
            if (count != 2) {
                throw new AppException("转账异常");
            }
            connection.commit();
        } catch (SQLException e) {
            throw new AppException("SQL异常！~");
        }

    }


}
