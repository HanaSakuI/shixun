package Servlet;

import JDBC.PsSolverDetailDao;
import JavaBean.PsSolverDetail;
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
 * DefectDistribution的Servlet
 * 作用：
 * 1、显示待分配的消缺员和已分配的消缺员
 * 2、分配消缺员和取消分配消缺员
 */
@WebServlet("/Homework/HTML/TaskConfig/DefectDistribution")
public class ServletDefectDistribution extends HttpServlet {
    PsSolverDetailDao psSolverDetailDao = new PsSolverDetailDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solveTaskCode = request.getParameter("solveTaskCode");

        try {
            List<List<PsSolverDetail>> listList = new ArrayList<>();
            List<PsSolverDetail> unassignedPsSolverDetailList = psSolverDetailDao.getSolverBySolverStatus("", 0);
            List<PsSolverDetail> assignedPsSolverDetailList = psSolverDetailDao.getSolverBySolverStatus(solveTaskCode, 1);

            listList.add(unassignedPsSolverDetailList);
            listList.add(assignedPsSolverDetailList);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(listList));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String solverCodes = request.getParameter("solverCodes");
        String action = request.getParameter("action");
        String solveTaskCode = request.getParameter("solveTaskCode");
        System.out.println("solveTaskCode = " + solveTaskCode + "; solverCodes = " + solverCodes + "; action = " + action);

        try {
            if ("choose".equals(action)) {
                psSolverDetailDao.changeSolverStatusAndAssignments(solverCodes, solveTaskCode, 1);
            } else {
                psSolverDetailDao.changeSolverStatusAndAssignments(solverCodes, solveTaskCode, 0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
