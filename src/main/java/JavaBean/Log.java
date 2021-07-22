package JavaBean;

public class Log {
    private int id;
    private String userCode;
    private String userName;
    private String operateInfo;
    private String operateDatetime;

    public Log(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperateInfo() {
        return operateInfo;
    }

    public void setOperateInfo(String operateInfo) {
        this.operateInfo = operateInfo;
    }

    public String getOperateDatetime() {
        return operateDatetime;
    }

    public void setOperateDatetime(String operateDatetime) {
        this.operateDatetime = operateDatetime;
    }
}
