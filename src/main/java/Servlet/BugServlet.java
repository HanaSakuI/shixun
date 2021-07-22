package Servlet;

import com.alibaba.fastjson.JSON;
import JDBC.BugDao;
import JavaBean.Bug;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/Homework/HTML/TaskConfig/bug")
public class BugServlet extends HttpServlet {
    BugDao dao = new BugDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Bug> list= null;
        try {
            list = dao.getBugAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }

}
