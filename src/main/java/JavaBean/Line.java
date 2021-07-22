package JavaBean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * ��·����ʵ���� Line
 */
public class Line {

    //��·���
    private String lineCode;
    //��·����
    private String lineName;
    //��·����
    private double lineLength;
    //��·����
    private double backLength;
    //Ͷ��ʱ��
    private Date productDate;
    //��ѹ�ȼ�
    private String voltageLevel;
    //��ʼ�˺�ID
    private int startPole;
    //��ʼ�˺ű���Code
    private String startPoleCode;
    //��ֹ�˺�ID
    private int endPole;
    //��ֹ�˺ű���Code
    private String endPoleCode;
    //������
    private int towerBaseNum;
    //ά�޵�λ
    private String maintenanceCompany;
    //����״̬
    private int runningStatus;
    //����״̬����
    private String runningStatusName;
    //��ע
    private String common;
    //�Ƿ����� 1������  0��ͣ��
    private int isStart;
    //����ʱ��
    private String creationTime;
    //������
    private String createdBy;
    //����޸�ʱ��
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
