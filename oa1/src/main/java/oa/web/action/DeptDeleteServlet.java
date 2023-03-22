package oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        if (count == 1) {
            req.getRequestDispatcher("/dept/list").forward(req, resp);
        } else {
            req.getRequestDispatcher("/error.html").forward(req, resp);
        }
    }

}
