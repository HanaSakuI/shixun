package Servlet;

import com.alibaba.fastjson.JSON;
import JDBC.UserDaoImpl;
import JavaBean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Homework/HTML/SystemConfig/user")
public class UserServlet extends HttpServlet {
    UserDaoImpl udi = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        List<User> list = null;
        try {
            list = udi.getUserAll(pageNo);
            resp.setContentType("applicaton/json;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(list));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        String userStatus = req.getParameter("userStatus");

        List<User> list = null;
        try {
            list = udi.getUserByuserName(userName,userStatus);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(list));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
