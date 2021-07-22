package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageDao extends BaseDao{
    public  int getCount() throws SQLException {
        int count = 0;
        String sql="SELECT count(*) FROM ps_pole_info";
        ResultSet rs= executeQuery(sql);
        if(rs.next()){
            count=rs.getInt(1);//ResultSet结果集中的第一个参数就是记录数，其实结果集中只有一个参数
        }
        return count;
    }

    public int getUserCount() throws SQLException {
        int count = 0;
        String sql="SELECT count(*) FROM ps_user";
        ResultSet rs= executeQuery(sql);
        if(rs.next()){
            count=rs.getInt(1);//ResultSet结果集中的第一个参数就是记录数，其实结果集中只有一个参数
        }
        return count;
    }

}
