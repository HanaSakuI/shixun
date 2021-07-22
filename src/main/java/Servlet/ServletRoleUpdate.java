package Servlet;

import JDBC.PsRoleDao;
import JavaBean.PsRole;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yt
 * RoleMange的Servlet
 * 作用：1、实现角色的添加
 * 2、实现角色的修改
 * 3、实现角色的删除
 */
@WebServlet("/Homework/HTML/SystemConfig/roleUpdate")
public class ServletRoleUpdate extends HttpServlet {
    PsRoleDao psRoleDao = new PsRoleDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleId = request.getParameter("roleId");

        PsRole psRole = null;
        try {
            psRole = psRoleDao.getRoleNameByRoleId(roleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(psRole));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        String createdBy = request.getParameter("createdBy");
        String action = request.getParameter("action");


        System.out.println("roleId = " + roleId + "; roleName = " + roleName + "; createdBy = " + createdBy + ";");

        if ("delete".equals(action)) {
            int row = psRoleDao.deleteRoleByRoleId(roleId);
            int flag = 0;
            if (row > 0) {
                response.getWriter().write("{\"fig\":\"success\", \"flag\":" + flag + "}");
            } else {
                response.getWriter().write("{\"fig\":\"error\", \"flag\":" + flag + "}");
            }
        } else {
            int flag = 0;
            int row = 0;
            int isStart = Integer.parseInt(request.getParameter("isStart"));
            try {
                if ("add".equals(action)) {
                    flag = psRoleDao.judgeRoleIdAndRoleName(roleId, roleName);
                    if (flag == 0) {
                        row = psRoleDao.addRole(roleId, roleName, createdBy, isStart);
                    }
                } else if ("edit".equals(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    flag = psRoleDao.judgeRoleIdAndRoleName(id, roleId, roleName);
                    int flag1 = psRoleDao.judgeRoleIdAndRoleName(roleId, roleName);
                    if (flag == 0) {
                        if (flag1 == 0) {
                            row = psRoleDao.updateRole(id, roleId, roleName, isStart);
                        } else {
                            flag = flag1;
                        }
                    } else if (flag == 1) {
                        if (flag1 == 1) {
                            row = psRoleDao.updateRole(id, roleId, roleName, isStart);
                        } else {
                            flag = 2;
                        }
                    } else if (flag == 2) {
                        System.out.println("flag1 = " + flag1 + "|roleName = " + roleName + "|roleId = " + roleId);
                        if (flag1 == 2) {
                            row = psRoleDao.updateRole(id, roleId, roleName, isStart);
                        } else {
                            flag = 1;
                        }
                    } else if (flag == 3) {
                        if (flag1 == 3) {
                            row = psRoleDao.updateRole(id, roleId, roleName, isStart);
                        } else {
                            flag = flag1;
                        }
                    }
                }

                System.out.println("flag = " + flag + "/ row = " + row);

                if (row > 0) {
                    response.getWriter().write("{\"fig\":\"success\", \"flag\":" + flag + "}");
                } else {
                    response.getWriter().write("{\"fig\":\"error\", \"flag\":" + flag + "}");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
