package JDBC;
import JavaBean.Solve;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolveDao extends BaseDao{
    public List<Solve> getSolveBycondition() throws SQLException {
        String sql="SELECT * FROM ps_solver_detail where solveStatus = 0";

        ResultSet rs= executeQuery(sql);
        List<Solve>list=new ArrayList<Solve>();
        while(rs.next()){
        Solve solve = new Solve();
        solve.setSolverName((rs.getString("solverName")));
        solve.setSolveTaskCode(rs.getString("solveTaskCode"));
        list.add(solve);
        }
        closeAll();
        return list;

    }
}
