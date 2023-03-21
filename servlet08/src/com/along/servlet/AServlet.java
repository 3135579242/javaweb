package com.along.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date nowTime = new Date();
        req.setAttribute("sysTime" , nowTime);

/*      BServet bServet = new BServet();
        bServet.doGet(req,resp);*/
        //请求转发
        req.getRequestDispatcher("/b").forward(req,resp);



    }

}
