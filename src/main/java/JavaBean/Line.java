package JavaBean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 线路管理实体类 Line
 */
public class Line {

    //线路编号
    private String lineCode;
    //线路名称
    private String lineName;
    //线路长度
    private double lineLength;
    //回路长度
    private double backLength;
    //投运时间
    private Date productDate;
    //电压等级
    private String voltageLevel;
    //起始杆号ID
    private int startPole;
    //起始杆号编码Code
    private String startPoleCode;
    //终止杆号ID
    private int endPole;
    //终止杆号编码Code
    private String endPoleCode;
    //塔基数
    private int towerBaseNum;
    //维修单位
    private String maintenanceCompany;
    //运行状态
    private int runningStatus;
    //运行状态名称
    private String runningStatusName;
    //备注
    private String common;
    //是否启用 1：启用  0：停用
    private int isStart;
    //创建时间
    private String creationTime;
    //创建人
    private String createdBy;
    //最后修改时间
    private String lastUpdateTime;

    private  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private java.util.Date date = new java.util.Date();

    public Line(){}

    public Line(String lineCode,String lineName,String startPoleCode,String endPoleCode,
                int towerBaseNum,int runningStatus,int isStart){
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.startPoleCode = startPoleCode;
        this.endPoleCode = endPoleCode;
        this.towerBaseNum = towerBaseNum;
        this.runningStatus = runningStatus;
        this.isStart = isStart;
    }

    public Line(String lineCode,String lineName,double lineLength,double backLength,
                Date productDate ,String voltageLevel, String startPoleCode,String endPoleCode,
                int towerBaseNum,String common,int isStart){
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.startPoleCode = startPoleCode;
        this.endPoleCode = endPoleCode;
        this.towerBaseNum = towerBaseNum;
        this.lineLength = lineLength;
        this.backLength = backLength;
        this.productDate = productDate;
        this.voltageLevel = voltageLevel;
        this.common = common;
        this.isStart = isStart;
    }

    public Line(String lineCode,String lineName,double lineLength,double backLength,
           Date productDate,String voltageLevel, int startPole, String startPoleCode,
           int endPole, String endPoleCode, int towerBaseNum,String maintenanceCompany,
            int runningStatus,String runningStatusName, String common,int isStart,
          String creationTime, String createdBy,String lastUpdateTime){
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.lineLength = lineLength;
        this.backLength = backLength;
        this.productDate = productDate;
        this.voltageLevel = voltageLevel;
        this.startPole = startPole;
        this.endPole = endPole;
        this.startPoleCode = startPoleCode;
        this.endPoleCode = endPoleCode;
        this.towerBaseNum = towerBaseNum;
        this.maintenanceCompany =maintenanceCompany;
        this.runningStatusName =runningStatusName;
        this.common = common;
        this.creationTime = creationTime;
        this.lastUpdateTime = lastUpdateTime;
        this.createdBy = createdBy;
        this.runningStatus = runningStatus;
        this.isStart = isStart;
    }

    public String getLineCode() {
        return lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public double getLineLength() {
        return lineLength;
    }

    public double getBackLength() {
        return backLength;
    }

    public Date getProductDate() {
        return productDate;
    }

    public String getVoltageLevel() {
        return voltageLevel;
    }

    public int getStartPole() {
        return startPole;
    }

    public String getStartPoleCode() {
        return startPoleCode;
    }

    public int getEndPole() {
        return endPole;
    }

    public String getEndPoleCode() {
        return endPoleCode;
    }

    public int getTowerBaseNum() {
        return towerBaseNum;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public int getRunningStatus() {
        return runningStatus;
    }

    public String getRunningStatusName() {
        return runningStatusName;
    }

    public String getCommon() {
        return common;
    }

    public int getIsStart() {
        return isStart;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }
}
