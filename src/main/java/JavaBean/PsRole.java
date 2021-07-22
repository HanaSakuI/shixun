package JavaBean;

/**
 * @author yt
 * 表ps_role的实体类
 */
public class PsRole {
    private int id;
    private String roleId;
    private String roleName;
    private String creationTime;
    private String createdBy;
    private String lastUpdateTime;
    private int isStart;

    public PsRole() {

    }

    /***
     * 添加角色
     * */
    public PsRole(String roleId, String roleName, String createdBy, String creationTime, int isStart) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdBy = createdBy;
        this.creationTime = creationTime;
        this.isStart = isStart;
    }

    /***
     * 编辑角色
     * lastUpdateTime为当前时间
     * */
    public PsRole(String roleId, String roleName, String lastUpdateTime, int isStart) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.lastUpdateTime = lastUpdateTime;
        this.isStart = isStart;
    }

    /***
     * 展示角色
     * */
    public PsRole(int id, String roleId, String roleName, String createdBy, String lastUpdateTime, int isStart) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdBy = createdBy;
        this.lastUpdateTime = lastUpdateTime;
        this.isStart = isStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }
}
