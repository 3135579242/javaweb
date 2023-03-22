package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bean.User;

import java.io.IOException;

public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId("1");
        user.setName("楼兰");

        req.setAttribute("userObj", user);
        //请求转发（一次请求）
//        req.getRequestDispatcher("/b").forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/b");

    }

}
