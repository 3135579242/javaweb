package along.service;

import along.exception.AppException;
import along.exception.MoneyNotEnoughException;

public interface AccountService {

    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException;

}
