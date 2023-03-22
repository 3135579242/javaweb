package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_student (no,name) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            ps.setString(2, name);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
//            req.getRequestDispatcher("/success.html").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/success.html");
        } else {

            resp.sendRedirect(req.getContextPath() + "/error.html");
        }

    }


}
