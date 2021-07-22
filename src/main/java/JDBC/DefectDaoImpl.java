package JDBC;

import JavaBean.Defect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefectDaoImpl extends BaseDao{
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
