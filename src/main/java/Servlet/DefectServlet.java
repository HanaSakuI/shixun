package Servlet;

import com.alibaba.fastjson.JSON;
import JDBC.DefectDao;
import JDBC.SolveDao;
import JavaBean.Defect;
import JavaBean.Tower;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/Homework/HTML/TaskConfig/defect")
public class DefectServlet extends HttpServlet {
    DefectDao dao = new DefectDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String pageNo = req.getParameter("pageNo");
//        List<Defect> list= null;
//        try {
//            list = dao.getDefectAll(pageNo);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.setContentType("application/json;charset=utf-8");
//        resp.getWriter().write(JSON.toJSONString(list));
        String action = req.getParameter("action");
        System.out.println("action " + action);
        List<Defect> list = new ArrayList<>();
        switch (action){
            case "get" :
                String pageNo = req.getParameter("pageNo");
                try {
                    list = dao.getDefectAll(pageNo);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().write(JSON.toJSONString(list));
                break;

            case "receipt":
                String solveTaskCode = req.getParameter("solveTaskCode");
                System.out.println("no");
                try {
                    list = dao.getDefectReceipt(solveTaskCode);
                    resp.setContentType("applicaton/json;charset=utf-8");
                    resp.getWriter().write(JSON.toJSONString(list));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String solveTaskCode = req.getParameter("solveTaskCode");
        int row = 0;
        switch (action){
            case "search" :
                String taskStatus = req.getParameter("taskStatus");
                String issuedByName = req.getParameter("issuedByName");
                String startTime = req.getParameter("startTime");
                String endTime = req.getParameter("endTime");
                List<Defect>list = null;
                try {
                    list = dao.getDefectBycondition(solveTaskCode,taskStatus,issuedByName,startTime,endTime);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.setContentType("applicaton/json;charset=utf-8");
                resp.getWriter().write(JSON.toJSONString(list));
                break;

            case "submit":

                String taskNotes = req.getParameter("taskNotes");
                String taskFinishReport = req.getParameter("taskFinishReport");
                System.out.println(taskNotes);
                System.out.println(taskFinishReport);

                row = dao.submitDefect(solveTaskCode,taskNotes,taskFinishReport);
                break;
            case "check":
                String managerSuggestion = req.getParameter("managerSuggestion");
                String issuedSuggestion = req.getParameter("issuedSuggestion");
                String isCancel = req.getParameter("isCancel");

                row = dao.checkDefect(solveTaskCode,managerSuggestion,issuedSuggestion,isCancel);
            default:
                break;
        }
    }


}
