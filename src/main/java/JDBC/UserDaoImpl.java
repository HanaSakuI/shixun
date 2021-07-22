package JDBC;

import JavaBean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public List<User> getUserAll(String pageNo) throws SQLException {
        int pageIndex=Integer.parseInt(pageNo);
        int beginStart=(pageIndex-1)*10;
        String sql = "select * from ps_user , ps_role where ps_user.roleId = ps_role.roleId limit " + beginStart + ",10";
        ResultSet rs = executeQuery(sql);
        List<User>list = new ArrayList<User>();
        while(rs.next()){
            User user = new User();
            user.setUserCode(rs.getString("userCode"));
            user.setUserName(rs.getString("userName"));
            user.setRoleId(rs.getString("roleId"));
            user.setRoleName(rs.getString("roleName"));
            user.setEmail(rs.getString("email"));
            user.setLastLoginTime(rs.getTimestamp("lastLoginTime").toLocaleString());
            user.setUserStatus(rs.getInt("userStatus"));
            list.add(user);
        }
        closeAll();
        return list;
    }

    @Override
    public List<User> getUserByuserName(String userName, String userStatus) throws SQLException {

        String sql = "select * from ps_user , ps_role where ps_user.roleId = ps_role.roleId";
        if(!userName.equals("")){
            sql += " and userName = '" + userName + "'";
        }
        if(!userStatus.equals("")){
            sql += " and userStatus = '" + userStatus + "'";
        }

        ResultSet rs = executeQuery(sql);
        List<User> list = new ArrayList<User>();
        while(rs.next()){
            User user = new User();
            user.setUserCode(rs.getString("userCode"));
            user.setUserName(rs.getString("userName"));
            user.setRoleId(rs.getString("roleId"));
            user.setRoleName(rs.getString("roleName"));
            user.setEmail(rs.getString("email"));
            user.setLastLoginTime(rs.getTimestamp("lastLoginTime").toLocaleString());
            user.setUserStatus(rs.getInt("userStatus"));
            list.add(user);
        }
        closeAll();
        return list;
    }

    public int addUser(String userCode, String userName, String userPassword, String sex, String age , String phoneTel,  String email ,String lastLoginTime ,String roleId, String entryDate, String dimissionDate, String createdBy ,String userStatus) throws ParseException {
        String sql = "insert into ps_user(userCode,userName,userPassword,sex,age,phoneTel,email,lastLoginTime,roleId,entryDate,dimissionDate,createdBy,userStatus)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date entryDate_1 = sf.parse(entryDate);
        Date dimissionDate_1 = null;
        if(!dimissionDate.equals("")){
            dimissionDate_1 = sf.parse(dimissionDate);
        }
        System.out.println(createdBy);

        Object []objects = {
                userCode,
                userName,
                userPassword,
                sex,
                age,
                phoneTel,
                email,
                lastLoginTime,
                roleId,
                entryDate_1,
                dimissionDate_1,
                createdBy,
                userStatus
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

    public int forzenUser(String userCode) {
        String sql = "update ps_user set userStatus = 0 where userCode = ? ";
        System.out.println(userCode);
        Object []objects = {
                userCode
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

    public int updateUser(String userCode, String userName, String userPassword, String sex, String age, String phoneTel , String email, String lastLoginTime, String roleId, String entryDate, String dimissionDate, String userStatus) throws ParseException {
        String sql = "update ps_user set userName = ?,userPassword = ?,sex = ? , age = ?, phoneTel = ? , email = ?,lastLoginTime = ?,roleId = ?,entryDate = ?,dimissionDate = ?,userStatus = ? where userCode = ?";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date entryDate_1 = sf.parse(entryDate);
        Date dimissionDate_1 = null;
        if(!dimissionDate.equals("")){
            dimissionDate_1 = sf.parse(dimissionDate);
        }


        Object []objects = {
                userName,
                userPassword,
                sex,
                age,
                phoneTel,
                email,
                lastLoginTime,
                roleId,
                entryDate_1,
                dimissionDate_1,
                userStatus,
                userCode
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

    public int deleteUser(String userCode) {
        String sql="delete from ps_user where userCode='"+userCode+"'";
        int row=executeUpdate(sql);
        return row;
    }

    public User userLogin(String userCode,String passWord) throws SQLException, ClassNotFoundException {
        String sql =  "select * from ps_user where userCode = '"+userCode+"' and userPassword = '"+passWord+"'";
        User user = new User();
        rs = executeQuery(sql);
        if (rs.next()){
            user.setUserCode(rs.getString("userCode"));
            user.setUserName(rs.getString("userName"));
            user.setRoleId(rs.getString("roleId"));
            user.setEmail(rs.getString("email"));
            user.setLastLoginTime(rs.getTimestamp("lastLoginTime").toLocaleString());
            user.setUserStatus(rs.getInt("userStatus"));
        }else{
            return null;
        }
        closeAll();
        return user;
    }

    public int updateUserNew(String userCode, String userName, String oldPassword, String newPassword, String sex, String age, String phoneTel, String email, String entryDate, String dimissionDate) throws ParseException, SQLException {
        String sql = "select * from ps_user where userCode = '" + userCode + "' and userPassword = '" + oldPassword + "'";
        ResultSet rs = executeQuery(sql);

        if(rs.next() == false){
            return -2;
        }else{
            String sql_update = "update ps_user set userName = ?,userPassword = ?,sex = ? , age = ?, phoneTel = ? , email = ?,entryDate = ?,dimissionDate = ? where userCode = ?";
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date entryDate_1 = sf.parse(entryDate);
            Date dimissionDate_1 = null;
            if(!dimissionDate.equals("")){
                dimissionDate_1 = sf.parse(dimissionDate);
            }
            Object []objects = {
                    userName,
                    newPassword,
                    sex,
                    age,
                    phoneTel,
                    email,
                    entryDate_1,
                    dimissionDate_1,
                    userCode
            };
            int row = executeUpdate(sql_update,objects);
            return row;
        }
    }
}
