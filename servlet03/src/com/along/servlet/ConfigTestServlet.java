package com.along.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/*
    ---->org.apache.catalina.core.StandardWrapperFacade@1611c351
    ---->org.apache.catalina.core.StandardWrapperFacade@5471544a
 */
public class ConfigTestServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        ServletConfig config = this.getServletConfig();
        PrintWriter out = servletResponse.getWriter();
        out.print("---->" + config);
        out.print("---->" + getServletConfig().getServletName());

        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            String test = getInitParameter(s);
            out.print("<br>");
            out.print(s + "==>" + test);
        }
        ServletContext application1 = config.getServletContext();

        ServletContext application2 = this.getServletContext();


    }


}
