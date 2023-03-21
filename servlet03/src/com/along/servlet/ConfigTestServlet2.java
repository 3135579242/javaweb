package com.along.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class ConfigTestServlet2 extends GenericServlet {


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();
        PrintWriter out = servletResponse.getWriter();
        out.print(" ----2> " + config);
    }


}
