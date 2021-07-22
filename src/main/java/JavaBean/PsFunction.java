package JavaBean;

/**
 * @author yt
 * 表ps_function的实体类
 */
public class PsFunction {
    private int id;
    private String functionCode;
    private String functionName;
    private String creationTime;
    private String createdBy;
    private String lastUpdateTime;
    private String functionUrl;
    private int isStart;
    private String parentId;
    private String checkboxId;

    public PsFunction() {

    }

    public PsFunction(String functionCode, String checkboxId) {
        this.functionCode = functionCode;
        this.checkboxId = checkboxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
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

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCheckboxId() {
        return checkboxId;
    }

    public void setCheckboxId(String checkboxId) {
        this.checkboxId = checkboxId;
    }
}
