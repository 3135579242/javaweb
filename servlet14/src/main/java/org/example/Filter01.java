package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebFilter("/a.l")
public class Filter01 implements Filter {

    public Filter01() {
        System.out.println("Filter01()");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init()");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter()");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
