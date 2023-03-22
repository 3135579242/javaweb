package oa.web.action;

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

public class DeptEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String contextPath = req.getContextPath();
        String deptno = req.getParameter("deptno");
        PrintWriter out = resp.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("    <title>修改部门</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>修改部门</h1>");
        out.print("<hr>");
        out.print("<form action='" + contextPath + "/dept/update' method='post'>");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno , dname , loc from dept ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("                部门编号<input type='text' name='deptno' value='" + deptno + "' readonly><br>");
                out.print("                部门名称<input type='text' name='dname' value='" + dname + "' ><br>");
                out.print("                部门位置<input type='text' name='loc' value='" + loc + "' ><br>");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }


        out.print("    <input type='submit' value='修改'>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");


    }

}
