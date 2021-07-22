package Servlet;

import JDBC.PsSystemConfigDao;
import JavaBean.PsSystemConfig;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yt
 * SystemConfig的Servlet
 * 作用：1、实现SystemConfig的展示
 */
@WebServlet("/Homework/HTML/SystemConfig/SystemConfig")
public class ServletSystemConfig extends HttpServlet {
    PsSystemConfigDao psSystemConfigDao = new PsSystemConfigDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        pageNo = "1";

        List<PsSystemConfig> psSystemConfigList = null;

        try {
            psSystemConfigList = psSystemConfigDao.getAllSystemConfigByKind(pageNo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(psSystemConfigList));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String configCode = request.getParameter("configCode");
        if (configCode != null) {
            try {
                String configNameFromUp = psSystemConfigDao.getConfigNameByConfigCode(configCode);
                psSystemConfigDao.updateGetConfigCode(configCode, configNameFromUp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        List<PsSystemConfig> psSystemConfigList = null;

        try {
            psSystemConfigList = psSystemConfigDao.getAllSystemConfigByConfigCode(configCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(psSystemConfigList));

    }
}
