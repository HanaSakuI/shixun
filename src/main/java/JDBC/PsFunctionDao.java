package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yt
 * 实体类PsFunction的Dao
 */
public class PsFunctionDao extends BaseDao {
    /**
     * 根据functionCode获取checkboxId
     *
     * @param functionCode 功能编号
     * @return checkboxId
     */
    public String getCheckboxIdByFunctionCode(String functionCode) throws SQLException {
        String sql = "SELECT * FROM ps_function where functionCode = '" + functionCode + "'";
        String checkboxId = "";

        ResultSet rs = executeQuery(sql);

        while (rs.next()) {
            checkboxId = rs.getString("checkboxId");
        }

        closeAll();
        return checkboxId;
    }
}
