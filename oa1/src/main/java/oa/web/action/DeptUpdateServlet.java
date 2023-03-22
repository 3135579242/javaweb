package oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptUpdateServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ? , loc = ? where deptno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (count == 1) {
            req.getRequestDispatcher("/dept/list").forward(req, resp);
        } else {
            req.getRequestDispatcher("/error.html").forward(req, resp);
        }

    }

}
