package JavaBean;

public class InspectionDetail {
    private String isBugName;
    private String taskId;
    private String lineCode;
    private String poleCode;
    //有无bug
    private int isBug;
    private int bugType;
    private String bugTypeName;
    private int bugLevel;
    private String bugLevelName;
    private String common;
    //巡检时间
    private String inspectTime;
    //巡检员
    private String inspector;
    //发现人
    private String discoverName;
    //发现时间
    private String discoverTime;
    //下发人
    private String createdBy;
    //下发时间
    private String creationTime;
    //完好率
    private double intactRate;

    public InspectionDetail(){}
    public InspectionDetail(String taskId,String lineCode,String poleCode,int bugType,int bugLevel,
           String discoverTime,String discoverName, double intactRate,String common){
            this.taskId = taskId;
            this.lineCode = lineCode;
            this.poleCode = poleCode;
            this.bugType = bugType;
            this.bugLevel = bugLevel;
            this.discoverTime = discoverTime;
            this.discoverName = discoverName;
            this.intactRate = intactRate;
            this.common = common;
    }

    public InspectionDetail(String lineCode,String poleCode,String isBugName,String bugTypeName,
                            String bugLevelName,String common,String inspectTime,String inspector,
                            String discoverName,String discoverTime, String createdBy,String creationTime,double intactRate ){
        this.bugTypeName = bugTypeName;
        this.isBugName = isBugName;
        this.bugLevelName=bugLevelName;
        this.inspectTime= inspectTime;
        this.inspector=inspector;
        this.createdBy=createdBy;
        this.creationTime=creationTime;
        this.lineCode = lineCode;
        this.poleCode = poleCode;
        this.discoverTime = discoverTime;
        this.discoverName = discoverName;
        this.common = common;
        this.intactRate = intactRate;
    }

    public InspectionDetail(String taskId, String lineCode, String poleCode, String bugLevel, String bugType,
                            String discovererName, String discoverTime, String createdBy, String creationTime,
                            double intactRate, String bugDesc) {
        this.taskId = taskId;
        this.lineCode = lineCode;
        this.poleCode = poleCode;
        this.bugTypeName = bugType;
        this.discoverTime = discoverTime;
        this.discoverName = discovererName;
        this.common = bugDesc;
        this.createdBy=createdBy;
        this.creationTime = creationTime;
        this.intactRate = intactRate;
        this.bugLevelName= bugLevel;

    }

    public int getIsBug() {
        return isBug;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public String getInspector() {
        return inspector;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public String getPoleCode() {
        return poleCode;
    }

    public int getBugType() {
        return bugType;
    }

    public int getBugLevel() {
        return bugLevel;
    }

    public String getDiscoverTime() {
        return discoverTime;
    }

    public String getDiscoverName() {
        return discoverName;
    }

    public double getIntactRate() {
        return intactRate;
    }

    public String getCommon() {
        return common;
    }

    public String getIsBugName() {
        return isBugName;
    }

    public String getBugTypeName() {
        return bugTypeName;
    }

    public String getBugLevelName() {
        return bugLevelName;
    }
}
