package JDBC;

import JavaBean.Work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkDao extends BaseDao{

    public List<Work> getXQWork(String userCode) throws SQLException {
        String sql = "select * from ps_solver_detail , ps_solvetask_main where ps_solver_detail.solveTaskCode = ps_solvetask_main.solveTaskCode and solveCode = '" + userCode + "' and taskStatus = 1 " ;
        ResultSet rs = executeQuery(sql);
        List<Work> list = new ArrayList<>();
        while (rs.next()){
            Work work = new Work();
            work.setWorkType("消缺任务");
            work.setWorkCode(rs.getString("solveTaskCode"));
            System.out.println(work.getWorkCode());
            work.setWorkName(rs.getString("solveTaskName"));
            work.setWorkTime(rs.getTimestamp("creationTime").toGMTString());
            work.setWorkStatus(rs.getString("taskStatus"));
            list.add(work);
        }
        closeAll();
        return list;
    }

    public List<Work> getXJWork(String userCode) throws SQLException {
        String sql = "select * from ps_inspector_detail , ps_inspectiontask_main where ps_inspector_detail.taskId = ps_inspectiontask_main.inspectionTaskCode and inspectorCode = '" + userCode + "' and taskStatus = 1";
        System.out.println(sql);
        ResultSet rs = executeQuery(sql);
        List<Work> list = new ArrayList<>();
        while (rs.next()){
            Work work = new Work();
            work.setWorkType("巡检任务");
            work.setWorkCode(rs.getString("taskId"));
            System.out.println(work.getWorkCode());
            work.setWorkName(rs.getString("inspectionTaskName"));
//            work.setWorkTime(rs.getTimestamp("issuedTime").toGMTString());
            work.setWorkTime(rs.getDate("issuedTime").toString());
            System.out.println(work.getWorkTime());
            work.setWorkStatus(rs.getString("taskStatus"));
            list.add(work);
        }
        closeAll();
        return list;
    }

    public int handleWork(String workCode , String roleId) {
        String sql = "";
        if(roleId.equals("ps_role03")){
            sql = "update ps_inspectiontask_main set taskStatus = 2 , taskStatusName = '执行中' where inspectionTaskCode = ?";
        }else if(roleId.equals("ps_role04")){
            sql = "update ps_solvetask_main set taskStatus = 3 , taskStatusName = '执行中' where solveTaskCode = ?";
        }
        System.out.println(sql);

        Object []objects = {
                workCode
        };

        int row = executeUpdate(sql,objects);
        return row;
    }
}
