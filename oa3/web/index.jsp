<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎使用OA系统</title>
</head>
<body>
<%--<a href="<%=request.getContextPath() %>/dept/list">查看部门列表</a>--%>

<h1>用户登录</h1>
<hr>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username: <input type="text" name="username"><br>
    password: <input type="password" name="password"><br>

    <input type="checkbox" name="f" value="1">十天内免登录 <br>
    <input type="submit" value="login">

</form>


</body>

</html>