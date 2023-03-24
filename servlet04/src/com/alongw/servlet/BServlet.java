package com.alongw.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();
        ServletContext application = this.getServletContext();
        out.print("servletContext --> " + application);

        Object userObj = application.getAttribute("userObj");
        out.print("<br>" + userObj);
    }
}
