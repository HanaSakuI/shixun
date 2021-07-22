package JavaBean;

public class Bug {
    private String taskId;
    private String poleCode;
    private String lineCode;
    private String bugLevelName;
    private String bugTypeName;
    private String bugDesc;
    private String discovererName;
    private String discoverTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPoleCode() {
        return poleCode;
    }

    public void setPoleCode(String poleCode) {
        this.poleCode = poleCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getBugLevelName() {
        return bugLevelName;
    }

    public void setBugLevelName(String bugLevelName) {
        this.bugLevelName = bugLevelName;
    }

    public String getBugTypeName() {
        return bugTypeName;
    }

    public void setBugTypeName(String bugTypeName) {
        this.bugTypeName = bugTypeName;
    }

    public String getBugDesc() {
        return bugDesc;
    }

    public void setBugDesc(String bugDesc) {
        this.bugDesc = bugDesc;
    }

    public String getDiscovererName() {
        return discovererName;
    }

    public void setDiscovererName(String discovererName) {
        this.discovererName = discovererName;
    }

    public String getDiscoverTime() {
        return discoverTime;
    }

    public void setDiscoverTime(String discoverTime) {
        this.discoverTime = discoverTime;
    }
}
