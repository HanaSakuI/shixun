package JDBC;

import JavaBean.PsRole;
import com.alibaba.fastjson.JSON;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yt
 * PSRole的Dao
 */
public class PsRoleDao extends BaseDao {
    /**
     * 展示所有角色，并实现分页
     *
     * @param pageNo 页号
     * @return psRoleList
     */
    public List<PsRole> getRoleAll(String pageNo) throws SQLException {
        int pageIndex = Integer.parseInt(pageNo);
        int beginStart = (pageIndex - 1) * 7;

        String sql = "SELECT * FROM ps_role limit " + beginStart + ",7";
        ResultSet rs = executeQuery(sql);

        List<PsRole> psRoleList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String roleId = rs.getString("roleId");
            String roleName = rs.getString("roleName");
            String createdBy = rs.getString("createdBy");
            String lastUpdateTime = rs.getString("lastUpdateTime");
            int isStart = rs.getInt("isStart");

            PsRole psRole = new PsRole(id, roleId, roleName, createdBy, lastUpdateTime, isStart);
            psRoleList.add(psRole);
        }

        closeAll();
        return psRoleList;
    }

    /**
     * 根据角色名称和启用状态查询角色
     *
     * @param roleName 角色名称
     * @param isStart  启用状态
     * @return psRoleList
     */
    public List<PsRole> getRoleByRoleNameAndIsStart(String roleName, String isStart) throws SQLException {
        String sql = "SELECT * FROM ps_role where 1 = 1";

        if (!"0".equals(roleName)) {
            sql += " and roleName = '" + roleName + "'";
        }
        if (!"-1".equals(isStart)) {
            int status = Integer.parseInt(isStart);
            sql += " and isStart = " + status;
        }

        ResultSet rs = executeQuery(sql);
        List<PsRole> psRoleList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String roleId = rs.getString("roleId");
            String roleName1 = rs.getString("roleName");
            String createdBy = rs.getString("createdBy");
            String lastUpdateTime = rs.getString("lastUpdateTime");
            int isStart1 = rs.getInt("isStart");

            PsRole psRole = new PsRole(id, roleId, roleName1, createdBy, lastUpdateTime, isStart1);
            psRoleList.add(psRole);
        }

        closeAll();
        return psRoleList;
    }

    /**
     * 实现角色的添加
     *
     * @param roleId    角色ID
     * @param roleName  角色名称
     * @param createdBy 创建人
     * @param isStart   启用状态
     * @return row
     */
    public int addRole(String roleId, String roleName, String createdBy, int isStart) {
        String sql = "INSERT INTO ps_role(`roleId`, `roleName`, `creationTime`, `createdBy`, `lastUpdateTime`, `isStart`) VALUES (?, ?, now(), ?, now(), ?);";

        Object[] param = {
                roleId,
                roleName,
                createdBy,
                isStart
        };

        int row = executeUpdate(sql, param);
        closeAll();
        return row;
    }

    /**
     * 判断roleId或roleName是否存在
     *
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @return flag flag=0:两者均不存在，flag=1:roleId存在，flag=2:roleName存在，flag=3:两者均存在
     */
    public int judgeRoleIdAndRoleName(String roleId, String roleName) throws SQLException {
        String sql1 = "SELECT COUNT(*) FROM ps_role WHERE roleId = '" + roleId + "'";
        String sql2 = "SELECT COUNT(*) FROM ps_role WHERE roleName = '" + roleName + "'";

        ResultSet rs1 = executeQuery(sql1);
        ResultSet rs2 = executeQuery(sql2);

        int flag1 = 0;
        int flag2 = 0;

        if (rs1.next()) {
            if (rs1.getInt(1) != 0) {
                flag1 = 1;
            }
        }
        if (rs2.next()) {
            if (rs2.getInt(1) != 0) {
                flag2 = 2;
            }
        }

        closeAll();
        return flag1 + flag2;
    }

    /**
     * 判断roleId或roleName是否存在
     *
     * @param id       id
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @return flag flag=0:roleId和roleName与原参数不相同，flag=1:roleId与原参数相同，flag=2:roleName与原参数相同，flag=3:俩者与原参数都相同
     */
    public int judgeRoleIdAndRoleName(int id, String roleId, String roleName) throws SQLException {
        String sql = "SELECT * FROM ps_role WHERE id = " + id;

        ResultSet rs = executeQuery(sql);

        int flag1 = 0;
        int flag2 = 0;

        if (rs.next()) {
            if (rs.getString("roleId").equals(roleId)) {
                flag1 = 1;
            }
            if (rs.getString("roleName").equals(roleName)) {
                flag2 = 2;
            }
        }

        closeAll();
        return flag1 + flag2;
    }

    /**
     * 根据roleId获取roleName
     *
     * @param roleId 角色ID
     * @return roleName 角色名称
     */
    public PsRole getRoleNameByRoleId(String roleId) throws SQLException {
        String sql = "SELECT * FROM ps_role where roleId = '" + roleId + "'";

        ResultSet rs = executeQuery(sql);
        PsRole psRole = null;

        while (rs.next()) {
            int id = rs.getInt("id");
            String roleName = rs.getString("roleName");
            int isStart = rs.getInt("isStart");

            psRole = new PsRole(id, roleId, roleName, null, null, isStart);
        }

        closeAll();
        return psRole;
    }

    /**
     * 实现角色信息的更改
     *
     * @param id       id
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @param isStart  启用状态
     * @return row
     */
    public int updateRole(int id, String roleId, String roleName, int isStart) {
        String sql = "update ps_role set roleId = ?, roleName = ?, isStart = ?, lastUpdateTime = now() where id = " + id;

        Object[] param = {
                roleId,
                roleName,
                isStart
        };

        int row = executeUpdate(sql, param);
        closeAll();
        return row;
    }

    /**
     * 根据roleId删除role
     *
     * @param roleId 角色编号
     * @return row*/
    public int deleteRoleByRoleId(String roleId) {
        String sql = " delete from ps_role where roleId = '" + roleId + "'";
        System.out.println("SQL = " + sql);
        int row = executeUpdate(sql);

        closeAll();
        return row;
    }
}
