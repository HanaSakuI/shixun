package JDBC;

import JavaBean.Bug;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BugDaoImpl extends BaseDao{

    public List<Bug> getBug(String solveTaskCode) throws SQLException {
        String sql = "select * from ps_inspectiontask_detail where taskId = '" + solveTaskCode + "'";
        ResultSet rs = executeQuery(sql);
        List<Bug> list = new ArrayList<>();
        while(rs.next()){
            Bug bug = new Bug();
            bug.setLineCode(rs.getString("lineCode"));
            bug.setPoleCode(rs.getString("poleCode"));
            bug.setBugLevelName(rs.getString("bugLevelName"));
            bug.setBugTypeName(rs.getString("bugTypeName"));
            bug.setBugDesc(rs.getString("bugDesc"));
            bug.setDiscovererName(rs.getString("discovererName"));
            bug.setDiscoverTime(rs.getTimestamp("discoverTime").toLocaleString());
            list.add(bug);
        }
        closeAll();
        return list;
    }
}
