package JavaBean;

import java.sql.Date;
import java.util.List;

/**
 *  Ѳ������  ����
 *
 *
 */
public class InspectionMain {
    //Ѳ��������
    private String inspectionTaskCode;
    //Ѳ����������
    private String inspectionTaskName;
    //��·ID
    private int lineId;
    //��·���
    private String lineCode;
    //��·����
    private String lineName;
    //��ʼ�˺�ID
    private int startPole;
    //��ʼ�˺ű���
    private String startPoleCode;
    //��ֹ�˺�ID
    private int endPole;
    //��ֹ�˺ű���
    private String endPoleCode;
    //�·���Code
    private String issuedByCode;
    //�·�������
    private String issuedByName;
    //�·�ʱ��
    private Date issuedTime;
    //����״̬
    private int taskStatus;
    //����״̬����
    private String taskStatusName;
    //�������ʱ��
    private String finishTime;
    //�Ƿ�ȡ�� 1���� 0����
    private int isCancel;
    //��ע
    private String common;
    //����ʱ��
    private String creationTime;
    //������
    private String createdBy;
    //����޸�ʱ��
    private String lastUpdateTime;

    public InspectionMain(){}
    public InspectionMain(String inspectionTaskCode,String inspectionTaskName,String startPoleCode,String endPoleCode,
                          String issuedByName,Date issuedTime,int lineId,String common){
        this.inspectionTaskCode = inspectionTaskCode;
        this.inspectionTaskName = inspectionTaskName;
        this.startPoleCode = startPoleCode;
        this.endPoleCode = endPoleCode;
        this.issuedByName = issuedByName;
        this.issuedTime = issuedTime;
        this.lineId = lineId;
        this.common = common;
    }
    public InspectionMain(String inspectionTaskCode, String inspectionTaskName, int lineId,
                          String lineCode,String lineName, int startPole,String startPoleCode,
                          int endPole, String endPoleCode, String issuedByCode, String issuedByName,
                          Date issuedTime, int taskStatus,String taskStatusName, String finishTime,
                          int isCancel, String common,String creationTime,String createdBy, String lastUpdateTime){
            this.inspectionTaskCode = inspectionTaskCode;
            this.inspectionTaskName = inspectionTaskName;
            this.lineId = lineId;
            this.lineCode = lineCode;
            this.lineName = lineName;
            this.startPole = startPole;
            this.startPoleCode = startPoleCode;
            this.endPole = endPole;
            this.endPoleCode = endPoleCode;
            this.issuedByCode = issuedByCode;
            this.issuedByName = issuedByName;
            this.issuedTime = issuedTime;
            this.taskStatus = taskStatus;
            this.taskStatusName = taskStatusName;
            this.finishTime = finishTime;
            this.isCancel = isCancel;
            this.common = common;
            this.creationTime = creationTime;
            this.createdBy = createdBy;
            this.lastUpdateTime = lastUpdateTime;
    }

    public String getInspectionTaskCode() {
        return inspectionTaskCode;
    }

    public String getInspectionTaskName() {
        return inspectionTaskName;
    }

    public int getLineId() {
        return lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public String getLineName() {
        return lineName;
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

    public String getIssuedByCode() {
        return issuedByCode;
    }

    public String getIssuedByName() {
        return issuedByName;
    }

    public Date getIssuedTime() {
        return issuedTime;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public String getCommon() {
        return common;
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
