package com.along.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object sysTime = req.getAttribute("sysTime");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("系统时间为：" + sysTime);
    }

}
