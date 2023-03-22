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

public class DeptListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("    <title>部门列表</title>");


        out.print("<script type='text/javascript'>");
        out.print("    function del(dno){");
        out.print("        if (window.confirm('亲、删除了不可恢复哦')) {");
        out.print("            document.location.href = '/oa/dept/delete?deptno=' + dno ");
        out.print("        }");
        out.print("    }");
        out.print("</script>");


        out.print("</head>");
        out.print("<body>");
        out.print("    <h1 align='center'>部门列表</h1>");
        out.print("    <hr>");
        out.print("    <table border='1px' align='center' width='50%'>");
        out.print("        <tr>");
        out.print("            <th>序号</th>");
        out.print("            <th>部门编号</th>");
        out.print("            <th>部门名称</th>");
        out.print("            <th>部门位置</th>");
        out.print("        </tr>");


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno as a , dname , loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                int deptno = rs.getInt("a");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("<tr>");
                out.print("    <td>" + (++i) + "</td>");
                out.print("    <td>" + deptno + "</td>");
                out.print("    <td>" + dname + "</td>");
                out.print("    <td>" + loc + "</td>");
                out.print("    <td>");
                out.print("        <a href='javascript:void(0)' onclick='del(" + deptno + ")' >删除</a>");
                out.print("        <a href='" + contextPath + "/dept/edit?deptno=" + deptno + "'>修改</a>");
                out.print("        <a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>详情</a>");
                out.print("    </td>");
                out.print("</tr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("    </table>");
        out.print("    <hr>");
        out.print("    <a href='" + contextPath + "/add.html'>新增部门</a>");
        out.print("</body>");
        out.print("</html>");

    }
}
