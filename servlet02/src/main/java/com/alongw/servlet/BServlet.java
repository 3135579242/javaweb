package com.alongw.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {

    public BServlet() {
        System.out.println("无参BServlet");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BServlet init");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BServlet service");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("BServlet destroy");
    }
}
