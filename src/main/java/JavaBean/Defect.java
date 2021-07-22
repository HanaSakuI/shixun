package JavaBean;

public class Defect {
    private String solveTaskCode;//消缺任务编号
    private String solveTaskName;//消缺任务名称
    private int workDocType;//工作单据ID
    private String workDocTypeName;//工作单据Name
    private String taskDesc;//任务描述
    private String taskManagerCode;//任务负责人ID
    private String taskManagerName;//任务负责人Name
    private int taskStatus;//任务状态ID
    private String taskStatusName;//任务状态Name
    private String finishTime;//任务完成时间
    private String common;//备注
    private String managerSuggestion;//负责人审查意见
    private String taskFinishDesc;//完成情况描述
    private String taskFinishReport;//工作终结报告
    private String taskNotes;//工作间断延期记载
    private String issuedByCode;//下发人ID
    private String issuedByName;//下发人Name
    private String issuedTime;//任务下发时间
    private String issuedSuggestion;//下发人审查意见
    private int isCancel;//是否取消
    private String creationTime;//创建时间
    private String createdBy;//创建者
    private String lastUpdateTime;//最后修改时间
    private String solveName;//消缺员

    public Defect(){

    }

    public String getSolveTaskCode() {
        return solveTaskCode;
    }

    public void setSolveTaskCode(String solveTaskCode) {
        this.solveTaskCode = solveTaskCode;
    }

    public String getSolveTaskName() {
        return solveTaskName;
    }

    public void setSolveTaskName(String solveTaskName) {
        this.solveTaskName = solveTaskName;
    }

    public int getWorkDocType() {
        return workDocType;
    }

    public void setWorkDocType(int workDocType) {
        this.workDocType = workDocType;
    }

    public String getWorkDocTypeName() {
        return workDocTypeName;
    }

    public void setWorkDocTypeName(String workDocTypeName) {
        this.workDocTypeName = workDocTypeName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskManagerCode() {
        return taskManagerCode;
    }

    public void setTaskManagerCode(String taskManagerCode) {
        this.taskManagerCode = taskManagerCode;
    }

    public String getTaskManagerName() {
        return taskManagerName;
    }

    public void setTaskManagerName(String taskManagerName) {
        this.taskManagerName = taskManagerName;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getManagerSuggestion() {
        return managerSuggestion;
    }

    public void setManagerSuggestion(String managerSuggestion) {
        this.managerSuggestion = managerSuggestion;
    }

    public String getTaskFinishDesc() {
        return taskFinishDesc;
    }

    public void setTaskFinishDesc(String taskFinishDesc) {
        this.taskFinishDesc = taskFinishDesc;
    }

    public String getTaskFinishReport() {
        return taskFinishReport;
    }

    public void setTaskFinishReport(String taskFinishReport) {
        this.taskFinishReport = taskFinishReport;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

    public String getIssuedByCode() {
        return issuedByCode;
    }

    public void setIssuedByCode(String issuedByCode) {
        this.issuedByCode = issuedByCode;
    }

    public String getIssuedByName() {
        return issuedByName;
    }

    public void setIssuedByName(String issuedByName) {
        this.issuedByName = issuedByName;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getIssuedSuggestion() {
        return issuedSuggestion;
    }

    public void setIssuedSuggestion(String issuedSuggestion) {
        this.issuedSuggestion = issuedSuggestion;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getSolveName() {
        return solveName;
    }
    public void setSolveName(String solveName) {
        this.solveName = solveName;
    }
}
