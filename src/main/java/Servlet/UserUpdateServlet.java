package Servlet;

import JDBC.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/Homework/HTML/SystemConfig/userupdate")
public class UserUpdateServlet extends HttpServlet {
    UserDaoImpl udi = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userCode = req.getParameter("userCode");
        String action = req.getParameter("action");

        String userName = null;
        String userPassword = null;
        String sex = null;
        String age = null;
        String phoneTel = null;
        String email = null;
        String lastLoginTime = null;
        String roleId = null;
        String entryDate = null;
        String dimissionDate = null;
        String createdBy = null;
        String userStatus = null;
        String oldPassword = null;
        String newPassword = null;

        if(action.equals("add") || action.equals("update")){
            userName = req.getParameter("userName");
            userPassword = req.getParameter("userPassword");
            sex = req.getParameter("sex");
            age = req.getParameter("age");
            phoneTel = req.getParameter("phoneTel");
            email = req.getParameter("email");
            Date date = new Date();
            lastLoginTime = date.toLocaleString();
            roleId = req.getParameter("roleId");
            entryDate = req.getParameter("entryDate");
            dimissionDate = req.getParameter("dimissionDate");
            createdBy = req.getParameter("createdBy");
            System.out.println(createdBy);
            userStatus = req.getParameter("userStatus");
        }
        else if(action.equals("forzen")){
            userPassword = req.getParameter("userPassword");
        }
        else if(action.equals("updateNew")){
            userName = req.getParameter("userName");
            oldPassword = req.getParameter("oldPassword");
            newPassword = req.getParameter("newPassword");
            sex = req.getParameter("sex");
            age = req.getParameter("age");
            phoneTel = req.getParameter("phoneTel");
            email = req.getParameter("email");
            entryDate = req.getParameter("entryDate");
            dimissionDate = req.getParameter("dimissionDate");
        }

        int row = 0;
        switch (action){
            case "add":
                try {
                    row = udi.addUser(userCode,userName,userPassword,sex,age,phoneTel,email,lastLoginTime,roleId,entryDate,dimissionDate,createdBy,userStatus);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            case "frozen":
                row = udi.forzenUser(userCode);
                break;

            case "update":
                try {
                    row = udi.updateUser(userCode,userName,userPassword,sex,age,phoneTel,email,lastLoginTime,roleId,entryDate,dimissionDate,userStatus);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "del":
                row = udi.deleteUser(userCode);
                break;

            case "updateNew":
                try {
                    row = udi.updateUserNew(userCode,userName,oldPassword,newPassword,sex,age,phoneTel,email,entryDate,dimissionDate);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
            default:break;
        }

        if(row>0){
            resp.getWriter().write("{\"fig\":\"success\"}");
        }else if(row == -2){
            resp.getWriter().write("{\"fig\":\"defect\"}");
        }
        else{
            resp.getWriter().write("{\"fig\":\"error\"}");
        }
    }
}
