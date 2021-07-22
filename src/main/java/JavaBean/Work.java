package JavaBean;

public class Work {
    private String workCode;//任务编号
    private String workType;//任务类型
    private String workName;//任务名称
    private String workTime;//任务到达时间
    private String workStatus;//任务完成状态

    public Work(){

    }

    public String getWorkType() {
        return workType;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
