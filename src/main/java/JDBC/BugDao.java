package JDBC;

import JavaBean.Bug;
import JavaBean.Tower;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BugDao extends BaseDao{
    public List<Bug> getBugAll() throws SQLException {
        String sql="SELECT * FROM ps_inspectiontask_detail,ps_solvetask_main where ps_inspectiontask_detail.taskId=ps_solvetask_main.solveTaskCode";
        ResultSet rs= executeQuery(sql);
        List<Bug>list=new ArrayList<Bug>();
        while(rs.next()){
           Bug bug = new Bug();
           bug.setTaskId(rs.getString("taskId"));
           bug.setLineCode(rs.getString("lineCode"));
           bug.setPoleCode(rs.getString("poleCode"));
           bug.setBugTypeName(rs.getString("bugTypeName"));
           bug.setBugDesc(rs.getString("bugDesc"));
           bug.setDiscovererName(rs.getString("discovererName"));
           bug.setDiscoverTime(rs.getString("discoverTime"));
           bug.setBugLevelName(rs.getString("bugLevelName"));
           list.add(bug);
        }
        closeAll();
        return list;
    }
}
