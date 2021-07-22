package Servlet;

import com.alibaba.fastjson.JSON;
import JDBC.TowerDao;
import JavaBean.Tower;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Homework/HTML/TowerConfig/tower")
public class TowerServlet extends HttpServlet {
    TowerDao dao = new TowerDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("archer");
        String pageNo = req.getParameter("pageNo");
        List<Tower> list= null;
        try {
            list = dao.getTowerAll(pageNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lineName =req.getParameter("lineName");
        String isStart = req.getParameter("isStart");
        List<Tower>list=null;
        try {
            list = dao.getTowerBycondition(lineName,isStart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("applicaton/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(list));
    }
}


