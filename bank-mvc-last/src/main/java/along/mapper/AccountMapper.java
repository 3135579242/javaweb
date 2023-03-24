package along.mapper;

import along.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AccountMapper {


    public int insert(Account account);

    public int deleteByActno(String actno);

    public int update(Account account);


    public Account selectByActno(String actno);

    public List<Account> selectAll();


}
