package Servlet;

import JDBC.PsRolePermissionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author yt
 * RolePermissionConfig的Servlet
 * 作用：1、实现权限的更改
 */
@WebServlet("/Homework/HTML/SystemConfig/RolePermissionUpdate")
public class ServletRolePermissionUpdate extends HttpServlet {
    PsRolePermissionDao psRolePermissionDao = new PsRolePermissionDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String functionCodeOfSystemManager = request.getParameter("functionCodeOfSystemManager");
        String functionCodeOfLineManager = request.getParameter("functionCodeOfLineManager");
        String functionCodeOfInspector = request.getParameter("functionCodeOfInspector");
        String functionCodeOfVacancy = request.getParameter("functionCodeOfVacancy");

        int row1 = psRolePermissionDao.updateFunctionCodeByRoleId("ps_role01", functionCodeOfSystemManager + "24,25,");
        int row2 = psRolePermissionDao.updateFunctionCodeByRoleId("ps_role02", functionCodeOfLineManager + "24,25,");
        int row3 = psRolePermissionDao.updateFunctionCodeByRoleId("ps_role03", functionCodeOfInspector + "24,25,");
        int row4 = psRolePermissionDao.updateFunctionCodeByRoleId("ps_role04", functionCodeOfVacancy + "24,25,");

        if (row1 + row2 + row3 + row4 == 4) {
            response.getWriter().write("{\"fig\":\"success\"}");
        } else {
            response.getWriter().write("{\"fig\":\"error\"}");
        }
    }
}
