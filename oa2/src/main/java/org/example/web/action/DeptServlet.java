package org.example.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/list", "/dept/detail", "/dept/delete", "/dept/save", "/dept/edit", "/dept/update"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(req, resp);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(req, resp);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(req, resp);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(req, resp);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(req, resp);
        } else if ("/dept/update".equals(servletPath)) {
            doUpdate(req, resp);
        }


    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

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
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            request.getRequestDispatcher("/error.html").forward(request, response);
        }

    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    private void doSave(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String contextPath = req.getContextPath();
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;


        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept (deptno,dname,loc) values(?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            resp.sendRedirect(contextPath + "/dept/list");
//            req.getRequestDispatcher("/dept/list").forward(req, resp);
        } else {
            resp.sendRedirect("/error.html");
//            req.getRequestDispatcher("/error.html").forward(req, resp);
        }


    }


    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
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
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String deptno = request.getParameter("deptno");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();

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
            ps.setString(1, deptno);
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

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
