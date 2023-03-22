package oa.web.action;

import com.mysql.cj.protocol.Resultset;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oa.utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptTailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String deptno = req.getParameter("deptno");
        PrintWriter out = resp.getWriter();
        String contextPath = req.getContextPath();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("    <title>部门详细</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("    <h1>部门详细</h1>");
        out.print("    <hr>");


        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno,dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("                部门编号：" + deptno + " <br>");
                out.print("                部门名称：" + dname + " <br>");
                out.print("        部门位置：" + loc + " <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("    <hr>");
        out.print("    <a href='" + contextPath + "/dept/list'>后退</a>");
        out.print("</body>");
        out.print("</html>");

    }

}
