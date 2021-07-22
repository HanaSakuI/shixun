package Servlet;

import JDBC.PsSystemConfigDao;
import JavaBean.PsSystemConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author yt
 * SystemConfig的Servlet
 * 作用：
 * 1、实现对config的增加
 * 2、实现对config的删除
 */
@WebServlet("/Homework/HTML/SystemConfig/ConfigUpdate")
public class ServletSystemConfigUpdate extends HttpServlet {
    PsSystemConfigDao psSystemConfigDao = new PsSystemConfigDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String configCode = request.getParameter("configCode");

        String configName = request.getParameter("configName");


        int configValueId = 0;
        if (request.getParameter("configValueId") != null) {
            configValueId = Integer.parseInt(request.getParameter("configValueId"));
        }

        String configValueName = request.getParameter("configValueName");

        int isStart = 0;
        if (request.getParameter("isStart") != null) {
            isStart = Integer.parseInt(request.getParameter("isStart"));
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int row = 0;
            int flag = 0;

            try {
                PsSystemConfig psSystemConfig = psSystemConfigDao.getConfig();
                row = psSystemConfigDao.deleteConfigByConfigCodeAndConfigValueId(psSystemConfig.getConfigCode(), configValueId);
                System.out.println("row = " + row + "; configCode = " + psSystemConfig.getConfigCode() + "; configValueId = " + configValueId);

                if (row > 0) {
                    response.getWriter().write("{\"fig\":\"success\", \"flag\":" + flag + "}");
                } else {
                    response.getWriter().write("{\"fig\":\"error\", \"flag\":" + flag + "}");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            int flag = 0;
            int row = 0;

            if ("addUp".equals(action)) {
                try {
                    flag = psSystemConfigDao.judgeSystemConfigByConfigCode(configCode, configName);
                    if (flag == 0) {
                        row = psSystemConfigDao.addSystemConfig(configCode, configName);
                    }

                    if (row > 0) {
                        response.getWriter().write("{\"fig\":\"success\", \"flag\":" + flag + "}");
                    } else {
                        response.getWriter().write("{\"fig\":\"error\", \"flag\":" + flag + "}");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if ("addDown".equals(action)) {
                try {
                    flag = psSystemConfigDao.judgeSystemConfigByConfigCodeAndConfigValueIdAndConfigValueName(configCode, configValueId, configValueName);
                    if (flag == 0) {
                        PsSystemConfig psSystemConfig = psSystemConfigDao.getConfig();
                        row = psSystemConfigDao.addSystemConfig(psSystemConfig.getConfigCode(), psSystemConfig.getConfigName(), configValueId, configValueName, isStart);
                    }

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
}
