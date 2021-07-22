package Servlet;

import JDBC.DealLineBySQL;
import JavaBean.Line;
import JavaBean.ProjectLogger;
import JavaBean.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Homework/HTML/LineConfig/LineServlet")
public class LineServlet extends HttpServlet {
    private List<Line> lineList;
    private ProjectLogger logger = new ProjectLogger();
    private String className = LineServlet.class.getName();
    private String poleCode = "XW";
    private Line line = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setContentType("applicaton/json;charset=utf-8");
        //获取执行类型
        String productId = request.getParameter("productId");
        String flag = "";
             switch (productId){
                 //获取全部
                 case "selectAll":
                     try {
                         lineList = new DealLineBySQL().getLineList();
                         logger.getLoggerMessage(className,"Get LineList Success!");
                         response.getWriter().write(JSON.toJSONString(lineList));
                     } catch (ClassNotFoundException|SQLException e) {
                         logger.getLoggerMessage(className,"Get LineList Error!");
                         e.printStackTrace();
                     }
                     break;
                  //添加
                 case "add":
                            User user = (User)request.getSession().getAttribute("user");
                            if (user ==null){
                                flag = "{\"flag\":\"error\"}";
                                response.getWriter().write(flag);
                            }
                            String lineCode = request.getParameter("lineCode");
                            String lineName = request.getParameter("lineName");
                            String lineLength = request.getParameter("lineLength");
                            String backLength = request.getParameter("backLength");
                            String productDate = request.getParameter("productDate");
                            String voltageLevel = request.getParameter("voltageLevel");
                            String startPoleCode = request.getParameter("startPoleCode");
                            int startPole = Integer.parseInt(startPoleCode.split(poleCode)[1]);
                            String endPoleCode = request.getParameter("endPoleCode");
                            int endPole = Integer.parseInt(endPoleCode.split(poleCode)[1]);
                            String towerBaseNum = request.getParameter("towerBaseNum");
                            String maintenanceCompany = "维修公司";
                            int runningStatus = 1;
                            String runningStatusName = "正常";
                            String common = request.getParameter("common");
                            String isStart = request.getParameter("isStart");
                            String creationTime = new DealLineBySQL().getLastUpdateTime();
                            String createdBy = user.getUserName();
                            String lastUpdateTime = new DealLineBySQL().getLastUpdateTime();
                             line = new Line(lineCode,lineName,Double.parseDouble(lineLength),Double.parseDouble(backLength)
                                    ,Date.valueOf(productDate),voltageLevel,startPole,startPoleCode,endPole,endPoleCode,
                                    Integer.parseInt(towerBaseNum), maintenanceCompany,runningStatus,runningStatusName,common,
                                    Integer.parseInt(isStart), creationTime,createdBy,lastUpdateTime);
                             try {
                                 int row  =  new DealLineBySQL().addLine(line);
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
                  //获取细节查询
                 case "select":
                     String sele = request.getParameter("sele");
                     String searchStartStatus = request.getParameter("searchStartStatus");
                     String searchRunStatus = request.getParameter("searchRunStatus");
                     try {
                         lineList = new DealLineBySQL().getSelectLineList(sele,searchRunStatus,searchStartStatus);
                         response.getWriter().write(JSON.toJSONString(lineList));
                     } catch (ClassNotFoundException|SQLException e) {
                         e.printStackTrace();
                     }
                     break;
               //删除
             case "delete":
                       String deleteLine = request.getParameter("lineCode");
                 try {
                     int row = new DealLineBySQL().deleteByLineCode(deleteLine);

                     if (row > 0){
                          flag = "{\"flag\":\"success\"}";
                     }else{
                         flag = "{\"flag\":\"error\"}";
                     }
                     response.getWriter().write(flag);
                 } catch (ClassNotFoundException|SQLException e) {
                     e.printStackTrace();
                 }
                 break;

                 //修改运行状态
                 case "status":
                     String changeLine = request.getParameter("lineCode");
                     try {
                         int row = new DealLineBySQL().changeStatus(changeLine);
                         if (row > 0){
                             flag = "{\"flag\":\"success\"}";
                         }else{
                             flag = "{\"flag\":\"error\"}";
                         }
                         response.getWriter().write(flag);
                     } catch (ClassNotFoundException|SQLException e) {
                         e.printStackTrace();
                     }
                     break;

                 //获取修改信息
                 case "edit":
                     String editLineCode = request.getParameter("lineCode");
                     try {
                          line = new DealLineBySQL().getLineMassage(editLineCode);
                         response.getWriter().write(JSON.toJSONString(line));
                     } catch (ClassNotFoundException|SQLException e) {
                         e.printStackTrace();
                     }
                     break;

                 //修改内容
                 case "changeMessage":
                     String changeLineCode = request.getParameter("lineCode");
                     String changeLineName = request.getParameter("lineName");
                     String changeLineLength = request.getParameter("lineLength");
                     String changeBackLength = request.getParameter("backLength");
                     String changeProductDate = request.getParameter("productDate");
                     String changeVoltageLevel = request.getParameter("voltageLevel");
                     String changeStartPoleCode = request.getParameter("startPoleCode");
                     String changeEndPoleCode = request.getParameter("endPoleCode");
                     String changeTowerBaseNum = request.getParameter("towerBaseNum");
                     String changeCommon = request.getParameter("common");
                     String changeIsStart = request.getParameter("isStart");

                         line = new Line(changeLineCode,changeLineName,Double.valueOf(changeLineLength),
                                Double.valueOf(changeBackLength), Date.valueOf(changeProductDate),changeVoltageLevel,
                                changeStartPoleCode,changeEndPoleCode,Integer.valueOf(changeTowerBaseNum),changeCommon,Integer.valueOf(changeIsStart));
                     try {
                         int row = new DealLineBySQL().changeLineMessage(line);
                         if (row > 0){
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