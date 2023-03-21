package com.along.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class LoginServlet extends GenericServlet {


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("login");
        ServletConfig servletConfig = getServletConfig();
        System.out.println("----->" + servletConfig);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("LoginServlet init()");
    }
}
