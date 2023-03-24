package com.along.mvc;

import com.along.exception.AppException;
import com.along.exception.MoneyNotEnoughException;

/**
 * service （逻辑类（业务代码））
 */
public class AccountService {

    AccountDao accountDao = new AccountDao();

    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
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
        count += accountDao.update(toAct);
        if (count != 2) {
            throw new AppException("转账异常");
        }

    }


}
