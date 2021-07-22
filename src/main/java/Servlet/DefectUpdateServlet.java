package Servlet;

import com.alibaba.fastjson.JSON;
import JDBC.DefectDao;
import JavaBean.Defect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/Homework/HTML/TaskConfig/defectedit")
public class DefectUpdateServlet extends HttpServlet {
    DefectDao dao = new DefectDao();
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String solveTaskCode = req.getParameter("solveTaskCode");
        System.out.println(solveTaskCode);
        String solveTaskName = req.getParameter("solveTaskName");
        String workDocTypeName = req.getParameter("workDocTypeName");
        String taskManagerName = req.getParameter("taskManagerName");
        String issuedByName = req.getParameter("issuedByName");
        String issuedTime = req.getParameter("issuedTime");
        String taskDesc = req.getParameter("taskDesc");
        String common = req.getParameter("common");
        String action = req.getParameter("action");
        int row = 0;
        switch (action){
            case "modify":
                //执行修改
                row =dao.updateDefect(solveTaskCode,solveTaskName,taskManagerName,common,issuedByName);
                break;
            case "add":
                //执行添加的操作
                row=   dao.addDefect(solveTaskCode,solveTaskName,workDocTypeName,taskManagerName,issuedByName,taskDesc,common);
                break;
            case "change":
                row= dao.cancelDefect(solveTaskCode);
                break;
        }

        if(row>0){
            resp.getWriter().write("{\"fig\":\"success\"}");
        }else{
            resp.getWriter().write("{\"fig\":\"error\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String solveTaskCode = req.getParameter("solveTaskCode");
        Defect defect = null;
        try {
            defect = dao.getDefectBysolveTaskCode(solveTaskCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(defect));
    }
}
