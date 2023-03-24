<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>

<a href="<%=request.getContextPath()%>/cookie/generate">服务器生成Cookie、响应给浏览器、浏览器接收Cookie、将Cookie放到客户端上</a>
<br>
<a href="<%=request.getContextPath()%>/sendCookie">浏览器发送Cookie给服务器</a>

</body>
</html>
