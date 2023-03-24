<%@ page import="com.alongw.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>部门列表</title>
    <script type='text/javascript'>
        function del(dno) {
            if (window.confirm('亲、删除了不可恢复哦')) {
                document.location.href = '<%=request.getContextPath()%>/dept/delete?dno=' + dno
            }
        }
    </script>
</head>
<body>
<h1 align='center'>部门列表</h1>
<hr>

<h1>欢迎【<%= session.getAttribute("username")%>】登录</h1>

<a href= <%=request.getContextPath()%>/dept/logout>退出登录</a>


<table border='1px' align='center' width='50%'>
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>部门位置</th>
    </tr>

    <%
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        int j = 0;
        for (int i = 0; i < deptList.size(); i++) {
    %>
    <tr>
        <td><%=++j%>
        </td>
        <td><%=deptList.get(i).getDeptno()%>
        </td>
        <td><%=deptList.get(i).getDname()%>
        </td>
        <td><%=deptList.get(i).getLoc()%>
        </td>
        <td>
            <a href='javascript:void(0)' onclick='del(<%=deptList.get(i).getDeptno()%>)'>删除</a>
            <a href='<%=request.getContextPath()%>/dept/edit?dno=<%=deptList.get(i).getDeptno()%>'>修改</a>
            <a href='<%=request.getContextPath()%>/dept/detail?dno=<%=deptList.get(i).getDeptno()%>'>详情</a>
        </td>
    </tr>

    <%
        }
    %>

</table>
<hr>
<a href='<%=request.getContextPath()%>/add.jsp'>新增部门</a>
</body>
</html>
