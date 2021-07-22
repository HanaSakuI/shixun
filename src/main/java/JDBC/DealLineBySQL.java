package JDBC;

import JavaBean.Line;
import JavaBean.ProjectLogger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class DealLineBySQL {
    private List<Line> line = new ArrayList<>();
    private JDBC jdbc = new JDBC();
    private String className = DealLineBySQL.class.getName();
    private ProjectLogger projectLogger = new ProjectLogger();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String selectAll = "select * from ps_line_info";
    private String selectConcat = "select * from ps_line_info where CONCAT(lineCode,lineName,startPoleCode,endPoleCode,towerBaseNum) like ";
    private String insert = "insert into ps_line_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String delete = "delete from ps_line_info where lineCode = ";
    private String getStatus = "select isStart from ps_line_info where lineCode=";
    private String updateStatus = "UPDATE ps_line_info set isStart =";
    public DealLineBySQL(){}


    /**
     *   增加一条line
     * @param line
     * @return
     */
    public int addLine(Line line) throws ClassNotFoundException, SQLException {
        connection = new JDBC().getConnection();
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setInt(1,0);
        ps.setString(2,line.getLineCode());
        ps.setString(3,line.getLineName());
        ps.setDouble(4,line.getLineLength());
        ps.setDouble(5,line.getBackLength());
        ps.setDate(6,line.getProductDate());
        ps.setString(7,line.getVoltageLevel());
        ps.setInt(8,line.getStartPole());
        ps.setString(9,line.getStartPoleCode());
        ps.setInt(10,line.getEndPole());
        ps.setString(11,line.getEndPoleCode());
        ps.setInt(12,line.getTowerBaseNum());
        ps.setString(13,line.getMaintenanceCompany());
        ps.setInt(14,line.getRunningStatus());
        ps.setString(15,line.getRunningStatusName());
        ps.setString(16,line.getCommon());
        ps.setInt(17,line.getIsStart());
        ps.setString(18,line.getCreationTime());
        ps.setString(19,line.getCreatedBy());
        ps.setString(20,line.getLastUpdateTime());
       return ps.executeUpdate();
    }

    /**
     *    获取当前时间
     * @return
     */
    public String getLastUpdateTime(){
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         java.util.Date date = new java.util.Date();
         return df.format(date);
    }

    /**
     *  获取所有表内的内容，并存储在list中
     * @return line
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Line> getLineList() throws ClassNotFoundException, SQLException {
             projectLogger.getLoggerMessage(className,"Use getLineList ...");
             connection = jdbc.getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery(selectAll);
             while(resultSet.next()){
                 line.add(new Line(resultSet.getString("lineCode"),resultSet.getString("lineName"),
                         resultSet.getDouble("lineLength"), resultSet.getDouble("backLength"),
                         resultSet.getDate("productDate"),resultSet.getString("voltageLevel"),
                         resultSet.getInt("startPole"), resultSet.getString("startPoleCode"),
                         resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                         resultSet.getInt("towerBaseNum"),resultSet.getString("maintenanceCompany"),
                         resultSet.getInt("runningStatus"),resultSet.getString("runningStatusName"),
                         resultSet.getString("common"),resultSet.getInt("isStart"),
                         resultSet.getString("creationTime"), resultSet.getString("createdBy"), resultSet.getString("lastUpdateTime")));
             }
                closeAll();
                projectLogger.getLoggerMessage(className,"Use getLineList Success");
             return  line;
    }

    /**
     *   模糊查询
     * @param input
     * @param searchRunStatus
     * @param searchStartStatus
     * @return list
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public  List<Line> getSelectLineList(String input,String searchRunStatus,String searchStartStatus) throws ClassNotFoundException, SQLException {
        connection = jdbc.getConnection();
        statement = connection.createStatement();
        selectConcat += "'%"+input +"%' ";
        if (!searchRunStatus.equals("2")){
            selectConcat += " and runningStatus = "+searchRunStatus;
        }
        if (!searchStartStatus.equals("2")){
            selectConcat += " and isStart = "+searchStartStatus;
        }
        resultSet = statement.executeQuery(selectConcat);
        while(resultSet.next()){
            line.add(new Line(resultSet.getString("lineCode"),resultSet.getString("lineName"),
                    resultSet.getDouble("lineLength"), resultSet.getDouble("backLength"),
                    resultSet.getDate("productDate"),resultSet.getString("voltageLevel"),
                    resultSet.getInt("startPole"), resultSet.getString("startPoleCode"),
                    resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                    resultSet.getInt("towerBaseNum"),resultSet.getString("maintenanceCompany"),
                    resultSet.getInt("runningStatus"),resultSet.getString("runningStatusName"),
                    resultSet.getString("common"),resultSet.getInt("isStart"),
                    resultSet.getString("creationTime"), resultSet.getString("createdBy"), resultSet.getString("lastUpdateTime")));
        }
        closeAll();
        return line;
    }

    /**
     *  删除一条line数据
     *
     * @return boolean
     */
    public int deleteByLineCode(String lineCode) throws ClassNotFoundException, SQLException {
        connection = jdbc.getConnection();
        statement = connection.createStatement();
        delete +="'"+ lineCode + "'";
        int row = 0;
        row = statement.executeUpdate(delete);
        connection.close();
        statement.close();
        //大于 0 即删除成功
        return row;
    }

    /**
     *
     * 修改启动状态
     * @param lineCode
     * @return int
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int changeStatus(String lineCode) throws ClassNotFoundException, SQLException {
            connection = jdbc.getConnection();
            statement = connection.createStatement();
            getStatus += "'"+lineCode+"'";
            resultSet = statement.executeQuery(getStatus);
            resultSet.next();
            int status = resultSet.getInt("isStart");
            int row = 0;
            if (status == 1){
                updateStatus += 0 +" ,lastUpdateTime= '"+getLastUpdateTime()+"'  where lineCode ='"+lineCode+"'";
                row = statement.executeUpdate(updateStatus);
            }else if (status == 0){
                updateStatus += 1 +" ,lastUpdateTime= '"+getLastUpdateTime()+"' where lineCode ='"+lineCode+"'";
                 row = statement.executeUpdate(updateStatus);
            }else {
                return -1;
            }
            closeAll();
            return row;
        }

    /**
     *  根据线路编号获取线路信息
     *
     * @param lineCode
     * @return line
     * @throws ClassNotFoundException
     * @throws SQLException
     */
        public Line getLineMassage(String lineCode) throws ClassNotFoundException, SQLException {
                Line line;
            connection = jdbc.getConnection();
            statement = connection.createStatement();
            selectAll += " where lineCode='"+lineCode+"'";
             resultSet = statement.executeQuery(selectAll);
            resultSet.next();
            line = new Line(resultSet.getString("lineCode"),resultSet.getString("lineName"),
                    resultSet.getDouble("lineLength"),resultSet.getDouble("backLength"),
                    resultSet.getDate("productDate"),resultSet.getString("voltageLevel"),
                    resultSet.getString("startPoleCode"),resultSet.getString("endPoleCode"),
                    resultSet.getInt("towerBaseNum"),resultSet.getString("common"),resultSet.getInt("isStart"));
            closeAll();
            return line;
        }

    /**
     *   修改Line信息
     *
     * @param line
     * @return int
     * @throws SQLException
     * @throws ClassNotFoundException
     */
        public int changeLineMessage(Line line) throws SQLException, ClassNotFoundException {
            connection = jdbc.getConnection();
            statement = connection.createStatement();
             String sql = "UPDATE ps_line_info set lineName='" +line.getLineName()+"',lineLength='"
                     +line.getLineLength()+"',backLength='"+line.getBackLength()+"',productDate='"+line.getProductDate()+"'," +
                     "voltageLevel='"+line.getVoltageLevel()+"',startPoleCode='"+line.getStartPoleCode()+"',endPoleCode='"+line.getEndPoleCode()+"'," +
                     "towerBaseNum='"+line.getTowerBaseNum()+"',common='"+line.getCommon()+"',isStart='"+line.getIsStart()+"',lastUpdateTime= '"+getLastUpdateTime()+"' where lineCode='"+line.getLineCode()+"'";
             int row = statement.executeUpdate(sql);
             connection.close();
             statement.close();
            return  row;
        }

    /**
     *  关闭所有连接类
     * @throws SQLException
     */
    public void closeAll() throws SQLException {
        if (!connection.isClosed()){
            connection.close();
        }
        if (!statement.isClosed()){
            statement.close();
        }
        if (!resultSet.isClosed()){
            resultSet.close();
        }
    }
}
