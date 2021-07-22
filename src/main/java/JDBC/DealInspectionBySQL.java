package JDBC;

import JavaBean.InspectionDetail;
import JavaBean.InspectionMain;
import JavaBean.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *  根据 SQL 获取 巡检表的 数据
 *
 */
public class DealInspectionBySQL {
        private List<InspectionMain> inspectionMain = new ArrayList<>();
        private JDBC jdbc = new JDBC();
        private Connection connection = null;
        private Statement statement = null;
        private ResultSet resultSet = null;
        private PreparedStatement ps = null;
        private String insert = "insert into ps_inspectiontask_main values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //获取所有任务信息
        private String selectAll = "select * from ps_inspectiontask_main";
        private String selectByCode = "select * from ps_inspectiontask_main where inspectionTaskCode=";
        private String cancelStatus = "update ps_inspectiontask_main set isCancel ='1' where inspectionTaskCode=";
        private String runInspet = "update ps_inspectiontask_main set taskStatus ='2',taskStatusName='执行中' where inspectionTaskCode=";
        private String insertInspector = "insert into ps_inspector_detail values(?,?,?,?,?,?,?)";
        //获取某个任务的所有巡检员和任务信息
        private String selectInspector = "select * from ps_inspectiontask_main,ps_inspector_detail where ps_inspector_detail.taskId = ps_inspectiontask_main.id";
        //获取所有巡检员
        private String allInspector = "select * from ps_user,ps_role where ps_user.roleId = ps_role.roleId and ps_user.roleId = 'ps_role03' and ps_user.userStatus ='1'";
        //添加回执
        private String addRunBack = "insert into ps_inspectiontask_detail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        private String loadRunBack="select * from ps_inspectiontask_detail where taskId=";
        private String editRunBack="update ps_inspectiontask_detail set ";
        public DealInspectionBySQL(){}

                public List<InspectionMain> getAllInspectionDetailByUser(String userName) throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select * from ps_inspectiontask_main,ps_inspector_detail where \n" +
                        "ps_inspectiontask_main.inspectionTaskCode = ps_inspector_detail.taskId and \n" +
                        "ps_inspector_detail.inspectorName = '"+userName+"'");
                while(resultSet.next()){
                        inspectionMain.add(new InspectionMain(resultSet.getString("inspectionTaskCode"),
                                resultSet.getString("inspectionTaskName"),resultSet.getInt("lineId"),
                                resultSet.getString("lineCode"), resultSet.getString("lineName"),
                                resultSet.getInt("startPole"),resultSet.getString("startPoleCode"),
                                resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                                resultSet.getString("issuedByCode"),resultSet.getString("issuedByName"),
                                resultSet.getDate("issuedTime"),resultSet.getInt("taskStatus"),
                                resultSet.getString("taskStatusName"),resultSet.getString("finishTime"),
                                resultSet.getInt("isCancel"),resultSet.getString("common"),
                                resultSet.getString("creationTime"),resultSet.getString("createdBy"),
                                resultSet.getString("lastUpdateTime")));
                }
                closeAll();
                return inspectionMain;
        }

        /**
         *      获取所有巡检信息
         * @return list
         */
        public List<InspectionMain> getAllInspectionMain() throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(selectAll);
                while(resultSet.next()){
                        inspectionMain.add(new InspectionMain(resultSet.getString("inspectionTaskCode"),
                                resultSet.getString("inspectionTaskName"),resultSet.getInt("lineId"),
                                resultSet.getString("lineCode"), resultSet.getString("lineName"),
                                resultSet.getInt("startPole"),resultSet.getString("startPoleCode"),
                                resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                                resultSet.getString("issuedByCode"),resultSet.getString("issuedByName"),
                                resultSet.getDate("issuedTime"),resultSet.getInt("taskStatus"),
                                resultSet.getString("taskStatusName"),resultSet.getString("finishTime"),
                                resultSet.getInt("isCancel"),resultSet.getString("common"),
                                resultSet.getString("creationTime"),resultSet.getString("createdBy"),
                                resultSet.getString("lastUpdateTime")));
                }
                closeAll();
                return inspectionMain;
        }

        public List<InspectionDetail> getAllInspectionDetail() throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select * from ps_inspectiontask_detail");
                List<InspectionDetail> list = new ArrayList<>();
                while (resultSet.next()){
                        list.add( new InspectionDetail(resultSet.getString("taskId"),resultSet.getString("lineCode"),
                                resultSet.getString("poleCode"), resultSet.getString("bugLevelName"),
                                resultSet.getString("bugTypeName"), resultSet.getString("discovererName"),
                                resultSet.getString("discoverTime"), resultSet.getString("createdBy"),
                                resultSet.getString("creationTime"), resultSet.getDouble("intactRate"),
                                resultSet.getString("bugDesc")));
                }
                return list;
        }

                /**
                 *              根据搜索内容获取所有结果
                 * @param taskCode
                 * @param lineCode
                 * @param searchRunStatus
                 * @param creatorCode
                 * @param createdTime1
                 * @param createdTime2
                 * @return inspection
                 * @throws ClassNotFoundException
                 * @throws SQLException
                 */
        public  List<InspectionMain> getSelectList( String taskCode, String lineCode,  String searchRunStatus,
                                                    String creatorCode, String createdTime1, String createdTime2) throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                selectAll +=" where 1=1 ";
                if (!taskCode.isEmpty()){
                        selectAll += "and inspectionTaskCode like '%"+taskCode+"%' ";
                }
                if (!lineCode.isEmpty()){
                        selectAll += "and lineCode like '%"+lineCode+"%'  ";
                }
                if (!creatorCode.isEmpty()){
                        selectAll += " and issuedByName like '%"+creatorCode+"%' ";
                }
                if (!createdTime1.isEmpty()){
                        selectAll+= "and issuedTime >= '"+createdTime1+"' ";
                }
                if (!createdTime2.isEmpty()){
                        selectAll+= "and issuedTime <= '"+createdTime2+"' ";
                }
                if (!searchRunStatus.isEmpty()){
                        selectAll += "and  taskStatus ='"+searchRunStatus+"'";
                }
                resultSet = statement.executeQuery(selectAll);
                while(resultSet.next()){
                        inspectionMain.add(new InspectionMain(resultSet.getString("inspectionTaskCode"),
                                resultSet.getString("inspectionTaskName"),resultSet.getInt("lineId"),
                                resultSet.getString("lineCode"), resultSet.getString("lineName"),
                                resultSet.getInt("startPole"),resultSet.getString("startPoleCode"),
                                resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                                resultSet.getString("issuedByCode"),resultSet.getString("issuedByName"),
                                resultSet.getDate("issuedTime"),resultSet.getInt("taskStatus"),
                                resultSet.getString("taskStatusName"),resultSet.getString("finishTime"),
                                resultSet.getInt("isCancel"),resultSet.getString("common"),
                                resultSet.getString("creationTime"),resultSet.getString("createdBy"),
                                resultSet.getString("lastUpdateTime")));
                }
                closeAll();
                return inspectionMain;
        }


        /**
         *       增加任务
         * @param InspectionTaskCode
         * @param InspectionTaskName
         * @param Select
         * @param StartPoleCode
         * @param EndPoleCode
         * @param Created
         * @param Common
         * @param CreatedDate
         * @return  row
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int addInspection(String InspectionTaskCode,String InspectionTaskName,String Select,String StartPoleCode,
                                 String EndPoleCode,String Created,String Common,String CreatedDate) throws ClassNotFoundException, SQLException {
                connection = new JDBC().getConnection();
                ps = connection.prepareStatement(insert);
                ps.setInt(1,0);
                ps.setString(2,InspectionTaskCode);
                ps.setString(3,InspectionTaskName);
                ps.setInt(4,Integer.parseInt(Select));
                ps.setString(5, ("XW000"+Select));
                ps.setString(6, getLineName(Select));
                ps.setInt(7, Integer.parseInt(StartPoleCode.replace("XW","")));
                ps.setString(8,StartPoleCode);
                ps.setInt(9,Integer.parseInt(EndPoleCode.replace("XW","")));
                ps.setString(10,EndPoleCode);
                ps.setString(11,Created);
                ps.setString(12,Created);
                ps.setString(13,CreatedDate);
                ps.setInt(14,0);
                ps.setString(15,"待分配");
                ps.setString(16,null);
                ps.setInt(17,0);
                ps.setString(18,Common);
                ps.setString(19,getLastUpdateTime());
                ps.setString(20,Created);
                ps.setString(21,getLastUpdateTime());
                return ps.executeUpdate();
        }

        /**
         *
         *  编辑 修改 任务信息
         * @param editInspectionTaskCode
         * @param editInspectionTaskName
         * @param editStartPoleCode
         * @param editEndPoleCode
         * @param editCommon
         * @param editSelect
         * @return row
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int editInspection(String editInspectionTaskCode,String editInspectionTaskName,String editStartPoleCode, String editEndPoleCode,String editCommon,String editSelect) throws ClassNotFoundException, SQLException {
                String sql = "update ps_inspectiontask_main set inspectionTaskName='"+editInspectionTaskName+"',startPoleCode ='"+editStartPoleCode+"',endPoleCode='"+editEndPoleCode+"',common = '"+editCommon+"',lineId='"+editSelect+"',lastUpdateTime='"+getLastUpdateTime()+"' where inspectionTaskCode ='"+editInspectionTaskCode+"'";
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                return statement.executeUpdate(sql);
        }


        /**
         *
         *   通过任务编码 获取任务信息
         * @param code
         * @return inspection
         * @throws SQLException
         * @throws ClassNotFoundException
         */

        public InspectionMain getInspectionByCode(String code) throws SQLException, ClassNotFoundException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                selectByCode += "'"+code+"'";
                resultSet = statement.executeQuery(selectByCode);
                InspectionMain inspection = null;
                while(resultSet.next()){
                         inspection = new InspectionMain(resultSet.getString("inspectionTaskCode"),
                                 resultSet.getString("inspectionTaskName"),resultSet.getInt("lineId"),
                                 resultSet.getString("lineCode"), resultSet.getString("lineName"),
                                 resultSet.getInt("startPole"),resultSet.getString("startPoleCode"),
                                 resultSet.getInt("endPole"),resultSet.getString("endPoleCode"),
                                 resultSet.getString("issuedByCode"),resultSet.getString("issuedByName"),
                                 resultSet.getDate("issuedTime"),resultSet.getInt("taskStatus"),
                                 resultSet.getString("taskStatusName"),resultSet.getString("finishTime"),
                                 resultSet.getInt("isCancel"),resultSet.getString("common"),
                                 resultSet.getString("creationTime"),resultSet.getString("createdBy"),
                                 resultSet.getString("lastUpdateTime"));
                }
                closeAll();
                        return  inspection;
        }


        /**
         * 分配 巡检人员
         *
         * @param InspectCode
         * @param inspectors
         * @return int
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        public int addInspector(String InspectCode,String[] inspectors,User user) throws SQLException, ClassNotFoundException {

                for (int i = 0; i < inspectors.length; i++) {
                        String userCode = "";
                        String userName= "";
                        String allInspector = "select * from ps_user,ps_role where ps_user.roleId = ps_role.roleId and ps_user.roleId = 'ps_role03' and ps_user.userStatus ='1'";
                        allInspector += " and userCode='"+inspectors[i]+"'";
                         connection = jdbc.getConnection();
                         statement = connection.createStatement();
                         resultSet = statement.executeQuery(allInspector);
                        while(resultSet.next()){
                                userCode = resultSet.getString("userCode");
                                userName = resultSet.getString("userName");
                        }
                        PreparedStatement ps = connection.prepareStatement(insertInspector);
                        ps.setInt(1,0);
                        ps.setString(2,InspectCode);
                        ps.setString(3,userCode);
                        ps.setString(4,userName);
                        ps.setString(5,getLastUpdateTime());
                        ps.setString(6,user.getUserName());
                        ps.setString(7,getLastUpdateTime());
                       if (ps.executeUpdate() < 1){return 0;}
                }
                 String sql = "update ps_inspectiontask_main set taskStatus = '1',taskStatusName ='已分配' where inspectionTaskCode='"+InspectCode+"'";
                 statement.executeUpdate(sql);
                 closeAll();
                return 1;
        }

        /**
         *  添加回执信息
         *
         * @param addRunBackTaskCode
         * @param addRunBackStatus
         * @param addRunBackTowerCode
         * @param addRunBackLineCode
         * @param addRunBackIntactRate
         * @param addRunBackBugLevel
         * @param addRunBackBugType
         * @param common
         * @param userCode
         * @param userName
         * @return
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int addRunBackMessage(String addRunBackTaskCode,String addRunBackStatus,String addRunBackTowerCode,
                                     String addRunBackLineCode,String addRunBackIntactRate,String addRunBackBugLevel,
                                     String addRunBackBugType,String common,String userCode,String userName) throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                 ps = connection.prepareStatement(addRunBack);
                ps.setInt(1,0);
                ps.setString(2,addRunBackTaskCode);
                ps.setInt(3,Integer.parseInt(addRunBackStatus));
                ps.setInt(4,Integer.parseInt(addRunBackTowerCode.replace("XW","")));
                ps.setString(5,addRunBackTowerCode);
                ps.setString(6, addRunBackLineCode);
                ps.setString(7, getLineName(addRunBackLineCode.replace("XW","")));
                ps.setInt(8, 0);
                ps.setDouble(9,Double.parseDouble(addRunBackIntactRate));
                ps.setInt(10, Integer.parseInt(addRunBackBugLevel));
                ps.setString(11,getBugLevelName(addRunBackBugLevel));
                ps.setInt(12,Integer.parseInt(addRunBackBugType));
                ps.setString(13,getBugTypeName(addRunBackBugType));
                ps.setString(14,common);
                ps.setString(15,userCode);
                ps.setString(16,userName);
                ps.setString(17,getLastUpdateTime());
                ps.setInt(18,0);
                ps.setInt(19,0);
                ps.setString(20,getLastUpdateTime());
                ps.setString(21,getLastUpdateTime());
                ps.setString(22,userName);
                ps.setString(23,getLastUpdateTime());
                int row = ps.executeUpdate();
                if (row < 1 ){return 0;}
                String sql = "update ps_inspectiontask_main set taskStatus ='3',taskStatusName='已完成',finishTime='"+getLastUpdateTime()+"' where inspectionTaskCode='"+addRunBackTaskCode+"'";
                return  statement.executeUpdate(sql);
        }


        /**
         *      取消任务
         * @param code
         * @return row
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        public int cancelStatus(String code) throws SQLException, ClassNotFoundException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                int row = 0;
                cancelStatus += "'"+code+"'";
                row = statement.executeUpdate(cancelStatus);
                return row;
        }

        /**
         *      执行任务
         * @param code
         * @return  int
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int runInspection(String code) throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                runInspet += "'"+code+"'";
                return statement.executeUpdate(runInspet);
        }

        /**
         *      获取参与任务的人员信息
         *
         * @return      user
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public List<User> getInspectorList() throws ClassNotFoundException, SQLException {
                List<User> list = new ArrayList<>();
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(allInspector);
                while(resultSet.next()){
                        list.add(new User(resultSet.getString("userCode"),resultSet.getString("userName")));
                }
                return list;
        }

        /**
         *  根据任务ID加载任务信息
         *
         * @param code
         * @return inspectionDetail
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        public InspectionDetail LoadEditRunBack(String code) throws SQLException, ClassNotFoundException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                loadRunBack += "'"+code+"'";
                resultSet = statement.executeQuery(loadRunBack);
                InspectionDetail inspectionDetail=null;
                while (resultSet.next()){
                         inspectionDetail = new InspectionDetail(resultSet.getString("taskId"),
                                resultSet.getString("lineCode"), resultSet.getString("poleCode"),
                                resultSet.getInt("bugType"),resultSet.getInt("bugLevel"),
                                resultSet.getString("discoverTime"),resultSet.getString("discovererName"),
                                resultSet.getDouble("intactRate"),resultSet.getString("bugDesc"));
                }
                return inspectionDetail;
        }

        /** 根据编码获取细节信息
         *
         * @param code
         * @return
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        public InspectionDetail loadDetailByCode(String code) throws SQLException, ClassNotFoundException{
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                String inspector="";
                String inspect = "select * from ps_inspector_detail where taskId ='"+Integer.parseInt(code.replace("RW",""))+"'";
                resultSet = statement.executeQuery(inspect);
                while (resultSet.next()){
                        inspector += resultSet.getString("inspectorName")+" ";
                }
                loadRunBack += "'"+code+"'";
                resultSet = statement.executeQuery(loadRunBack);
                InspectionDetail inspectionDetail=null;
                while (resultSet.next()){
                        inspectionDetail = new InspectionDetail(resultSet.getString("lineCode"),
                                resultSet.getString("poleCode"), getBugName(resultSet.getString("isBug")),
                                resultSet.getString("bugTypeName"),resultSet.getString("bugLevelName"),
                               resultSet.getString("bugDesc"),resultSet.getString("inspectTime"),inspector,
                                resultSet.getString("discovererName"),resultSet.getString("discoverTime"),resultSet.getString("createdBy"),
                                resultSet.getString("creationTime"),resultSet.getDouble("intactRate"));
                }
                return inspectionDetail;
        }
        /**
         * 修改任务信息 回执
         *
         * @param editRunBackTaskCode
         * @param editRunBackIntactRate
         * @param editRunBackBugLevel
         * @param editRunBackBugType
         * @param common
         * @return row
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public int editRunBack(String editRunBackTaskCode,String editRunBackIntactRate,String editRunBackBugLevel,
                               String editRunBackBugType,String common) throws ClassNotFoundException, SQLException {
                connection = jdbc.getConnection();
                statement = connection.createStatement();
                editRunBack += "intactRate ='"+editRunBackIntactRate+"',bugLevel ='"+editRunBackBugLevel+"',bugType='"+editRunBackBugType+"',bugDesc='"+common+"' where taskId='"+editRunBackTaskCode+"'";
                return statement.executeUpdate(editRunBack);
        }
        /**
         *    获取当前时间
         * @return date
         */
        public String getLastUpdateTime(){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                return df.format(date);
        }

        /**
         *  获取当前日期
         * @return Date
         */
        public String getDate(){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = new java.util.Date();
                return df.format(date);
        }


        public String getBugName(String bugCode){
                String s = "";
                switch (bugCode){
                        case "1": s= "有"; break;
                        case "0": s= "无"; break;
                        default:break;
                }
                return s;
        }
        /**
         *      获取线路名
         * @param lineCode
         * @return lineName
         */
        public String getLineName(String lineCode){
                String s = "";
                switch (lineCode){
                        case "1": s= "一"; break;
                        case "2": s= "二"; break;
                        case "3": s= "三"; break;
                        case "4": s= "四"; break;
                        case "5": s= "五"; break;
                        case "6": s= "六"; break;
                        default:break;
                }
                return "西渭"+s+"线";
        }

        /** 获取bug等级
         *
         * @param bugLevel
         * @return String
         */
        public String getBugLevelName(String bugLevel){
                String s = "";
                switch (bugLevel){
                        case "1": s= "一般"; break;
                        case "2": s= "紧急"; break;
                        case "3": s= "严重"; break;
                        default:break;
                }
                return s;
        }

        /**
         *  获取bug类型名称
         * @param bugType
         * @return str
         */
        public String getBugTypeName(String bugType){
                String s = "";
                switch (bugType){
                        case "1": s= "叉梁断裂"; break;
                        case "2": s= "拦河线断裂"; break;
                        case "3": s= "绝缘子爆破"; break;
                        case "4": s= "杆塔倾斜"; break;
                        case "5": s= "横杠弯曲下垂"; break;
                        case "6": s= "瓷瓶破裂"; break;
                        case "7": s= "吊杆变形"; break;
                        case "8": s= "其他"; break;
                        default:break;
                }
                return s;
        }

        /**
         *  关闭所有连接类
         * @throws SQLException
         */
        public void closeAll() throws SQLException {
                if (connection!=null){
                        if (!connection.isClosed()){connection.close(); }
                }
                if (statement!=null){
                        if (!statement.isClosed()){ statement.close();}
                }
                if (resultSet!=null) {
                        if (!resultSet.isClosed()) { resultSet.close(); }
                }
                if (ps!=null) {
                        if (!ps.isClosed()) { ps.close(); }
                }
        }

}
