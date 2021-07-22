package JavaBean;

/**
 * @author yt
 * 表ps_systemconfig的实体类
 */
public class PsSystemConfig {
    private int id;
    private String configCode;
    private String configName;
    private int configValueId;
    private String configValueName;
    private int isStart;

    public PsSystemConfig() { }

    /***
     * 新增系统配置（上表）
     * */
    public PsSystemConfig(String configCode, String configName) {
        this.configCode = configCode;
        this.configName = configName;
    }

    /***
     * 新增配置参数（下表）
     * */
    public PsSystemConfig(int configValueId, String configValueName, int isStart) {
        this.configValueId = configValueId;
        this.configValueName = configValueName;
        this.isStart = isStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public int getConfigValueId() {
        return configValueId;
    }

    public void setConfigValueId(int configValueId) {
        this.configValueId = configValueId;
    }

    public String getConfigValueName() {
        return configValueName;
    }

    public void setConfigValueName(String configValueName) {
        this.configValueName = configValueName;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }
}
