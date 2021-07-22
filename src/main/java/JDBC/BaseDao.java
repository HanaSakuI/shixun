package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://116.62.36.247:3306/shixunweb";
	private static final String USER = "root";
	private static final String PWD = "root";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 获得oracle数据库连接的方法
	 *
	 * @return Oracle连接对象
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 查询方法 select
	 *
	 * @param sql
	 *            select语句
	 * @param param
	 *            参数列表 0~n 个
	 * @return 查询的结果 结果集
	 */
	public ResultSet executeQuery(String sql, Object... param) {

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * 受影响的行数
	 *
	 * @param sql
	 *            insert update delete语句
	 * @param param
	 *            参数列表 0~n
	 * @return 返回受影响的行数
	 */
	public int executeUpdate(String sql, Object... param) {

		int row = 0;
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return row;
	}

	/**
	 * 释放资源
	 */
	public void closeAll() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}