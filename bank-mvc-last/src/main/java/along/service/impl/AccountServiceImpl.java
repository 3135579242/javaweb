package along.service.impl;

import along.entity.Account;
import along.exception.AppException;
import along.exception.MoneyNotEnoughException;
import along.mapper.AccountMapper;
import along.mapper.impl.AccountMapperImpl;
import along.service.AccountService;
import along.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    AccountMapper accountDao = new AccountMapperImpl();

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false);
            Account fromAct = accountDao.selectByActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("余额不足");
            }
            Account toAct = accountDao.selectByActno(toActno);
            //修改内存余额
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            //更新数据库余额
            int count = accountDao.update(fromAct);

//            String s = null;
//            s.toString();

            count += accountDao.update(toAct);
            if (count != 2) {
                throw new AppException("转账异常");
            }

            connection.commit();
        } catch (SQLException e) {
            throw new AppException("SQL异常！~");
        }

    }
}
