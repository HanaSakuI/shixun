package JDBC;
import JavaBean.Defect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefectDao extends BaseDao{
    public List<Defect> getDefectAll(String pageNo) throws SQLException {
        System.out.println("DefectDao");
        int pageIndex=Integer.parseInt(pageNo);
        int beginStart=(pageIndex-1)*7;
        String sql="SELECT * FROM ps_solvetask_main limit "+beginStart+",7";
        ResultSet rs= executeQuery(sql);
        List<Defect>list=new ArrayList<Defect>();
        while(rs.next()){
          Defect defect = new Defect();
          defect.setSolveTaskCode(rs.getString("solveTaskCode"));
            System.out.println(defect.getSolveTaskCode());
          defect.setSolveTaskName(rs.getString("solveTaskName"));
          defect.setWorkDocTypeName(rs.getString("workDocTypeName"));
          defect.setIssuedByName(rs.getString("issuedByName"));
          defect.setIssuedTime(rs.getTimestamp("issuedTime").toLocaleString());
          defect.setTaskStatus(rs.getInt("taskStatus"));
          defect.setFinishTime(rs.getTimestamp("finishTime").toLocaleString());
            System.out.println(defect.getFinishTime());
          defect.setTaskStatus(rs.getInt("taskStatus"));
          defect.setIsCancel(rs.getInt("isCancel"));
          list.add(defect);
        }
        closeAll();
        return list;
    }

    public List<Defect> getDefectBycondition(String solveTaskCode, String taskStatus, String issuedByName, String startTime,String endTime) throws SQLException {
        String sql="SELECT * FROM ps_solvetask_main where 1=1";
        if (solveTaskCode != "") {
            sql += " and solveTaskCode like '%" + solveTaskCode + "%'";
        }

        if(!taskStatus.equals("4")){
            sql += " and taskStatus=" + taskStatus;
        }
        if (issuedByName != "") {
            sql += " and issuedByName like '%" + issuedByName +"%'";
        }
//        if(startTime!="" && endTime != ""){
//            sql += " and issuedTime>=" + startTime;
//            sql += " and issuedTime<=" + endTime;
//        }
        System.out.println(sql);
        ResultSet rs= executeQuery(sql);
        List<Defect>list=new ArrayList<Defect>();
        while(rs.next()){
            Defect defect = new Defect();
            defect.setSolveTaskCode(rs.getString("solveTaskCode"));
            defect.setSolveTaskName(rs.getString("solveTaskName"));
            defect.setWorkDocTypeName(rs.getString("workDocTypeName"));
            defect.setIssuedByName(rs.getString("issuedByName"));
            defect.setIssuedTime(rs.getTimestamp("issuedTime").toLocaleString());
            defect.setTaskStatus(rs.getInt("taskStatus"));
            defect.setFinishTime(rs.getTimestamp("finishTime").toLocaleString());
            defect.setTaskStatus(rs.getInt("taskStatus"));
            defect.setIsCancel(rs.getInt("isCancel"));
            list.add(defect);
        }
        closeAll();
        return list;

    }

    public int updateDefect(String solveTaskCode, String solveTaskName,String taskManagerName,String common,String issuedByName) {
        String sql="update ps_solvetask_main set solveTaskCode=?,solveTaskName=?,taskManagerName=?,common=?,issuedByName=? where solveTaskCode=?";
        Object[]param={
                solveTaskCode,
                solveTaskName,
                taskManagerName,
                common,
                issuedByName,
                solveTaskCode
        };
        int row=    executeUpdate(sql,param);
        closeAll();
        return row;
    }

    public int addDefect(String solveTaskCode,String solveTaskName,String workDocTypeName,String taskManagerName,String issuedByName,String taskDesc,String common) {
        //System.out.println(workDocTypeName);
        String sql="insert into ps_solvetask_main(solveTaskCode,solveTaskName,workDocTypeName,taskManagerName,issuedByName,issuedTime,taskDesc,common)" +
                "values(?,?,?,?,?,now(),?,?)";
        Object[]param={
                solveTaskCode,
                solveTaskName,
                workDocTypeName,
                taskManagerName,
                issuedByName,
                taskDesc,
                common,
        };
        int row=    executeUpdate(sql,param);
        closeAll();
        return row;
    }

    public Defect getDefectBysolveTaskCode(String solveTaskCode) throws SQLException{
        //System.out.println(ID);
        String sql="SELECT * FROM ps_solvetask_main WHERE ps_solvetask_main.solveTaskCode='"+solveTaskCode+"'";

        System.out.println(sql);
        ResultSet rs=  executeQuery(sql);
        Defect defect = null;
        if(rs.next()){
          defect = new Defect();
            defect.setSolveTaskCode(rs.getString("solveTaskCode"));
            defect.setSolveTaskName(rs.getString("solveTaskName"));
            defect.setWorkDocTypeName(rs.getString("workDocTypeName"));
            defect.setIssuedByName(rs.getString("issuedByName"));
            defect.setIssuedTime(rs.getTimestamp("issuedTime").toLocaleString());
            defect.setTaskStatus(rs.getInt("taskStatus"));
            defect.setFinishTime(rs.getTimestamp("finishTime").toLocaleString());
            defect.setTaskStatus(rs.getInt("taskStatus"));
            defect.setIsCancel(rs.getInt("isCancel"));
        }
        closeAll();
        return defect;
    }

    public int cancelDefect(String solveTaskCode) {
        String sql="update ps_solvetask_main set isCancel=1 where ps_solvetask_main.solveTaskCode='"+solveTaskCode+"'";
        int row=    executeUpdate(sql);
        closeAll();
        return row;
    }


    public List<Defect> getDefectReceipt(String solveTaskCode) throws SQLException {
        String sql = "select * from ps_solvetask_main,ps_solver_detail where ps_solvetask_main.solveTaskCode = '" + solveTaskCode + "' and ps_solver_detail.solveTaskCode = ps_solvetask_main.solveTaskCode";
        ResultSet rs = executeQuery(sql);
        List<Defect> list = new ArrayList<Defect>();
        while(rs.next()){
            Defect defect = new Defect();
            defect.setSolveTaskCode(solveTaskCode);
            defect.setSolveTaskName(rs.getString("solveTaskName"));
            defect.setTaskStatus(rs.getInt("taskStatus"));
            defect.setWorkDocTypeName(rs.getString("workDocTypeName"));
            defect.setIssuedByName(rs.getString("issuedByName"));
            defect.setIssuedTime(rs.getTimestamp("issuedTime").toLocaleString());
            defect.setTaskManagerName(rs.getString("taskManagerName"));
            defect.setTaskDesc(rs.getString("taskDesc"));
            defect.setSolveName(rs.getString("solverName"));
            defect.setFinishTime(rs.getTimestamp("finishTime").toLocaleString());
            defect.setManagerSuggestion(rs.getString("managerSuggestion"));
            defect.setIssuedSuggestion(rs.getString("issuedSuggestion"));
            defect.setIsCancel(rs.getInt("isCancel"));
            defect.setTaskFinishDesc(rs.getString("taskFinishDesc"));
            defect.setTaskNotes(rs.getString("taskNotes"));
            defect.setTaskFinishReport(rs.getString("taskFinishReport"));
            list.add(defect);
        }
        closeAll();
        return list;
    }


    public int submitDefect(String solveTaskCode, String taskNotes, String taskFinishReport) {
        String sql = "update ps_solvetask_main set taskNotes = ? ,taskFinishReport = ? where solveTaskCode = '" + solveTaskCode + "'";
        Object []objects = {
                taskNotes,
                taskFinishReport
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

    public int checkDefect(String solveTaskCode, String managerSuggestion, String issuedSuggestion, String isCancel) {
        String sql = "update ps_solvetask_main set managerSuggestion = ? , issuedSuggestion = ? , isCancel = ? where solveTaskCode = '" + solveTaskCode + "'";
        Object []objects = {
                managerSuggestion,
                issuedSuggestion,
                isCancel
        };
        int row = executeUpdate(sql,objects);
        return row;
    }

}
