package JDBC;

import JavaBean.Tower;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TowerDao extends BaseDao{
    public List<Tower> getTowerAll(String pageNo) throws SQLException {
        int pageIndex=Integer.parseInt(pageNo);
        int beginStart=(pageIndex-1)*10;
        String sql="SELECT * FROM ps_pole_info limit "+beginStart+",10";
        ResultSet rs= executeQuery(sql);
        List<Tower>list=new ArrayList<Tower>();
        while(rs.next()){
            Tower tower=new Tower();
            tower.setPoleCode(rs.getString("poleCode"));
            //System.out.println(rs.getString("ploeCode"));
            tower.setId(rs.getInt("id"));
            tower.setLineName(rs.getString("lineName"));
            tower.setIsStart(rs.getInt("isStart"));
            list.add(tower);
        }
        closeAll();
        return list;
    }

    public List<Tower> getTowerBycondition(String lineName,String isStart) throws SQLException {
        String sql="SELECT * FROM ps_pole_info where 1=1";
        if (lineName != null) {
            sql += " and lineName like '%" + lineName + "%'";
        }
        if (!isStart.equals("-1")) {
            sql += " and isStart=" + isStart;
        }
        ResultSet rs= executeQuery(sql);
        List<Tower>list=new ArrayList<Tower>();
        while(rs.next()){
            Tower tower=new Tower();
            tower.setPoleCode(rs.getString("poleCode"));
            tower.setId(rs.getInt("id"));
            //System.out.println(rs.getString("ploeCode"));
            tower.setLineName(rs.getString("lineName"));
            tower.setIsStart(rs.getInt("isStart"));
            list.add(tower);
        }
        closeAll();
        return list;

    }

    public Tower getTowerBypoleCode(String id) throws SQLException{
        int ID = Integer.parseInt(id);
        //System.out.println(ID);
        String sql="SELECT * FROM ps_pole_info WHERE ps_pole_info.id = "+id;
        //System.out.println(sql);
        ResultSet rs=  executeQuery(sql);
        Tower tower = null;
        if(rs.next()){
           tower = new Tower();
           tower.setId(rs.getInt("id"));
           //System.out.println(rs.getInt("id"));
           tower.setPoleCode(rs.getString("poleCode"));
           tower.setIsStart(rs.getInt("isStart"));
        }
        closeAll();
        return tower;
    }

    public int updateTower(String poleCode, String isStart,String id) {
        String sql="update ps_pole_info set poleCode=?,isStart=?,lastUpdateTime=now() where id=?";
        Object[]param={

                poleCode,
                isStart,
                id

        };
        int row=    executeUpdate(sql,param);
        closeAll();
        return row;
    }


    public int delTower(String id) {
        int ID = Integer.parseInt(id);
        String sql="delete from ps_pole_info where id = " + id;
        int row=executeUpdate(sql);
        closeAll();
        return row;
    }

    public int addTower(String poleCode, String isStart,String lineName) {
        String sql="insert into ps_pole_info(poleCode,isStart,lineName)" +
                "values(?,?,?)";
        Object[]param={

                poleCode,
                isStart,
                lineName,

        };
        int row=    executeUpdate(sql,param);
        closeAll();
        return row;
    }
}