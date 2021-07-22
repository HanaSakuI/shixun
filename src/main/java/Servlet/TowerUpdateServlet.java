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
@WebServlet("/Homework/HTML/TowerConfig/toweredit")
public class TowerUpdateServlet extends HttpServlet {
    TowerDao dao=new TowerDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=   req.getParameter("id");
        Tower tower= null;
        try {
           tower = dao.getTowerBypoleCode(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(tower));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //1.接收页面传递过来的数据
        req.setCharacterEncoding("utf-8");
        //1.接收页面传递过来的数据
        String poleCode= null;
        String action=  req.getParameter("action");
        String id = req.getParameter("id");
        String isStart=null;
        String lineName=null;
        if(!action.equals("del")){
            poleCode= req.getParameter("poleCode");
            isStart= req.getParameter("isStart");
            //System.out.println(isStart);
            lineName=req.getParameter("lineName");
        }


        int row=0;
        switch (action){
            case "modify":
                //执行修改
               row =dao.updateTower(poleCode,isStart,id);
                break;
            case "add":
                //执行添加的操作
                row=   dao.addTower(poleCode,isStart,lineName);
                break;
            case "del":
                row= dao.delTower(id);
                break;
        }
        //System.out.println(row);
        if(row>0){
            resp.getWriter().write("{\"fig\":\"success\"}");
        }else{
            resp.getWriter().write("{\"fig\":\"error\"}");
        }

    }

}
