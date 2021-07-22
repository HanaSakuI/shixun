package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yt
 * 实体类PsRolePermission的Dao
 */
public class PsRolePermissionDao extends BaseDao {
    /**
     * 根据roleId获取functionCode
     *
     * @param roleId 角色ID
     * @return functionCode
     */
    public String getFunctionCodeByRoleId(String roleId) throws SQLException {
        String sql = "SELECT * FROM ps_role_permission where roleId = '" + roleId + "'";
        String functionCode = "";

        ResultSet rs = executeQuery(sql);

        while (rs.next()) {
            functionCode = rs.getString("functionCode");
        }

        closeAll();
        return functionCode;
    }

    /**
     * 根据roleId修改functionCode
     *
     * @param roleId       角色ID
     * @param functionCode 权限编号
     * @return row
     */
    public int updateFunctionCodeByRoleId(String roleId, String functionCode) {
        String sql = "update ps_role_permission set functionCode = ? where roleId = '" + roleId + "'";

        Object[] param = {
                functionCode
        };

        int row = executeUpdate(sql, param);
        closeAll();
        return row;
    }
}
