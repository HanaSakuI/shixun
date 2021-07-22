package JDBC;

import JavaBean.PsSolverDetail;
import JavaBean.PsSystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yt
 */
public class PsSolverDetailDao extends BaseDao {
    /**
     * 展示任务分配人员情况
     *
     * @param solveTaskCode 任务编号
     * @param solverStatus  任务状态
     * @return psSolverDetailList
     */
    public List<PsSolverDetail> getSolverBySolverStatus(String solveTaskCode, int solverStatus) throws SQLException {
        String sql = "";
        if (solverStatus == 0) {
            sql = "SELECT * FROM ps_solver_detail where solverStatus = " + solverStatus;
        } else {
            sql = "SELECT * FROM ps_solver_detail where solveTaskCode = '" + solveTaskCode + "'";
        }


        ResultSet rs = executeQuery(sql);
        List<PsSolverDetail> psSolverDetailList = new ArrayList<>();

        while (rs.next()) {
            String solverCode = rs.getString("solverCode");
            String solverName = rs.getString("solverName");

            PsSolverDetail psSolverDetail = new PsSolverDetail(solverCode, solverName);
            psSolverDetailList.add(psSolverDetail);
        }

        closeAll();
        return psSolverDetailList;
    }

    /**
     * 通过solveTaskCode获取solverStatus
     *
     * @param solverCode 角色编号
     * @return solverStatus 0为未分配工作，1为已分配工作
     */
    public int getSolverStatusBySolveTaskCode(String solverCode) throws SQLException {
        int solverStatus = 0;
        String sql = "SELECT * FROM ps_solver_detail where solverCode = '" + solverCode + "'";

        ResultSet rs = executeQuery(sql);

        while (rs.next()) {
            solverStatus = rs.getInt("solverStatus");
        }

        closeAll();
        return solverStatus;
    }

    /**
     * 根据solverStatus,solveTaskCode,solverCode更新表
     *
     * @param solverCode    角色编号
     * @param solveTaskCode 任务编号
     * @param solverStatus  角色状态
     * @return row
     */
    public int updateSolverStatusAndSolveTaskCodeAndSolverCode(String solverCode, String solveTaskCode, int solverStatus) {
        String sql = "update ps_solver_detail set solveTaskCode = ?, solverStatus = ?, lastUpdateTime = now() where solverCode = '" + solverCode + "'";

        Object[] param = {
                solveTaskCode,
                solverStatus
        };

        int row = executeUpdate(sql, param);
        closeAll();
        return row;
    }

    /**
     * 分配或取消任务
     *
     * @param solverCodes   角色编号字符串
     * @param solveTaskCode 任务编号
     * @param flag          1为选择，0为取消
     */
    public void changeSolverStatusAndAssignments(String solverCodes, String solveTaskCode, int flag) throws SQLException {
        String[] solverCode = solverCodes.split(",");

        for (int i = 0; i < solverCode.length; i++) {
            if (flag == 1) {
                if (getSolverStatusBySolveTaskCode(solverCode[i]) == 0) {
                    updateSolverStatusAndSolveTaskCodeAndSolverCode(solverCode[i], solveTaskCode, 1);
                }
            } else {
                if (getSolverStatusBySolveTaskCode(solverCode[i]) == 1) {
                    updateSolverStatusAndSolveTaskCodeAndSolverCode(solverCode[i], null, 0);
                }
            }

        }
    }
}
