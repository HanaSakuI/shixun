package JDBC;

import JavaBean.Log;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogDaoImpl extends BaseDao{
    public List<Log> getLogByUserCode(String userCode) throws SQLException {
        String sql = "select * from ps_logs where userCode = '" + userCode + "'";
        ResultSet rs = executeQuery(sql);
        List<Log> list = new ArrayList<Log>();
        while(rs.next()){
            Log log = new Log();
            log.setId(rs.getInt("id"));
            log.setUserCode(rs.getString("userCode"));
            log.setUserName(rs.getString("userCode"));
            log.setOperateInfo(rs.getString("operateInfo"));
            System.out.println(log.getOperateInfo());
            log.setOperateDatetime(rs.getTimestamp("operateDatetime").toLocaleString());
            list.add(log);
        }
        closeAll();
        return list;
    }

    public int addLog(String flag , String userCode ,String userName) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String operateDatetime = df.format(new Date());
        String operateInfo = "";

        if(flag.equals("login")){
            operateInfo = new String("登录系统");
        }else if(flag == "logout"){
            operateInfo = "退出系统";
        }
        String sql = "insert into ps_logs(userCode,userName,operateInfo,operateDatetime) values(?,?,?,?)";
        Object []objects ={
                userCode,
                userName,
                operateInfo,
                operateDatetime
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

    public List<Log> searchLogByTime(String beginTime, String endTime , String userCode) throws ParseException, SQLException {
        System.out.println("searchLogByTime");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "select * from ps_logs where operateDatetime >= '" + beginTime + "' and operateDatetime <= '"
                + endTime + "' and userCode = '" + userCode + "'" ;
        System.out.println(sql);
        ResultSet rs = executeQuery(sql);
        List<Log> list = new ArrayList<Log>();
        while(rs.next()){
            Log log = new Log();
            log.setId(rs.getInt("id"));
            log.setUserCode(rs.getString("userCode"));
            log.setUserName(rs.getString("userCode"));
            log.setOperateInfo(rs.getString("operateInfo"));
            System.out.println(log.getOperateInfo());
            log.setOperateDatetime(rs.getTimestamp("operateDatetime").toLocaleString());
            list.add(log);
        }
        closeAll();
        return list;
    }
}
