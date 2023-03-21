package com.along.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();
        ServletContext application = this.getServletContext();
        out.print("servletContext --> " + application);

        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = application.getInitParameter(name);
            out.print("<br>" + name + "-->" + value + "<br>");
        }
        //获取项目根路径
        String contextPath = application.getContextPath();
        out.print(contextPath);

        //获取文件绝对路径 从web根开始找
        String realPath1 = application.getRealPath("/index.html");
        out.print(realPath1);

        String realPath2 = application.getRealPath("/common/index.html");
        out.print("<br>" + realPath2);
        //记录日志
        application.log("楼兰");

        int age = 17;
        if (age < 18){
            application.log("对不起、未成年",new RuntimeException("该网站不适合你"));
        }

        User user1 = new User("jack", "123");
        application.setAttribute("userObj",user1);
        Object userObj = application.getAttribute("userObj");
        out.print("<br>" + userObj);


    }

}
