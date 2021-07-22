package Servlet;

import JDBC.DealLineBySQL;
import JDBC.PsSystemConfigDao;
import JavaBean.PsSystemConfig;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet( "/Homework/HTML/SystemConfig/SystemConfigListServlet")
public class SystemConfigListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setContentType("applicaton/json;charset=utf-8");
        String productId = request.getParameter("productId");
        String flag = "";
        int row = 0;
        switch (productId){
            case "selectAll":
                try {
                    List<PsSystemConfig> psSystemConfigList = new PsSystemConfigDao().getAllSystemConfigByConfigCode("BUG_TYPE");
                    response.getWriter().write(JSON.toJSONString(psSystemConfigList));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

                case"delete":
                String configValueId = request.getParameter("configValueId");
                    row = new PsSystemConfigDao().deleteBugTypeByValueId(configValueId);
                    if (row >= 1){
                        flag = "{\"flag\":\"success\"}";
                    }else{
                        flag = "{\"flag\":\"error\"}";
                    }
                    response.getWriter().write(flag);
                break;

                case "edit":
                    try {
                        String editConfigValueId = request.getParameter("configValueId");
                        PsSystemConfig psSystemConfig = new PsSystemConfigDao().getPsSystemConfigByBugTypeValueId(editConfigValueId);
                        response.getWriter().write(JSON.toJSONString(psSystemConfig));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;


            case "changeMessage":
                String valueId = request.getParameter("configValueId");
                String typeName = request.getParameter("bugName");
                String isStart = request.getParameter("isStart");

                 row = new PsSystemConfigDao().editBugTypeByValueId(valueId,typeName,Integer.parseInt(isStart));
                if (row >= 1){
                    flag = "{\"flag\":\"success\"}";
                }else{
                    flag = "{\"flag\":\"error\"}";
                }
                response.getWriter().write(flag);
                break;
            default:break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
