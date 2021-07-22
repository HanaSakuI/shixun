package JDBC;

import JavaBean.ProjectLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * 数据库连接类
 *
 */
public class JDBC {
    private Connection connection;
    private ProjectLogger projectLogger = new ProjectLogger();
    private String username = "root";
    private String password = "root";
    private String success = "Connection to MYSQL for Shixunweb Success";
    private String error ="Connection to MYSQL for Shixunweb Error";
    private String url = "jdbc:mysql://116.62.36.247:3306/shixunweb?useUnicode=true&characterEncoding=utf8";
    private String className = JDBC.class.getName();
    public JDBC(){}
    /**
     *  this class for get h2-sql connected,and return this connection
     * @return connection
     */
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
//            projectLogger.getLoggerMessage(className,success);
            return connection;
        } catch (Exception e) {
            projectLogger.getLoggerMessage(className,error);
            e.printStackTrace();
        }
        return null;
    }
}
