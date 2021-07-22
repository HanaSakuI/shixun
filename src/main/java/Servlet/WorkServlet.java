package Servlet;

import JDBC.WorkDao;
import JavaBean.User;
import JavaBean.Work;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Homework/HTML/MyWorkPlace/work")
public class WorkServlet extends HttpServlet {
    WorkDao dao = new WorkDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String userCode = req.getParameter("userCode");
        System.out.println("yes");
        System.out.println(user.getUserCode());

        List<Work> list = new ArrayList<Work>();

        if(user.getRoleId().equals("ps_role04")){
            try {
                list = dao.getXQWork(user.getUserCode());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(user.getRoleId().equals("ps_role03")){
            try {
                list = dao.getXJWork(user.getUserCode());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
//        try {
////            list = dao.getXQWork(user.getUserCode());
//            list = dao.getXJWork(userCode);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        resp.setContentType("applicaton/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workCode = req.getParameter("workCode");
        User user = (User)req.getSession().getAttribute("user");
        System.out.println(user.getUserCode());
        int row = 0;
        row = dao.handleWork(workCode,user.getRoleId());

        if(row>0){
            resp.getWriter().write("{\"fig\":\"success\"}");
        }else{
            resp.getWriter().write("{\"fig\":\"error\"}");
        }
    }
}
