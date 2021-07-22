package Servlet;

import JDBC.PageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Homework/HTML/SystemConfig/pageServlet")
public class pageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageDao dao = new PageDao();
        int count = 0;
        try {
            count = dao.getUserCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setContentType("application/json;charset=utf-8");
        String json="{\"count\":"+count+"}";
        resp.getWriter().write(json);
    }
}
