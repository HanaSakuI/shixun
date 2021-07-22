package Servlet;

import JDBC.DealInspectionBySQL;
import JDBC.DealLineBySQL;
import JavaBean.InspectionDetail;
import JavaBean.InspectionMain;
import JavaBean.Line;
import JavaBean.User;
import com.alibaba.fastjson.JSON;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Homework/HTML/TaskConfig/InspectionMainServlet")
public class InspectionMainServlet extends HttpServlet {
    private List<InspectionMain> list=null;
    private InspectionMain inspection=null;
    private User user = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setContentType("applicaton/json;charset=utf-8");
        String productId = request.getParameter("productId");
        String flag = "";
        int row = 0;
        user = (User) request.getSession().getAttribute("user");
        switch (productId){
            //添加
            case "add":
                String addInspectionTaskCode = request.getParameter("lineCode");
                String addInspectionTaskName = request.getParameter("lineName");
                String addStartPoleCode = request.getParameter("startPoleCode");
                String addEndPoleCode = request.getParameter("endPoleCode");
                String addCreated = request.getParameter("created");
                String addCommon = request.getParameter("common");
                String addSelect = request.getParameter("select");
                String addCreatedDate = request.getParameter("createdDate");
                try {
                    row = new DealInspectionBySQL().addInspection(addInspectionTaskCode,addInspectionTaskName,addSelect,
                            addStartPoleCode,addEndPoleCode,addCreated,addCommon,addCreatedDate);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

            //修改
            case "edit":
                String editInspectionTaskCode = request.getParameter("lineCode");
                String editInspectionTaskName = request.getParameter("lineName");
                String editStartPoleCode = request.getParameter("startPoleCode");
                String editEndPoleCode = request.getParameter("endPoleCode");
                String editCreated = request.getParameter("created");
                String editCommon = request.getParameter("common");
                String editSelect = request.getParameter("select");
                String editCreatedDate = request.getParameter("createdDate");
                try {
                    row = new DealInspectionBySQL().editInspection( editInspectionTaskCode,editInspectionTaskName, editStartPoleCode, editEndPoleCode, editCommon, editSelect);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "runSelect":
                try {
                    list = new DealInspectionBySQL().getAllInspectionDetailByUser(user.getUserName());
                    response.getWriter().write(JSON.toJSONString(list));
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

            //获取全部任务
            case "selectAll":
                try {
                    list = new DealInspectionBySQL().getAllInspectionMain();
                    response.getWriter().write(JSON.toJSONString(list));
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

                //获取创建人和时间和线路名称
            case "getCreated":
                try {
                    List<Line> lineList = new DealLineBySQL().getLineList();
                    flag = "{\"userName\":\""+user.getUserName()+"\",\"proDate\":\""+new DealInspectionBySQL().getDate()+"\"}";
                    response.getWriter().write(flag);
                } catch (ClassNotFoundException |SQLException e) {
                    e.printStackTrace();
                }
                break;

                //进行人员分配
            case "runDistribute":
                try {
                    //任务编码
                    String InspectCode = request.getParameter("InspectCode");
                    //人员数
                    String inspetor = request.getParameter("inspetor");
                    //巡检人员数组
                    String[] inspectors = inspetor.split(" ");
                    row = new DealInspectionBySQL().addInspector(InspectCode,inspectors,user);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (SQLException|ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;

                //获取分配人员
            case "distribution":
                List<User> userList = null;
                try {
                    userList = new DealInspectionBySQL().getInspectorList();
                    response.getWriter().write(JSON.toJSONString(userList));
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

                //执行任务
            case "runInspection":
                     String InspectCode = request.getParameter("InspectionTaskCode");
                try {
                    row = new DealInspectionBySQL().runInspection(InspectCode);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (ClassNotFoundException|SQLException e) {
                    e.printStackTrace();
                }
                break;

            //加载回执信息
                case "loadRunBack":
                    try {
                        //任务编码
                        String loadTaskCode = request.getParameter("InspectionTaskCode");
                        inspection = new DealInspectionBySQL().getInspectionByCode(loadTaskCode);
                        flag = "{\"lineCode\":\""+("XW00")+inspection.getLineId()+"\",\"towerCode\":\""+inspection.getStartPoleCode()+"\",\"time\":\""+new DealInspectionBySQL().getDate()+"\",\"name\":\""+user.getUserName()+"\"}";
                        response.getWriter().write(flag);
                    } catch (SQLException|ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    break;

                    //添加回执信息
                case "addRunBack":
                         //任务编码
                        String addRunBackTaskCode = request.getParameter("taskCode");
                         //任务状态
                        String addRunBackStatus = request.getParameter("status");
                        //杆塔编码
                        String addRunBackTowerCode = request.getParameter("towerCode");
                        //线路编码
                        String addRunBackLineCode = request.getParameter("lineCode");
                        //缺陷类型
                        String addRunBackBugType = request.getParameter("bugType");
                        //缺陷等级
                        String addRunBackBugLevel = request.getParameter("bugLevel");
                        //缺陷备注
                        String addRunBackCommon = request.getParameter("common");
                        //发现时间
                        String addRunBackTime = request.getParameter("time");
                        //发现人
                        String addRunBackName = request.getParameter("name");
                        //完好率
                        String addRunBackIntactRate = request.getParameter("intactRate");
                        try {
                            row = new DealInspectionBySQL().addRunBackMessage(addRunBackTaskCode,addRunBackStatus,
                                    addRunBackTowerCode,addRunBackLineCode,addRunBackIntactRate,addRunBackBugLevel,
                                    addRunBackBugType,addRunBackCommon,user.getUserCode(),addRunBackName);
                            if (row >= 1){
                                flag = "{\"flag\":\"success\"}";
                            }else{
                                flag = "{\"flag\":\"error\"}";
                            }
                            response.getWriter().write(flag);
                        } catch (ClassNotFoundException|SQLException e) {
                            e.printStackTrace();
                        }
                        break;

              // 加载回执修改的信息
            case "loadEditRunBack":
                try {
                    String loadRunBackTaskCode = request.getParameter("InspectionTaskCode");
                    InspectionDetail inspectionDetail = new DealInspectionBySQL().LoadEditRunBack(loadRunBackTaskCode);
                    if (inspectionDetail != null){
                            response.getWriter().write(JSON.toJSONString(inspectionDetail));
                        }else{
                        response.getWriter().write("{\"flag\":\"error\"}");
                    }
                } catch (SQLException|ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;

                //修改回执信息
            case "editRunBack":
                try {
                //任务编码
                String editRunBackTaskCode = request.getParameter("taskCode");
                //缺陷类型
                String editRunBackBugType = request.getParameter("bugType");
                //缺陷等级
                String editRunBackBugLevel = request.getParameter("bugLevel");
                //缺陷备注
                String editRunBackCommon = request.getParameter("common");
                //完好率
                String editRunBackIntactRate = request.getParameter("intactRate");
                    row = new DealInspectionBySQL().editRunBack(editRunBackTaskCode,editRunBackIntactRate,editRunBackBugLevel,editRunBackBugType,editRunBackCommon);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            //根据任务编码获取任务信息
            case "selectByCode":
                //任务编码
                try {
                    String InspectionTaskCode = request.getParameter("InspectionTaskCode");
                     inspection = new DealInspectionBySQL().getInspectionByCode(InspectionTaskCode);
                    response.getWriter().write(JSON.toJSONString(inspection));
                } catch (SQLException|ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case "selectDetail":
                try {
                    List<InspectionDetail> inspectionDetails = new DealInspectionBySQL().getAllInspectionDetail();
                    response.getWriter().write(JSON.toJSONString(inspectionDetails));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case "loadDetail":
                try {
                    String loadTaskCode = request.getParameter("InspectionTaskCode");
                    InspectionDetail inspectionDetail = new DealInspectionBySQL().loadDetailByCode(loadTaskCode);
                    response.getWriter().write(JSON.toJSONString(inspectionDetail));
                } catch (SQLException|ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;

                //搜索
                case "select":
                    try {
                        //任务编码
                        String taskCode = request.getParameter("taskCode");
                        //线路编码
                        String lineCode = request.getParameter("lineCode");
                        //状态
                        String searchRunStatus = request.getParameter("searchRunStatus");
                        //创建人
                        String creatorCode = request.getParameter("creatorCode");
                        //下发时间1
                        String createdTime1 = request.getParameter("createdTime1");
                        //下发时间2
                        String createdTime2 = request.getParameter("createdTime2");
                        if (Integer.parseInt(searchRunStatus)==4){searchRunStatus = "";}
                        list = new DealInspectionBySQL().getSelectList(taskCode,lineCode,searchRunStatus,creatorCode,createdTime1,createdTime2);
                        response.getWriter().write(JSON.toJSONString(list));
                    } catch (ClassNotFoundException|SQLException e) {
                        e.printStackTrace();
                    }
                    break;

            //取消任务
            case "cancel":
                try {
                    String cancelTaskCode = request.getParameter("InspectionTaskCode");
                    row = new DealInspectionBySQL().cancelStatus(cancelTaskCode);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                } catch (SQLException|ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:break;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }
}
