package JDBC;

import JavaBean.PsSystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yt
 * PsSystemConfig的Dao
 */
public class PsSystemConfigDao extends BaseDao {

    /**
     *  根据 Bug Type 的 id 获取 缺陷类型的信息
     *
     * @param valueId
     * @return psSystemConfig
     * @throws SQLException
     */
    public PsSystemConfig getPsSystemConfigByBugTypeValueId(String valueId) throws SQLException {
        String sql = "select * from ps_systemconfig where configCode='BUG_TYPE' and configValueId = '"+valueId+"'";
        ResultSet rs = executeQuery(sql);
        PsSystemConfig psSystemConfig = null;
        while(rs.next()){
            psSystemConfig = new PsSystemConfig(rs.getInt("configValueId"),rs.getString("configValueName"),
                    rs.getInt("isStart"));
        }
        closeAll();
        return psSystemConfig;
    }

    /**
     *
     *  修改 bugType的名称及状态
     * @param configValueId
     * @param configValueName
     * @param isStart
     * @return
     */
    public int editBugTypeByValueId(String configValueId,String configValueName, int isStart){
        String sql = "update ps_systemconfig set configValueName= '"+configValueName+"',isStart = '"+isStart+"' where configCode='BUG_TYPE' and configValueId='"+configValueId+"'";
        int row = executeUpdate(sql);
        closeAll();
        return row;
    }

    /**
     * 删除一条 bugtype
     * @param valueId
     * @return
     */
    public int deleteBugTypeByValueId(String valueId){
        String sql = "delete FROM ps_systemconfig where configValueId='"+valueId+"' and configCode='BUG_TYPE'";
        int row = executeUpdate(sql);
        closeAll();
        return row;
    }


    /**
     * 展示所有种类的SystemConfig，并实现分页
     *
     * @param pageNo 页码
     * @return psSystemConfigList
     */
    public List<PsSystemConfig> getAllSystemConfigByKind(String pageNo) throws SQLException {
        int pageIndex = Integer.parseInt(pageNo);
        int beginStart = (pageIndex - 1) * 10;

        String sql = "SELECT * FROM ps_systemconfig group by configCode limit " + beginStart + ",10";
        ResultSet rs = executeQuery(sql);

        List<PsSystemConfig> psSystemConfigList = new ArrayList<>();

        while (rs.next()) {
            String configCode = rs.getString("configCode");
            String configName = rs.getString("configName");
            PsSystemConfig psSystemConfig = new PsSystemConfig(configCode, configName);
            psSystemConfigList.add(psSystemConfig);
        }

        closeAll();
        return psSystemConfigList;
    }

    /**
     * 根据configCode查询SystemConfig
     *
     * @param configCode 配置编号
     * @return psSystemConfigList
     */
    public List<PsSystemConfig> getAllSystemConfigByConfigCode(String configCode) throws SQLException {
        String sql = "SELECT * FROM ps_systemconfig where configCode = '" + configCode + "' and configValueId > 0";

        ResultSet rs = executeQuery(sql);
        List<PsSystemConfig> psSystemConfigList = new ArrayList<>();

        while (rs.next()) {
            int configValueId = rs.getInt("configValueId");
            String configValueName = rs.getString("configValueName");
            int isStart = rs.getInt("isStart");

            PsSystemConfig psSystemConfig = new PsSystemConfig(configValueId, configValueName, isStart);
            psSystemConfigList.add(psSystemConfig);
        }

        closeAll();
        return psSystemConfigList;
    }

    /**
     * 根据configCode获得configName
     *
     * @param configCode 配置编号
     * @return configName 配置名称*/
    public String getConfigNameByConfigCode(String configCode) throws SQLException {
        String sql = "SELECT * FROM ps_systemconfig where configCode = '" + configCode + "'";
        String configName = "";

        ResultSet rs = executeQuery(sql);

        while (rs.next()) {
            configName = rs.getString("configName");
        }

        closeAll();
        return configName;
    }

    /**
     * 判断configCode是否存在
     *
     * @param configCode 配置编号
     * @return flag flag=0：两者均不存在，flag=1：configCode存在，flag=2：configName存在，flag=3：两者均存在
     */
    public int judgeSystemConfigByConfigCode(String configCode, String configName) throws SQLException {
        String sql1 = "SELECT COUNT(*) FROM ps_systemconfig WHERE configCode = '" + configCode + "'";
        String sql2 = "SELECT COUNT(*) FROM ps_systemconfig WHERE configName = '" + configName + "'";

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
     * 实现config的添加
     *
     * @param configCode 配置类型编号
     * @param configName 配置类型名称
     * @return row
     */
    public int addSystemConfig(String configCode, String configName) {
        String sql = "INSERT INTO ps_systemconfig(`configCode`, `configName`) VALUES (?, ?);";

        Object[] param = {
                configCode,
                configName
        };

        int row = executeUpdate(sql, param);
        closeAll();

        return row;
    }

    /**
     * 判断configValueId和configValueName是否存在
     *
     * @param configCode      配置编号
     * @param configValueId   配置参数ID
     * @param configValueName 配置参数名称
     * @return flag flag=0：两者均不存在，flag=1：configValueId存在，flag=2：configValueName存在，flag=3：两者均存在
     */
    public int judgeSystemConfigByConfigCodeAndConfigValueIdAndConfigValueName(String configCode, int configValueId, String configValueName) throws SQLException {
        String sql1 = "SELECT COUNT(*) FROM ps_systemconfig WHERE configCode = '" + configCode + "' and configValueId = '" + configValueId + "'";
        String sql2 = "SELECT COUNT(*) FROM ps_systemconfig WHERE configCode = '" + configCode + "' and configValueName = '" + configValueName + "'";

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
     * 实现config的添加
     *
     * @param configCode 配置类型编号
     * @param configName 配置类型名称
     * @param configValueId 配置参数ID
     * @param configValueName 配置参数名称
     * @return row
     */
    public int addSystemConfig(String configCode, String configName, int configValueId, String configValueName, int isStart) {
        String sql = "INSERT INTO ps_systemconfig(`configCode`, `configName`, `configValueId`, `configValueName`, `isStart`) VALUES (?, ?, ?, ?, ?);";

        Object[] param = {
                configCode,
                configName,
                configValueId,
                configValueName,
                isStart
        };

        int row = executeUpdate(sql, param);
        closeAll();

        return row;
    }

    /**
     * 更新表getConfigCode中的标记
     *
     * @param configCode 配置编号
     * @param configName 配置名称 */
    public void updateGetConfigCode(String configCode, String configName) {
        String sql = "update getConfigCode set configCode = ?, configName = ? where id = 1";

        Object[] param = {
                configCode,
                configName
        };

        int row = executeUpdate(sql, param);
        closeAll();
    }

    /**
     * 获取getConfigCode表中的数据
     *
     * @return PSSystemConfig*/
    public PsSystemConfig getConfig() throws SQLException {
        PsSystemConfig psSystemConfig = null;
        String sql = "SELECT * FROM getConfigCode where id = 1";
        ResultSet rs = executeQuery(sql);

        while (rs.next()) {
            String configCode = rs.getString("configCode");
            String configName = rs.getString("configName");

            psSystemConfig = new PsSystemConfig(configCode, configName);
        }

        closeAll();
        return psSystemConfig;
    }

    /**
     * 根据configCode和configValueId删除config
     *
     * @param configCode  配置编号
     * @param configValueId 配置参数ID
     * @return row*/
    public int deleteConfigByConfigCodeAndConfigValueId(String configCode, int configValueId) {
        String sql = "";
        int row;
        if (configValueId == 0) {
            sql = "delete from ps_systemconfig where configCode = '" + configCode + "'";
        } else {
            sql = "delete from ps_systemconfig where configCode = '" + configCode + "' and configValueId = '" + configValueId + "'";
        }

        System.out.println("sql = " + sql);

        row = executeUpdate(sql);
        System.out.println("Before row = " + row);
        closeAll();
        System.out.println("After row = " + row);
        return row;
    }
}
