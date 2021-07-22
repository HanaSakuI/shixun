package Servlet;

import JDBC.UserDaoImpl;
import JavaBean.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Homework/HTML/Login/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.setContentType("applicaton/json;charset=utf-8");
        String status = request.getParameter("status");
        String flag = "";
        switch (status) {
            case "login":
                String userCode = request.getParameter("userCode");
                String passWord = request.getParameter("passWord");
                try {
                    User user = new UserDaoImpl().userLogin(userCode, passWord);
                    if (user == null) {
                        flag = "{\"flag\":\"error\"}";
                    } else {
                        if (user.getUserStatus() == 0) {
                            flag = "{\"flag\":\"frozen\"}";
                        } else {
                            request.getSession().setAttribute("user", user);
                            flag = "{\"flag\":\"success\"}";
                        }
                    }
                    response.getWriter().write(flag);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "logout":
                request.getSession().removeAttribute("user");
                flag = "{\"flag\":\"success\"}";
                response.getWriter().write(flag);
                break;
            case "home":
                User user = (User) request.getSession().getAttribute("user");
                response.getWriter().write(JSON.toJSONString(user));
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
