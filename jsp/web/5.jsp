<%@ page import="org.example.bean.User" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%
    User user = new User();
    user.setUsername("along");
    user.setPassword("123");
    user.setAge(10);


    request.setAttribute("userObj", user);

%>

${userObj}
<br>
${userObj.username}
<br>
${userObj.password}
<br>
${userObj.age}

