package JavaBean;

/**
 * @author yt
 * 表ps_solver_detail的实体类
 */
public class PsSolverDetail {
    private int id;
    private int psId;
    private String solveTaskCode;
    private String solverCode;
    private String solverName;
    private String creationTime;
    private String createdBy;
    private String lastUpdateTime;
    private int solverStatus;

    public PsSolverDetail() {

    }


    public PsSolverDetail(String solverCode, String solverName) {
        this.solverCode = solverCode;
        this.solverName = solverName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPsId() {
        return psId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }

    public String getSolveTaskCode() {
        return solveTaskCode;
    }

    public void setSolveTaskCode(String solveTaskCode) {
        this.solveTaskCode = solveTaskCode;
    }

    public String getSolverCode() {
        return solverCode;
    }

    public void setSolverCode(String solverCode) {
        this.solverCode = solverCode;
    }

    public String getSolverName() {
        return solverName;
    }

    public void setSolverName(String solverName) {
        this.solverName = solverName;
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

    public int getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(int solverStatus) {
        this.solverStatus = solverStatus;
    }
}
