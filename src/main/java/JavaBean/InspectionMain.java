package JavaBean;

import java.sql.Date;
import java.util.List;

/**
 *  巡检任务  主类
 *
 *
 */
public class InspectionMain {
    //巡检任务编号
    private String inspectionTaskCode;
    //巡检任务名称
    private String inspectionTaskName;
    //线路ID
    private int lineId;
    //线路编号
    private String lineCode;
    //线路名称
    private String lineName;
    //起始杆号ID
    private int startPole;
    //起始杆号编码
    private String startPoleCode;
    //终止杆号ID
    private int endPole;
    //终止杆号编码
    private String endPoleCode;
    //下发人Code
    private String issuedByCode;
    //下发人姓名
    private String issuedByName;
    //下发时间
    private Date issuedTime;
    //任务状态
    private int taskStatus;
    //任务状态名称
    private String taskStatusName;
    //任务完成时间
    private String finishTime;
    //是否取消 1：是 0：否
    private int isCancel;
    //备注
    private String common;
    //创建时间
    private String creationTime;
    //创建人
    private String createdBy;
    //最后修改时间
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
