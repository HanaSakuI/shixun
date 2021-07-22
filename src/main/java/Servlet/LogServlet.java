package Servlet;

import JDBC.LogDaoImpl;
import JavaBean.Log;
import JavaBean.User;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/Homework/HTML/SystemConfig/log")
public class LogServlet extends HttpServlet {
    LogDaoImpl ldi = new LogDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String userCode = req.getParameter("userCode");
            String beginTime = req.getParameter("beginTime");
            String endTime = req.getParameter("endTime");
            List<Log> list = null;

        if(beginTime != null && endTime != null){
            try {
                System.out.println("search");
                list = ldi.searchLogByTime(beginTime,endTime,userCode);
                resp.setContentType("applicaton/json;charset=utf-8");
                resp.getWriter().write(JSON.toJSONString(list));
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                list = ldi.getLogByUserCode(userCode);
                resp.setContentType("applicaton/json;charset=utf-8");
                resp.getWriter().write(JSON.toJSONString(list));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
