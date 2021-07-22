package Servlet;

import JDBC.PsRolePermissionDao;
import JavaBean.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author yt
 * main的Servlet
 * 作用：1、管理各个角色访问不同页面的权限
 */
@WebServlet("/Homework/HTML/UserPermission")
public class ServletUserPermission extends HttpServlet {
    PsRolePermissionDao psRolePermissionDao = new PsRolePermissionDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        User user = (User) request.getSession().getAttribute("user");
        String resourceCode = request.getParameter("resourceCode");

        try {
            int flag = 0;
            String functionCode = psRolePermissionDao.getFunctionCodeByRoleId(user.getRoleId());
            String[] arrayCode = functionCode.split(",");

            for (int i = 0; i < arrayCode.length; i++) {
                if (resourceCode.equals(arrayCode[i])) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"fig\":\"success\"}");
            } else {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"fig\":\"error\"}");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        User user = (User) request.getSession().getAttribute("user");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(user));
    }
}