package Servlet;

import JDBC.PsRoleDao;
import JavaBean.PsRole;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yt
 * RoleMange的Servlet
 * 作用：1、实现角色的展示
 *
 */
@WebServlet("/Homework/HTML/SystemConfig/role")
public class ServletRoleManage extends HttpServlet {
    PsRoleDao psRoleDao = new PsRoleDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNO");
        pageNo = "1";
        List<PsRole> psRoleList = null;

        try {
            psRoleList = psRoleDao.getRoleAll(pageNo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(psRoleList));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String roleName = request.getParameter("roleName");
        String isStart = request.getParameter("isStart");

        System.out.println("roleName = " + roleName + "; isStart = " + isStart);

        List<PsRole> psRoleList = null;

        try {
            psRoleList = psRoleDao.getRoleByRoleNameAndIsStart(roleName, isStart);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(psRoleList));
    }
}
