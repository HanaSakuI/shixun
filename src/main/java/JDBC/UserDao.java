package JDBC;

import JavaBean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User>getUserAll(String pageNo) throws SQLException;//获取全部数据

    public List<User>getUserByuserName(String userName , String userStatus) throws SQLException;//根据用户名称和用户状态进行查询
}
