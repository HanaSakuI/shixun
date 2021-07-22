package Servlet;

import JDBC.PsRolePermissionDao;
import JavaBean.PsFunction;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yt
 * RolePermissionConfig的Servlet
 * 作用：1、展示各个角色的功能
 */
@WebServlet("/Homework/HTML/SystemConfig/RolePermission")
public class ServletRolePermission extends HttpServlet {
    PsRolePermissionDao psRolePermissionDao = new PsRolePermissionDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String functionCodeOfSystemManager = psRolePermissionDao.getFunctionCodeByRoleId("ps_role01");
            String functionCodeOfLineManager = psRolePermissionDao.getFunctionCodeByRoleId("ps_role02");
            String functionCodeOfInspector = psRolePermissionDao.getFunctionCodeByRoleId("ps_role03");
            String functionCodeOfVacancy = psRolePermissionDao.getFunctionCodeByRoleId("ps_role04");

            String[] arrayCodeOfSystemManager = functionCodeOfSystemManager.split(",");
            String[] arrayCodeOfLineManager = functionCodeOfLineManager.split(",");
            String[] arrayCodeOfInspector = functionCodeOfInspector.split(",");
            String[] arrayCodeOfVacancy = functionCodeOfVacancy.split(",");

            String[] arrayIdOfSystemManager = new String[arrayCodeOfSystemManager.length];
            String[] arrayIdOfLineManager = new String[arrayCodeOfLineManager.length];
            String[] arrayIdOfInspector = new String[arrayCodeOfInspector.length];
            String[] arrayIdOfVacancy = new String[arrayCodeOfVacancy.length];

            List<List<PsFunction>> psFunctionList = new ArrayList<>();
            List<PsFunction> psFunctionListOfSystemManager = new ArrayList<>();
            List<PsFunction> psFunctionListOfLineManager = new ArrayList<>();
            List<PsFunction> psFunctionListOfInspector = new ArrayList<>();
            List<PsFunction> psFunctionListOfVacancy = new ArrayList<>();

            for (int i = 0; i < arrayCodeOfSystemManager.length; i++) {
                arrayIdOfSystemManager[i] = "#systemManager_" + arrayCodeOfSystemManager[i];
            }
            for (int i = 0; i < arrayCodeOfLineManager.length; i++) {
                arrayIdOfLineManager[i] = "#lineManager_" + arrayCodeOfLineManager[i];
            }
            for (int i = 0; i < arrayCodeOfInspector.length; i++) {
                arrayIdOfInspector[i] = "#inspector_" + arrayCodeOfInspector[i];
            }
            for (int i = 0; i < arrayCodeOfVacancy.length; i++) {
                arrayIdOfVacancy[i] = "#vacancy_" + arrayCodeOfVacancy[i];
            }

            for (int i = 0; i < arrayCodeOfSystemManager.length; i++) {
                PsFunction psFunction = new PsFunction(arrayCodeOfSystemManager[i], arrayIdOfSystemManager[i]);
                psFunctionListOfSystemManager.add(psFunction);
            }
            for (int i = 0; i < arrayCodeOfLineManager.length; i++) {
                PsFunction psFunction = new PsFunction(arrayCodeOfLineManager[i], arrayIdOfLineManager[i]);
                psFunctionListOfLineManager.add(psFunction);
            }
            for (int i = 0; i < arrayCodeOfInspector.length; i++) {
                PsFunction psFunction = new PsFunction(arrayCodeOfInspector[i], arrayIdOfInspector[i]);
                psFunctionListOfInspector.add(psFunction);
            }
            for (int i = 0; i < arrayCodeOfVacancy.length; i++) {
                PsFunction psFunction = new PsFunction(arrayCodeOfVacancy[i], arrayIdOfVacancy[i]);
                psFunctionListOfVacancy.add(psFunction);
            }

            psFunctionList.add(psFunctionListOfSystemManager);
            psFunctionList.add(psFunctionListOfLineManager);
            psFunctionList.add(psFunctionListOfInspector);
            psFunctionList.add(psFunctionListOfVacancy);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(psFunctionList));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
