package com.alongw.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public abstract class GenreicServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        this.init();
    }

    public void init(){

    }




    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
